package com.guan.rpc.service;

import com.guan.entity.Guan;

import java.util.List;

public interface GuanService {

    public String sayHello(String str) throws InterruptedException;

    public List<Guan> findAll() throws InterruptedException;

    public List<Guan> insertAll();

    public Guan findByName(String name);

    public List<Guan> findByNameLike(String name);
}
