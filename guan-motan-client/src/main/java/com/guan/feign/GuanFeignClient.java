package com.guan.feign;

import com.guan.entity.Guan;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
@FeignClient("guan-motan-server")
public interface GuanFeignClient {
    @RequestMapping(value = "/guan",method = RequestMethod.GET)
    public String findByName();

    @RequestMapping(value = "/getAllGuan",method = RequestMethod.GET)
    public List<Guan> getAllGuan();

    @RequestMapping(value = "/getConsulConfig",method = RequestMethod.GET)
    public String getConsulConfig();

}
