package com.guan.repository;

import com.guan.entity.Guan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GuanRepository extends JpaRepository<Guan,Integer> {

    @Query(value="SELECT * from guan",nativeQuery=true)
    public List<Guan> getAllGuan();

   public Guan findByName(String name);

    @Query(value="SELECT * from guan where name like %?1% ",nativeQuery=true)
   public List<Guan> findByNameLike(String name);

}
