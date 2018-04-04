package com.cni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 单页查询
 * <p>
 * Created by CNI on 2018/2/1.
 */
@Controller
public class TrackPageController {

    @RequestMapping(value = "/package/{number}")
    public String getSingleWaybillPage(@PathVariable(name = "number") String number) {
        return "forward:/index.html?" + number;
    }
}
