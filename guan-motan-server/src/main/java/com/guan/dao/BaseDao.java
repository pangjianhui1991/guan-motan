package com.guan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseDao<T> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

	public int execute(String sql, List<Object> params)throws Exception ;

	public int insert(String tableName, Map<String, Object> row)throws Exception ;
	public int delete(String tableName, Map<String, Object> whereCond)throws Exception ;
	public int update(String tableName, Map<String, Object> updateFields, Map<String, Object> whereCond)throws Exception ;
	public int insert(T vo) throws Exception;
	public int update(T vo) throws Exception;
}
