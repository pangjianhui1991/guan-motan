package com.guan.controller;


import com.guan.entity.Guan;
import com.guan.feign.GuanFeignClient;

import com.guan.rpc.service.GuanService;
import com.guan.rpc.service.JiaService;
import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GaunController {
    @MotanReferer
    GuanService guanService;

    @MotanReferer
    JiaService jiaService;

    @Autowired
    GuanFeignClient guanFeignClient;

    @GetMapping("say/{name}")
    public String sayHello(@PathVariable String name) throws Exception{
        return guanService.sayHello(name);
    }
    @GetMapping("/guans")
    public List<Guan> guans() throws InterruptedException {
        return guanService.findAll();
    }

    @GetMapping("/jia")
    public String jia(){
        return jiaService.jia();
    }


    @GetMapping("/insert")
    public List<Guan> insert(){
        return guanService.insertAll();
    }

    @GetMapping("/byName/{name}")
    public Guan insert(@PathVariable String name){
        return guanService.findByName(name);
    }

    @GetMapping("/byNameLike/{name}")
    public List<Guan> byNameLike(@PathVariable String name){
        return guanService.findByNameLike(name);
    }

    @GetMapping("/fegin")
    public String fegin(){
        return guanFeignClient.findByName();
    }

    @ApiOperation(value="fegin方式查询全部Guan", notes="查询全部Guan")
    @ApiImplicitParam(name = "guans", value = "查询全部Guan")
    @GetMapping("/feginGetAllGuan")
    public List<Guan> feginGetAllGuan(){
        return guanFeignClient.getAllGuan();
    }

    @GetMapping("/getConsulConfig")
    public String getConsulConfig(){
        return guanFeignClient.getConsulConfig();
    }



}
