package com.guan.dao;

import com.guan.entity.Guan;
import com.guan.repository.GuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
@Service
public class GuanDaoImpl extends BaseDaoImpl<Guan> implements GuanDao {
    @Resource
    GuanRepository guanRepository;

    @Autowired
    protected EntityManagerFactory sessionFactory;

    @Override
    public boolean saveBatch(List<Guan> list) {
        int batchSize = 50;
        int i = 0;

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        boolean flag = false;
        try {
            entityManager = sessionFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            for (Guan guan : list){
                entityManager.persist( guan );
                i++;
                if (i % batchSize == 0) {
                    entityManager.flush();
                    entityManager.clear();
                    transaction.commit();
                    transaction.begin();
                }
            }
            transaction.commit();
            flag = true;
        } catch (RuntimeException e) {
            if ( transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return flag;
    }

}
