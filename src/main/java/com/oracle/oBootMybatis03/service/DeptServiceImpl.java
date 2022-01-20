package com.oracle.oBootMybatis03.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.DeptDao;
import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDao dd;

	/**
	 * 부서코드 가져오기
	 */
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = dd.deptSelect();
		return deptList;
	}
	
	/**
	 * 
	 */
	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("DeptServiceImpl insertDept");
		dd.insertDept(deptVO);
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("DeptServiceImpl selListDept");
		dd.selListDept(map);
	} 
	
}
