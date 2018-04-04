package com.cni.controller;

import com.cni.service.MappingHolderService;
import com.cni.statemap.MapRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 管理系统配置信息，查单时的状态映射。
 */

@RestController
public class MappingManageController {

    private final MappingHolderService service;

    @Autowired
    public MappingManageController(MappingHolderService service) {
        this.service = service;
    }

    @PostMapping("reloadConfig")
    public String reloadConfig(@RequestBody List<MapRow> mapRows) {
        service.reloadHolder(mapRows);
        return service.checkConf();
    }

    @GetMapping("reloadConfig")
    public String reloadConfig() {
        return Boolean.toString(service.reloadHolder());
    }

    @PostMapping("addConfig")
    public String addConfig(@RequestBody List<MapRow> mapRows) {
        service.addConfig(mapRows);
        return "true";
    }

    @PostMapping("removeConfig")
    public String removeConfig(@RequestBody List<MapRow> mapRows) {
        service.removeConfig(mapRows);
        return "true";
    }

    @GetMapping("removeConfig")
    public String removeConfig(@RequestParam("statusMatchingId") long statusMatchingId) {
        service.removeConfig(statusMatchingId);
        return service.checkConf();
    }

    @GetMapping("checkConf")
    public String checkConf() {
        return service.checkConf();
    }
}
