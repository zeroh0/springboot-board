package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
//@Repository를 하면 bean을 안 만들어도 됨.
@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public int total() {
		int tot = 0;
		System.out.println("EmpDaoImpl Start total...");
		
		//Result => 14
		try { 
			tot = session.selectOne("tkEmpTotal"); //태광 emp테이블의 total
			System.out.println("EmpDaoImpl total tot->" + tot); //selectOne ->  한 건 조회
		}catch (Exception e) {
			System.out.println("EmpDaoImpl total Exception->" + e.getMessage());
		}
		return tot;
	}

	/**
	 * 게시글 목록 조회
	 */
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp Start...");
		try {
			//	Naming Rule					Map ID		 parameter
			empList = session.selectList("tkEmpListAll3", emp); //selectList -> 여러건 조회
		}catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp Exception->" + e.getMessage());
		}
		return empList;
	}
	
	/**
	 * 게시글 상세 정보 조회
	 */
	@Override
	public Emp detail(int empno) {
		System.out.println("_dao: " + empno);
		Emp emp = new Emp();
		try {
			emp = session.selectOne("tkEmpSelOne", empno);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return emp;
	}
	
	/**
	 * 게시글 수정
	 */
	@Override
	public int update(Emp emp) {
		int kkk = 0;
		try {
			kkk = session.update("tkEmpUpdate", emp);
		} catch (Exception e) {
			System.out.println("update" + e.getMessage());
		}
		return kkk;
	}

	/**
	 * 관리자 가져오기
	 */
	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		try {
			empList = session.selectList("tkSelectManager", empList);
		} catch (Exception e) {
			System.out.println("listManager: " + e.getMessage());
		}
		return empList;
	}

	/**
	 * 게시글 작성
	 */
	@Override
	public int insert(Emp emp) {
		int result = 0;
		try {
			result = session.insert("insertEmp", emp);
			System.out.println("insert result" + result);
		} catch (Exception e) {
			System.out.println("insert result" + result);
			System.out.println("insert" + e.getMessage());
		}
		return result;
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public int delete(int empno) {
		int result = 0;
		try {
			result = session.delete("deleteEmp", empno);
		} catch (Exception e) {
			System.out.println("delete" + e.getMessage());
		}
		return result;
	}
	
}
