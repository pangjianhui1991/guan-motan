package com.guan.service;


import com.guan.dao.GuanDao;
import com.guan.entity.Guan;
import com.guan.repository.GuanRepository;
import com.guan.rpc.service.GuanService;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@MotanService
public class GuanServiceImpl implements GuanService {

    private static AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    GuanRepository guanRepository;

    @Autowired
    GuanDao guanDao;



    @Override
    public String sayHello(String str) throws InterruptedException {
        if(str.equals("guan")){
            System.out.println("guan进来睡了");
            Thread.sleep(10000l);
            System.out.println("guan睡完了");
        }
        return "hello"+str;
    }

    @Override
    public List<Guan> findAll() throws InterruptedException {
        List<Guan> list = guanRepository.getAllGuan();
        return  list;
    }

    @Override
    @Transactional
    public List<Guan> insertAll() {
        List<Guan> list= new ArrayList<>();
        for(int i = 1; i < 1000; i ++){
            Guan guan = new Guan();
            guan.setName("guan"+i);
            guan.setAge(i);
            list.add(guan);
        }
        boolean nnn = guanDao.saveBatch(list);
        System.out.println("保存结果--------------------------------"+nnn);
        //guanDao.saveBatch(list);


        return list;
    }

    @Override
    public Guan findByName(String name) {
        return guanRepository.findByName(name);
    }

    @Override
    public List<Guan> findByNameLike(String name) {
        return guanRepository.findByNameLike(name);
    }
}
