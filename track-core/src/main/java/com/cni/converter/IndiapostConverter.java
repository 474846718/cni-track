package com.cni.converter;

import com.cni.converter.support.ConvertUtils;
import com.cni.converter.support.SavePointAdpt;
import com.cni.converter.support.MappingFinder;
import com.cni.converter.support.WaybillAdpt;
import com.cni.dao.entity.Waybill;
import com.cni.exception.ConvertException;
import com.cni.exception.OrderNotFoundException;
import com.cni.httptrack.resp.IndiapostResponseBody;
import com.cni.httptrack.support.Channels;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.MapConfigHolder;
import com.cni.statemap.MapResult;
import com.cni.statemap.neoman.NeomanConfigHolder;
import com.cni.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.cni.converter.support.ConvertUtils.IGNORE;

/**
 * 将Indiapost公司的数据转成自己的格式
 * <p>
 * Created by CNI on 2018/1/18.
 */


public class IndiapostConverter implements Converter<IndiapostResponseBody> {

    private static Log log = LogFactory.getLog(IndiapostConverter.class);
    private static final ThreadLocal<SimpleDateFormat> scanBeanDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd/MM/yyyyHH:mm:ss"));
    private MappingFinder finder;


    public IndiapostConverter(NeomanConfigHolder configHolder) {
        finder = new MappingFinder(configHolder, Channels.INPT);
    }

    @Override
    public void setMapConfigHolder(MapConfigHolder configHolder) {

    }

    @Override
    public MapConfigHolder getMapConfigHolder() {
        return null;
    }

    @Override
    public Class<IndiapostResponseBody> getTypeConvertBefore() {
        return IndiapostResponseBody.class;
    }

    @Override
    public Waybill convert(IndiapostResponseBody in) throws OrderNotFoundException, ConvertException {
        if (in == null || !in.isSuccess() || in.getInfo() == null || in.getInfo().getDetails() == null)
            throw new OrderNotFoundException("查无此单" + in);
        return handleBody(in);
    }

    @Override
    public void setOrderNumMatcher(OrderNumMatcher matcher) {

    }

    private Waybill handleBody(IndiapostResponseBody in) {
        try {
            IndiapostResponseBody.InfoBean infoBean = in.getInfo();
            Waybill body = ConvertUtils.createOrderBill(new WaybillAdpt() {

                @Override
                public String getNumber() {
                    return infoBean.getAwb();
                }

                @Override
                public String getOrigin() {
                    return infoBean.getBookAt();
                }

                @Override
                public String getDestination() {
                    return infoBean.getDeliveredAt();
                }

                @Override
                public Long getEstimatedDate() {
                    return null;
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
                    return "INDIA POST";
                }

                @Override
                public String getReferenceNo() {
                    return null;
                }

                @Override
                public String getPackageType() {
                    return null;
                }

                @Override
                public Double getWeight() {
                    return null;
                }

                @Override
                public Integer getPincode() {
                    return Integer.parseInt(infoBean.getDestinationPincode());
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
                    return infoBean.getTryTimes() > 0 ? infoBean.getTryTimes() : null;
                }
            });

            List<Waybill.SavePoint> mySavePoints = infoBean.getDetails().stream()
                    .map(detailsBean -> {
                        MapResult result = finder.findMapping(detailsBean.getEvent(), body.getFlow());
                        if (ObjectUtils.isEmpty(result) || IGNORE.equals(result.getStatus())) return null;
                        return ConvertUtils.createInfoNode(new SavePointAdpt() {
                            @Override
                            public String getPlace() {
                                return detailsBean.getOffice();
                            }

                            @Override
                            public Long getDate() {
                                return DateUtils.parse(scanBeanDateFormat.get(), detailsBean.getDate() + detailsBean.getTime());
                            }

                            @Override
                            public String getStatus() {
                                return result.getStatus();
                            }

                            @Override
                            public String getInfo() {
                                return detailsBean.getEvent();
                            }
                        });
                    })
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparingLong(Waybill.SavePoint::getDate).reversed())
                    .collect(Collectors.toList());
            body.setScans(mySavePoints);
            return body;
        } catch (Exception e) {
            throw new ConvertException("ind运单转换失败", e);
        }
    }


}
