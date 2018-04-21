package com.cni.converter;


import com.cni.converter.support.ConvertUtils;
import com.cni.converter.support.SavePointAdpt;
import com.cni.converter.support.MappingFinder;
import com.cni.converter.support.WaybillAdpt;
import com.cni.dao.entity.Waybill;
import com.cni.exception.ConvertException;
import com.cni.exception.OrderNotFoundException;
import com.cni.httptrack.resp.BluedartResponseBody;
import com.cni.httptrack.resp.BluedartResponseBody.InfoBean.ShipmentBean;
import com.cni.httptrack.resp.BluedartResponseBody.InfoBean.ShipmentBean.ScansBean.ScanDetailBean;
import com.cni.httptrack.support.Channels;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.MapConfigHolder;
import com.cni.statemap.MapResult;
import com.cni.statemap.neoman.NeomanConfigHolder;
import com.cni.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static com.cni.converter.support.ConvertUtils.IGNORE;

/**
 * 将Bluedart数据转成自己的数据格式给前端
 * <p>
 * Created by CNI on 2018/1/18.
 */
public class BluedartConverter implements Converter<BluedartResponseBody> {

    private static final Logger log = LoggerFactory.getLogger(BluedartConverter.class);

    private static final ThreadLocal<SimpleDateFormat> scanBeanDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyyHH:mm", Locale.ENGLISH));
    private static final ThreadLocal<SimpleDateFormat> estimatedDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd MMMMMMMMM yyyy", Locale.ENGLISH));
    private OrderNumMatcher orderNumMatcher;
    private MappingFinder finder;
    private MapConfigHolder configHolder;

    public BluedartConverter(NeomanConfigHolder configHolder) {
        this.configHolder = configHolder;
        this.finder = new MappingFinder(this.configHolder, Channels.BLUEDART);
        this.finder.setStrategy((currStatus, optional) -> {
            //TODO 参数检查
            boolean isBack = (boolean) optional[0];
            String status = null;
            if ("SHIPMENT OUT FOR DELIVERY".equals(currStatus))
                status = isBack ? "Returned In Transit" : "Out For Delivery";

            if ("SHIPMENT DELIVERED".equals(currStatus))
                status = isBack ? "Returned Delivered" : "Delivered";

            if ("SHIPMENT ARRIVED".equals(currStatus)
                    || "CLUBBED CANVAS BAG SCAN".equals(currStatus)
                    || "SHIPMENT ARRIVED AT HUB".equals(currStatus)
                    || "SHIPMENT FURTHER CONNECTED".equals(currStatus)
                    || "SHIPMENT RETURNED".equals(currStatus)) {
                status = isBack ? "Returned In Transit" : "In Transit";
            }
            return new MapResult(status, null);
        });
    }


    @Override
    public Waybill convert(BluedartResponseBody in) throws OrderNotFoundException, ConvertException {
        if (ObjectUtils.isEmpty(in) || !in.isSuccess() || ObjectUtils.isEmpty(in.getInfo()))
            throw new OrderNotFoundException("查无此单" + in);
        return handleBody(in);
    }

    @Override
    public void setOrderNumMatcher(OrderNumMatcher matcher) {
        this.orderNumMatcher = matcher;
    }

