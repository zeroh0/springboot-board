package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.EmpDept;

@Repository
public class EmpDeptDaoImpl implements EmpDeptDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> listEmpDept = null;
		try {
			listEmpDept = session.selectList("tkListEmpDept");
		} catch (Exception e) {
			System.out.println("listEmpDept: " + e.getMessage());
		}
		return listEmpDept;
	}
}
