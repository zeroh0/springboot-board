package com.oracle.oBootMybatis03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.DeptDao;
import com.oracle.oBootMybatis03.model.Dept;

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

}