    /**
     * @param in 要转换的pojo对象
     * @return 运单实体类
     */
    private Waybill handleBody(BluedartResponseBody in) {
        try {
            List<ShipmentBean> shipmentBeans = in.getInfo().getShipment();
            BluedartResponseBody.InfoBean.ShipmentBean firstBean = shipmentBeans.get(0);
            String refAwb = isEffective(firstBean);

            boolean isback = !StringUtils.isEmpty(refAwb);//通过识别相关单号判断是否是返程
            //TODO 拼接body

            Waybill body = ConvertUtils.createOrderBill(new WaybillAdpt() {

                @Override
                public String getNumber() {
                    return String.valueOf(firstBean.getWaybillNo());
                }

                @Override
                public String getOrigin() {
                    return firstBean.getOrigin();
                }

                @Override
                public String getDestination() {
                    return firstBean.getDestination();
                }

                @Override
                public Long getEstimatedDate() {
                    final SimpleDateFormat sdf = estimatedDateFormat.get();
                    final String expectedDeliveryDate = firstBean.getExpectedDeliveryDate();
                    return DateUtils.parse(sdf, expectedDeliveryDate);
                }

                @Override
                public String getCustomer() {
                    return firstBean.getCustomerName();
                }

                @Override
                public String getConsignee() {
                    return firstBean.getConsignee();
                }

                @Override
                public String getConsigneeTelNo() {
                    return firstBean.getConsigneeTelNo();
                }

                @Override
                public String getReceiveBy() {
                    return firstBean.getReceivedBy();
                }

                @Override
                public String getTailCompany() {
                    return "BLUE DART";
                }

                @Override
                public String getReferenceNo() {
                    return refAwb;
                }

                @Override
                public String getPackageType() {
                    return null;
                }

                @Override
                public Double getWeight() {
                    return firstBean.getWeight();
                }

                @Override
                public Integer getPincode() {
                    return firstBean.getConsigneePincode();
                }

                @Override
                public String getFlow() {
                    return Waybill.FLOW_FORWARD;
                }

                @Override
                public String getFlowDirection() {
                    return isback ? Waybill.FLOW_DIRECTION_RETURN : Waybill.FLOW_DIRECTION_ONWARD;
                }

                @Override
                public Integer getDispatchCount() {
                    return null;
                }
            });
            List<ScanDetailBean> scanDetailBeanList=null;
            try {
                scanDetailBeanList = firstBean.getScans().getScanDetail();
            }catch (NullPointerException e){ //也要返回
                log.warn("追踪信息为空");
                return body;
            }
            List<Waybill.SavePoint> result = convertScanBean(scanDetailBeanList, body.getFlow(), isback);

            if (isSplit(shipmentBeans)) {
                BluedartResponseBody.InfoBean.ShipmentBean secondBean = shipmentBeans.get(1);
                scanDetailBeanList = secondBean.getScans().getScanDetail();
                result.addAll(convertScanBean(scanDetailBeanList, body.getFlow(), true));
            }
            body.setSavePoints(result);
            return body;
        } catch (Exception e) {
            throw new ConvertException("bd运单转换失败！", e);
        }
    }

    /**
     * 提取ScanBean信息 并且过滤掉 Ignore状态
     */
    private List<Waybill.SavePoint> convertScanBean(List<ScanDetailBean> scanDetailBeans, String flow, boolean isback) {
        return scanDetailBeans.stream()
                .map(scanDetailBean -> {
                    MapResult result = finder.findMapping(scanDetailBean.getScan(), flow, isback);
                    if (ObjectUtils.isEmpty(result) || IGNORE.equals(result.getStatus())) return null;
                    return ConvertUtils.createInfoNode(new SavePointAdpt() {
                        @Override
                        public String getPlace() {
                            return scanDetailBean.getScannedLocation();
                        }

                        @Override
                        public Long getDate() {
                            return DateUtils.parse(scanBeanDateFormat.get(), scanDetailBean.getScanDate() + scanDetailBean.getScanTime());
                        }

                        @Override
                        public String getStatus() {
                            return result.getStatus();
                        }

                        @Override
                        public String getInfo() {
                            return StringUtils.isEmpty(result.getChangedInfo()) ? scanDetailBean.getScan() : result.getChangedInfo();
                        }
                    });
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingLong(Waybill.SavePoint::getDate).reversed())
                .collect(Collectors.toList());
    }

    /**
     * 判断相关单号的准确性
     * 并且返回相关单号
     * 相关单号不存在或者不准确则返回null
     *
     * @param shipmentBean pojo
     * @return 相关单号
     */
    private String isEffective(ShipmentBean shipmentBean) {
        String refAwb = shipmentBean.getRefNo() != null ? shipmentBean.getRefNo() : "";
        Matcher matcher = orderNumMatcher.getRegex().matcher(refAwb);
        return matcher.find() ? matcher.group() : null;
    }

    /**
     * 判断当前是否是拆单
     *
     * @return 是否拆单
     */
    private boolean isSplit(List<ShipmentBean> shipmentBeans) {
        return 2 == shipmentBeans.size();
    }

    @Override
    public void setMapConfigHolder(MapConfigHolder configHolder) {
        this.configHolder = configHolder;
    }

    @Override
    public MapConfigHolder getMapConfigHolder() {
        return configHolder;
    }

    /**
     * 返回当前接受的参数类型
     *
     * @return 当前convert接受的pojo类型
     */
    @Override
    public Class<BluedartResponseBody> getTypeConvertBefore() {
        return BluedartResponseBody.class;
    }

}
