package com.cni.converter;

import com.cni.dao.entity.OrderBill;
import com.cni.exception.NeomanException;
import com.cni.httptrack.resp.NeomanResponseBody;
import com.cni.httptrack.resp.NeomanResponseBody.InfoBean.TrackDataBean;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.MapConfigHolder;
import com.cni.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 只需要获取Neoman的创建订单以及发出订单两个信息即可
 */
public class NeomanConverter implements Converter<NeomanResponseBody> {

    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm"));

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

    /**
     * 这个方法只取入库和出库两个节点
     *
     * @param in
     * @return
     */
    @Override
    public OrderBill convert(NeomanResponseBody in) throws NeomanException {
        if (!in.isSuccess()) return null;
        List<TrackDataBean> trackDataBeans = in.getInfo().getTrackData();
        List<OrderBill.InfoNode> result = new ArrayList<>(2);
        try {
            TrackDataBean one = trackDataBeans.get(0);
            result.add(innerConvert(one));
            try {
                TrackDataBean two = trackDataBeans.get(1);
                result.add(innerConvert(two));
            } catch (Exception ignored) {
            }
        } catch (Exception ignored) {
        }
        OrderBill doc = new OrderBill();
        doc.setScans(result);
        return doc;
    }

    @Override
    public void setOrderNumMatcher(OrderNumMatcher matcher) {

    }

    private OrderBill.InfoNode innerConvert(NeomanResponseBody.InfoBean.TrackDataBean trackData) {
        OrderBill.InfoNode item = new OrderBill.InfoNode();
        item.setStatus(findMapping(trackData.getInfo()));
        item.setDate(DateUtils.parse(formatter.get(), trackData.getDateTime()));
        item.setPlace(trackData.getPlace());
        String info = trackData.getInfo();
        if (info.matches("The electrolic infomation has been received on.*"))
            item.setInfo("The electrolic infomation has been received on");
        item.setInfo("");
        return item;
    }

    private String findMapping(String info) {
        if ("Check In Scan".equals(info))
            return "Check In Scan";
        else if ("Shipment Dispatched".equals(info))
            return "Outward Scan";
        else if (info.matches("The electrolic infomation has been received on.*"))
            return "Shipment Created";
        return null;
    }

}
