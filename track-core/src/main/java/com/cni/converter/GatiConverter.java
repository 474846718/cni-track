package com.cni.converter;

import com.alibaba.fastjson.JSON;
import com.cni.converter.support.ConvertUtils;
import com.cni.converter.support.InfoNodeAdpt;
import com.cni.converter.support.MappingFinder;
import com.cni.converter.support.OrderBillAdpt;
import com.cni.dao.entity.OrderBill;
import com.cni.exception.ConvertException;
import com.cni.exception.OrderNotFoundException;
import com.cni.httptrack.resp.GatiResponseBody;
import com.cni.httptrack.resp.GatiResponseBody.GatiInnerBean.ListBean;
import com.cni.httptrack.support.Channels;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.MapConfigHolder;
import com.cni.statemap.MapResult;
import com.cni.statemap.neoman.NeomanConfigHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.cni.converter.support.ConvertUtils.IGNORE;

/**
 * 将Gati公司的数据转成自己的格式
 * <p>
 * Created by CNI on 2018/1/18.
 */
public class GatiConverter implements Converter<GatiResponseBody> {


    private MappingFinder finder;

    public GatiConverter(NeomanConfigHolder configHolder) {

        finder = new MappingFinder(configHolder, Channels.GATI);
    }

    @Override
    public void setMapConfigHolder(MapConfigHolder configHolder) {

    }

    @Override
    public MapConfigHolder getMapConfigHolder() {
        return null;
    }

    @Override
    public Class<GatiResponseBody> getTypeConvertBefore() {
        return GatiResponseBody.class;
    }

    @Override
    public OrderBill convert(GatiResponseBody in) throws OrderNotFoundException, ConvertException {
        if (ObjectUtils.isEmpty(in) || !in.isSuccess() || ObjectUtils.isEmpty(in.getInfo()) || ObjectUtils.isEmpty(in.getInfo().getList()))
            throw new OrderNotFoundException("查无此单" + JSON.toJSONString(in));

        return handleBody(in.getInfo().getList().get(0));
    }

    @Override
    public void setOrderNumMatcher(OrderNumMatcher matcher) {

    }

    private OrderBill handleBody(ListBean listBean) {
        try {
            OrderBill body = ConvertUtils.createOrderBill(new OrderBillAdpt() {
                @Override
                public String getNumber() {
                    return listBean.getTrackNumber();
                }

                @Override
                public String getOrigin() {
                    return listBean.getData().getEmsInfo().getFrom();
                }

                @Override
                public String getDestination() {
                    return listBean.getData().getEmsInfo().getDes();
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
                    return listBean.getData().getEmsInfo().getSign();
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
                    return "GATI";
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
                    return null;
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
            List<OrderBill.InfoNode> infoNodes = listBean.getData().getTrackData().stream()
                    .map(trackDataBean -> {
                        MapResult result = finder.findMapping(trackDataBean.getInfo(), body.getFlow());
                        if (ObjectUtils.isEmpty(result) || IGNORE.equals(result.getStatus())) return null;
                        return ConvertUtils.createInfoNode(new InfoNodeAdpt() {
                            @Override
                            public String getPlace() {
                                return trackDataBean.getPlace();
                            }

                            @Override
                            public Long getDate() {
                                return new Date(trackDataBean.getCompareDateTime()).toInstant().toEpochMilli();
                            }

                            @Override
                            public String getStatus() {
                                return result.getStatus();
                            }

                            @Override
                            public String getInfo() {
                                return StringUtils.isEmpty(result.getChangedInfo()) ? trackDataBean.getInfo() : result.getChangedInfo();
                            }
                        });
                    })
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparingLong(OrderBill.InfoNode::getDate).reversed())
                    .collect(Collectors.toList());
            body.setInfoNodes(infoNodes);
            return body;
        } catch (Exception e) {
            throw new ConvertException("gati运单转换失败", e);
        }
    }

}
