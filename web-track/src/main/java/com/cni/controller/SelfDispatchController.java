package com.cni.controller;

import com.cni.httptrack.SelfDispatchNumHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("selfDispatch")
@RestController
public class SelfDispatchController {
    @Autowired
    SelfDispatchNumHolder selfDispatchNumHolder;

    @GetMapping("getAll")
    public Set<String> getAll(){
        return selfDispatchNumHolder.getAll();
    }

    @PostMapping("setAll")
    public void setAll(@RequestBody String[] orderNums){
        Set<String> set= Arrays.stream(orderNums).filter(num-> !StringUtils.isEmpty(num)).collect(Collectors.toSet());
        System.out.println("收到自派送单号"+set);
        selfDispatchNumHolder.reset(set);
    }

}
