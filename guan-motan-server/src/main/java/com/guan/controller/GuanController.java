package com.guan.controller;

import com.guan.config.ConsulConfig;

import com.guan.entity.Guan;
import com.guan.repository.GuanRepository;
import com.guan.rpc.service.GuanService;
import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Lazy
public class GuanController {
    private static AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    GuanRepository guanRepository;

    @Autowired
    ConsulConfig consulConfig;

    @MotanReferer
    GuanService guanService;

    @GetMapping("/by/{name}")
    public Guan findByName(@PathVariable String name){
        return guanRepository.findByName(name);
    }

    @GetMapping("/getAllGuan")
    public List<Guan> getAllGuan() throws InterruptedException {
        List<Guan> allGuan = guanRepository.getAllGuan();
        return allGuan;
    }

    @GetMapping("/getConsulConfig")
    public String getConsulConfig() throws InterruptedException {
        return consulConfig.getName();
    }

    @GetMapping("/guans")
    public List<Guan> guans() throws InterruptedException {
        return guanService.findAll();
    }

    @GetMapping("say/{name}")
    public String sayHello(@PathVariable String name) throws Exception{
        return guanService.sayHello(name);
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
}
