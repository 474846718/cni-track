package com.cni.controller;

import com.cni.dao.entity.Waybill;
import com.cni.httptrack.resp.CommonResponseBody;
import com.cni.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600) //标记为支持跨域
@RestController
public class CniCoreTrackController {

    private final TrackService trackService;

    @Autowired
    public CniCoreTrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/neoman_track")
    public Object getWaybills(@RequestParam("awb") String awb) {
        try {
            if (StringUtils.isEmpty(awb))
                return CommonResponseBody.error("400", "请输入单号");
            List<String> nums = new ArrayList<>(Arrays.asList(awb.split(",")));
            if (CollectionUtils.isEmpty(nums))
                return CommonResponseBody.error("400", "请输入单号");
            List<Waybill> results = trackService.trackOrders(nums);
            return CollectionUtils.isEmpty(results) ? CommonResponseBody.error("400", "列表为空", results) : CommonResponseBody.success("查询完成", results);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponseBody.error("500", e.getMessage(), awb);
        }
    }
}
