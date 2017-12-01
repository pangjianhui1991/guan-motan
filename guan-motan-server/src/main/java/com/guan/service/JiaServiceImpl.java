package com.guan.service;

import com.guan.rpc.service.JiaService;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

@MotanService
public class JiaServiceImpl implements JiaService {
    @Override
    public String jia() {
        return "guanjiagui";
    }
}
