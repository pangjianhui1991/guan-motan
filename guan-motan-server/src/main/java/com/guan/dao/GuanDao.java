package com.guan.dao;

import com.guan.entity.Guan;

import java.util.List;

public interface GuanDao extends BaseDao<Guan>{

    public boolean saveBatch(List<Guan> list);

}
