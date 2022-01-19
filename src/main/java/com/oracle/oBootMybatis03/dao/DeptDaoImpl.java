package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Dept;

@Repository
public class DeptDaoImpl implements DeptDao {

	@Autowired
	private SqlSession session;
	
	/**
	 * 부서코드 가져오기
	 */
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		try {
			deptList = session.selectList("tkSelectDept", deptList);
		} catch (Exception e) {
			System.out.println("deptSelect: " + e.getMessage());
		}
		return deptList;
	}
	
}
