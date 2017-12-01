package com.guan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{

	@Autowired
	protected EntityManagerFactory sessionFactory;
	@Autowired
	protected EntityManager session;

	@Override
	public Iterable<T> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> S save(S entity) {
		boolean isContainTransaction=session.isJoinedToTransaction();
		if(!isContainTransaction){//没有spring事务控制，采用手工控制
			EntityManager session = sessionFactory.createEntityManager();
			try{
				session.getTransaction().begin();
				session.persist(entity);
				session.getTransaction().commit();
			}catch(Exception ex){
				session.getTransaction().rollback();
				throw ex;
			}finally{
				if(session!=null){
					session.close();
				}
			}
		}else{
			session.persist(entity);
		}
		return entity;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findOne(Long id) {
		throw new RuntimeException("this method must be impl!");
	}

	@Override
	public boolean exists(Long id) {
		return findOne(id)!=null;
	}

	@Override
	public Iterable<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<T> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		T entity=findOne(id);
		if(entity!=null){
			delete(entity);
		}
	}

	@Override
	public void delete(T entity) {
		boolean isContainTransaction=session.isJoinedToTransaction();
		if(!isContainTransaction){//没有spring事务控制，采用手工控制
			EntityManager session = sessionFactory.createEntityManager();
			try{
				session.getTransaction().begin();
				session.remove(entity);
				session.getTransaction().commit();
			}catch(Exception ex){
				session.getTransaction().rollback();
				throw ex;
			}finally{
				if(session!=null){
					session.close();
				}
			}
		}else{
			session.persist(entity);
		}
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T findOne(Specification<T> spec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		return new ArrayList<T>();
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		return new ArrayList<T>();
	}

	@Override
	public long count(Specification<T> spec) {
		return findAll(spec).size();
	}

	@Override
	public int execute(String sql, List<Object> params) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(String tableName, Map<String, Object> row) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String tableName, Map<String, Object> whereCond) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String tableName, Map<String, Object> updateFields, Map<String, Object> whereCond)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(T vo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(T vo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
