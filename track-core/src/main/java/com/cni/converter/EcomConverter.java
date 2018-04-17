package com.cni.converter;

import com.alibaba.fastjson.JSON;
import com.cni.converter.support.ConvertUtils;
import com.cni.converter.support.InfoNodeAdpt;
import com.cni.converter.support.MappingFinder;
import com.cni.converter.support.OrderBillAdpt;
import com.cni.dao.entity.Waybill;
import com.cni.exception.ConvertException;
import com.cni.exception.OrderNotFoundException;
import com.cni.httptrack.resp.EcomResponseBody;
import com.cni.httptrack.resp.EcomResponseBody.InfoBean.ListBean;
import com.cni.httptrack.support.Channels;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.MapConfigHolder;
import com.cni.statemap.MapResult;
import com.cni.statemap.neoman.NeomanConfigHolder;
import com.cni.util.DateUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.cni.converter.support.ConvertUtils.IGNORE;

/**
 * 将Ecom公司的数据转成自己的格式
 * <p>
 * Created by CNI on 2018/1/18.
 */

public class EcomConverter implements Converter<EcomResponseBody> {

    private static final ThreadLocal<SimpleDateFormat> estimatedDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH));

    private OrderNumMatcher ecomMatcher;

    private MappingFinder finder;


    public EcomConverter(NeomanConfigHolder configHolder) {
        finder = new MappingFinder(configHolder, Channels.ECOM);
    }

    @Override
    public void setMapConfigHolder(MapConfigHolder configHolder) {

    }

    @Override
    public MapConfigHolder getMapConfigHolder() {
        return null;
    }

    @Override
    public Class<EcomResponseBody> getTypeConvertBefore() {
        return EcomResponseBody.class;
    }

    @Override
    public Waybill convert(EcomResponseBody in) throws OrderNotFoundException, ConvertException {
        if (in == null || !in.isSuccess() || in.getInfo() == null
                || CollectionUtils.isEmpty(in.getInfo().getList())) {
            throw new OrderNotFoundException("查无此单" + JSON.toJSONString(in));
        }
        return handleBody(in.getInfo().getList().get(0));
    }

    @Override
    public void setOrderNumMatcher(OrderNumMatcher matcher) {
        this.ecomMatcher = matcher;
    }

    private Waybill handleBody(ListBean listBean) {
        try {
            Waybill body = ConvertUtils.createOrderBill(new OrderBillAdpt() {
                @Override
                public String getNumber() {
                    return listBean.getAwb_number() + "";
                }

                @Override
                public String getOrigin() {
                    return listBean.getOrigin();
                }

                @Override
                public String getDestination() {
                    return listBean.getDestination();
                }

                @Override
                public Long getEstimatedDate() {
                    return DateUtils.parse(estimatedDateFormat.get(), listBean.getExpected_date());
                }

                @Override
                public String getCustomer() {
                    return listBean.getCustomer();
                }

                @Override
                public String getConsignee() {
                    return listBean.getConsignee();
                }

                @Override
                public String getConsigneeTelNo() {
                    return null;
                }

                @Override
                public String getReceiveBy() {
                    return listBean.getReceiver();
                }

                @Override
                public String getTailCompany() {
                    return "ECOM EXPRESS";
                }

                @Override
                public String getReferenceNo() {
                    String refAwb = listBean.getRef_awb();
                    ecomMatcher = new OrderNumMatcher("2\\d{8}");
                    return ecomMatcher.match(refAwb) ? refAwb : null;
                }

                @Override
                public String getPackageType() {
                    return null;
                }

                @Override
                public Double getWeight() {
                    return listBean.getActual_weight();
                }

                @Override
                public Integer getPincode() {
                    return Integer.parseInt(listBean.getPincode());
                }

                @Override
                public String getFlow() {
                    return "FORWARD";
                }

                @Override
                public String getFlowDirection() {
                    return "ONWARD";
                }

                @Override
                public Integer getDispatchCount() {
                    return null;
                }
            });
            List<Waybill.SavePoint> savePoints = listBean.getScans().stream()
                    .map(scansBean -> {
                        MapResult result = finder.findMapping(scansBean.getStatus(), body.getFlow());
                        if (ObjectUtils.isEmpty(result) || IGNORE.equals(result.getStatus())) return null;
                        return ConvertUtils.createInfoNode(new InfoNodeAdpt() {
                            @Override
                            public String getPlace() {
                                return scansBean.getLocation_city();
                            }

                            @Override
                            public Long getDate() {
                                return new Date(scansBean.getUpdated_on()).toInstant().toEpochMilli();
                            }

                            @Override
                            public String getStatus() {
                                return result.getStatus();
                            }

                            @Override
                            public String getInfo() {
                                String info = scansBean.getStatus();
                                info = StringUtils.isEmpty(info) ? scansBean.getReason_code() : info;

                                String changedInfo = result.getChangedInfo();
                                return StringUtils.isEmpty(changedInfo) ? info : changedInfo;
                            }
                        });
                    })
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparingLong(Waybill.SavePoint::getDate).reversed())
                    .collect(Collectors.toList());

            body.setSavePoints(savePoints);
            return body;
        } catch (Exception e) {
            throw new ConvertException("ecom运单转换异常", e);
        }
    }


}
