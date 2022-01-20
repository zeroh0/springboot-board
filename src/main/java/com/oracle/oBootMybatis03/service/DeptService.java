package com.oracle.oBootMybatis03.service;

import java.util.HashMap;
import java.util.List;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;

public interface DeptService {

	public List<Dept> deptSelect();
	void insertDept(DeptVO deptVO);
	public void selListDept(HashMap<String, Object> map);
}
