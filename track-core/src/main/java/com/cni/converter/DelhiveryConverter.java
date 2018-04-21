package com.cni.converter;

import com.cni.converter.support.ConvertUtils;
import com.cni.converter.support.SavePointAdpt;
import com.cni.converter.support.MappingFinder;
import com.cni.converter.support.WaybillAdpt;
import com.cni.dao.entity.Waybill;
import com.cni.exception.ConvertException;
import com.cni.exception.OrderNotFoundException;
import com.cni.httptrack.resp.DelhiveryResponseBody;
import com.cni.httptrack.support.Channels;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.MapConfigHolder;
import com.cni.statemap.MapResult;
import com.cni.statemap.neoman.NeomanConfigHolder;
import com.cni.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.cni.converter.support.ConvertUtils.IGNORE;

/**
 * 将Delhivery公司的数据转成自己的数据格式
 * <p>
 * Created by CNI on 2018/1/18.
 */
public class DelhiveryConverter implements Converter<DelhiveryResponseBody> {

    private static final Log log = LogFactory.getLog(DelhiveryConverter.class);

    private static final String DATE_PATTERN = "yyyy-MM-d'T'HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat(DATE_PATTERN));

    private MappingFinder finder;

    private MapConfigHolder configHolder;

    public DelhiveryConverter(NeomanConfigHolder configHolder) {
        this.configHolder = configHolder;
        finder = new MappingFinder(this.configHolder, Channels.DELHIVERY);
        finder.setStrategy((currStatus, optional) -> {
            String delhiveryState = (String) optional[0];
            String state = null;
            if ("Consignment received".equals(delhiveryState)) {
                if ("IN_TRANSIT".equals(currStatus))
                    state = "In Transit";
                else if ("RETURNED".equals(currStatus))
                    state = "Returned In Transit";
            }
            return new MapResult(state, null);
        });
    }

    private static Long parseByLocalDateTime(String strDateTime) {
        if (ObjectUtils.isEmpty(strDateTime))
            return null;
        String trimDate = strDateTime.substring(0, DATE_PATTERN.length() - 2);
        return DateUtils.parse(dateFormat.get(), trimDate);
    }

    @Override
    public void setMapConfigHolder(MapConfigHolder configHolder) {
        this.configHolder = configHolder;
    }

    @Override
    public MapConfigHolder getMapConfigHolder() {
        return configHolder;
    }

    @Override
    public Class<DelhiveryResponseBody> getTypeConvertBefore() {
        return DelhiveryResponseBody.class;
    }

    @Override
    public Waybill convert(DelhiveryResponseBody in) throws OrderNotFoundException, ConvertException {
        if (in == null || !in.isSuccess() || in.getInfo() == null || in.getInfo().getData() == null)
            throw new OrderNotFoundException("查无此单" + in);
        return handleBody(in.getInfo().getData().get(0));
    }

    @Override
    public void setOrderNumMatcher(OrderNumMatcher matcher) {

    }

    private Waybill handleBody(DelhiveryResponseBody.InfoBean.DataBean dataBean) {
        if (CollectionUtils.isEmpty(dataBean.getScans()))
            throw new OrderNotFoundException("查无此单");

        try {
            Waybill body = ConvertUtils.createOrderBill(new WaybillAdpt() {

                @Override
                public String getNumber() {
                    return dataBean.getAwb();
                }

                @Override
                public String getOrigin() {
                    return null;
                }

                @Override
                public String getDestination() {
                    return dataBean.getDestination();
                }

                @Override
                public Long getEstimatedDate() {
                    return parseByLocalDateTime(dataBean.getEstimatedDate());
                }

                @Override
                public String getCustomer() {
                    return null;
                }

                @Override
                public String getConsignee() {
                    return null;
                }

                @Override
                public String getConsigneeTelNo() {
                    return null;
                }

                @Override
                public String getReceiveBy() {
                    return null;
                }

                @Override
                public String getTailCompany() {
                    return "DELHIVERY";
                }

                @Override
                public String getReferenceNo() {
                    return null;
                }

                @Override
                public String getPackageType() {
                    return dataBean.getPackageType();
                }

                @Override
                public Double getWeight() {
                    return null;
                }

                @Override
                public Integer getPincode() {
                    return null;
                }

                @Override
                public String getFlow() {
                    return dataBean.getFlow();
                }

                @Override
                public String getFlowDirection() {
                    return dataBean.getFlowDirection();
                }

                @Override
                public Integer getDispatchCount() {
                    return dataBean.getDispatchCount();
                }
            });
            List<Waybill.SavePoint> mySavePoints = dataBean.getScans().stream()
                    .map(scansBean -> {
                        MapResult result = finder.findMapping(scansBean.getInstructions(), body.getFlow(), scansBean.getStatus());
                        if (ObjectUtils.isEmpty(result) || IGNORE.equals(result.getStatus())) return null; //TODO 检查
                        return ConvertUtils.createInfoNode(new SavePointAdpt() {
                            @Override
                            public String getPlace() {
                                return scansBean.getScannedLocation();
                            }

                            @Override
                            public Long getDate() {
                                return DateUtils.parse(dateFormat.get(), scansBean.getScanDateTime());
                            }

                            @Override
                            public String getStatus() {
                                return result.getStatus();
                            }

                            @Override
                            public String getInfo() {
                                return StringUtils.isEmpty(result.getChangedInfo()) ? scansBean.getInstructions() : result.getChangedInfo();
                            }
                        });
                    })
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparingLong(Waybill.SavePoint::getDate).reversed())
                    .collect(Collectors.toList());
            body.setSavePoints(mySavePoints);
            return body;
        } catch (Exception e) {
            throw new ConvertException("del运单转换失败", e);
        }


    }


}
