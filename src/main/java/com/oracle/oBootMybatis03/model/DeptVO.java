package com.oracle.oBootMybatis03.model;

import lombok.Getter;
import lombok.Setter;

/*
 * VO:Virture Object
 * readonly
 */
@Getter
@Setter
public class DeptVO {
	private int deptno;
	private String dname;
	private String loc;
	private int odeptno;
	private String odname;
	private String oloc;
}
