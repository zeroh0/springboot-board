package com.oracle.oBootMybatis03.model;

import lombok.Getter;
import lombok.Setter;

// join 목적

@Getter
@Setter
public class EmpDept {
	// emp
	private int empno;			private String ename;
	private String job;			private int mgr;
	private String hiredate;	private int sal;
	private int comm;			private int deptno;
	
	// dept용 많다는 가정
	private String dname;
	private String loc;
}
