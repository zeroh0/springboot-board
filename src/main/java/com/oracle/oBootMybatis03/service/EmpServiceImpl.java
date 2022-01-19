package com.oracle.oBootMybatis03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.EmpDao;
import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	
	@Override
	public int total() {
		System.out.println("EmpServiceImpl Start total...");
		int totCnt = ed.total();
		System.out.println("EmpServiceImpl total totCnt->" + totCnt);
		return totCnt;
	}

	/**
	 * 게시글 목록 조회
	 */
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listEmp Start...");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->" + empList.size());
		return empList;
	}

	/**
	 * 게시글 정보 조회
	 */
	@Override
	public Emp detail(int empno) {
		System.out.println("_service: " + empno);
		Emp emp = ed.detail(empno);
		return emp;
	}

	/**
	 * 게시글 수정
	 */
	@Override
	public int update(Emp emp) {
		int kkk = 0;
		kkk = ed.update(emp);
		return kkk;
	}

	/**
	 * 관리자 가져오기
	 */
	@Override
	public List<Emp> listManager() {
		List<Emp> empList = ed.listManager();
		return empList;
	}

	/**
	 * 게시글 작성
	 */
	@Override
	public int insert(Emp emp) {
		int result = ed.insert(emp);
		return result;
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public int delete(int empno) {
		int result = ed.delete(empno);
		return result;
	} 
	
}
