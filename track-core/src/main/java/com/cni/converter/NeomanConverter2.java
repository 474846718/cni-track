package com.cni.converter;

import com.cni.converter.support.ConvertUtils;
import com.cni.converter.support.InfoNodeAdpt;
import com.cni.converter.support.MappingFinder;
import com.cni.converter.support.OrderBillAdpt;
import com.cni.dao.entity.Waybill;
import com.cni.exception.ConvertException;
import com.cni.exception.OrderNotFoundException;
import com.cni.httptrack.resp.NeomanResponseBody;
import com.cni.httptrack.resp.NeomanResponseBody.InfoBean.TrackDataBean;
import com.cni.httptrack.support.Channels;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.MapConfigHolder;
import com.cni.statemap.MapResult;
import com.cni.util.DateUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.cni.converter.support.ConvertUtils.IGNORE;

/**
 * TODO 以后可以删除这个类
 * 从钮门获取自拍件
 * 获取钮门全部信息
 */
public class NeomanConverter2 implements Converter<NeomanResponseBody> {


    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";//2018-02-27 09:32
    private static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat(DATE_PATTERN));

    private MappingFinder finder;

    public NeomanConverter2(MapConfigHolder configHolder) {
        finder = new MappingFinder(configHolder, Channels.DELHIVERY);
    }

    @Override
    public void setMapConfigHolder(MapConfigHolder configHolder) {

    }

    @Override
    public MapConfigHolder getMapConfigHolder() {
        return null;
    }

    @Override
    public Class<NeomanResponseBody> getTypeConvertBefore() {
        return NeomanResponseBody.class;
    }

    @Override
    public Waybill convert(NeomanResponseBody in) throws OrderNotFoundException, ConvertException {

        if (ObjectUtils.isEmpty(in) || !in.isSuccess()
                || ObjectUtils.isEmpty(in.getInfo())
                || ObjectUtils.isEmpty(in.getInfo().getEmsInfo())
                || CollectionUtils.isEmpty(in.getInfo().getTrackData())) return null;

        NeomanResponseBody.InfoBean.EmsInfoBean emsInfoBean = in.getInfo().getEmsInfo();
        List<TrackDataBean> trackDataBeans = in.getInfo().getTrackData();
        Waybill doc = ConvertUtils.createOrderBill(new OrderBillAdpt() {
            @Override
            public String getNumber() {
                return emsInfoBean.getNumber();
            }

            @Override
            public String getOrigin() {
                return null;
            }

            @Override
            public String getDestination() {
                return emsInfoBean.getDestination();
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
                return null;
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
                return Double.parseDouble(emsInfoBean.getWeight());
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
        Pattern pattern = Pattern.compile("\\[.*]");
        List<Waybill.SavePoint> mySavePoints = trackDataBeans.stream()
                .map(trackData -> {
                    String subString = pattern.matcher(trackData.getInfo()).replaceAll("");
                    MapResult result = finder.findMapping(subString, doc.getFlow());
                    if (ObjectUtils.isEmpty(result) || IGNORE.equals(result.getStatus())) return null;
                    return ConvertUtils.createInfoNode(new InfoNodeAdpt() {
                        @Override
                        public String getPlace() {
                            return trackData.getPlace();
                        }

                        @Override
                        public Long getDate() {
                            return parseByLocalDateTime(trackData.getDateTime());
                        }

                        @Override
                        public String getStatus() {
                            return result.getStatus();
                        }

                        @Override
                        public String getInfo() {
                            return subString;
                        }
                    });
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingLong(Waybill.SavePoint::getDate).reversed())
                .collect(Collectors.toList());
// 去除入库节点前的地点信息
        int index = findCheckInScanIdx(mySavePoints);
        if (index > -1)
            mySavePoints.stream()
                    .skip(index + 1)
                    .forEach(scan -> scan.setPlace(""));
        doc.setSavePoints(mySavePoints);
        return doc;
    }

    /**
     * 在list中获取入库节点的索引位置
     * 倒序查找
     *
     * @return 返回索引位置找不到返回-1
     */
    private int findCheckInScanIdx(List<Waybill.SavePoint> scans) {
        for (int i = scans.size() - 1; i > -1; i--) {
            String status = scans.get(i).getStatus();
            if (!StringUtils.isEmpty(status) && status.equalsIgnoreCase("Check In Scan"))
                return i;
        }
        return -1;
    }

    private static Long parseByLocalDateTime(String strDateTime) {
        if (ObjectUtils.isEmpty(strDateTime))
            return null;
        return DateUtils.parse(dateFormat.get(), strDateTime);
    }

    @Override
    public void setOrderNumMatcher(OrderNumMatcher matcher) {
    }


}
