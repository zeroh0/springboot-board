package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.service.EmpService;
import com.oracle.oBootMybatis03.service.Paging;

@Controller
public class EmpController {
	@Autowired
	private EmpService es;
	
	/**
	 * 게시글 목록 조회
	 */
	@RequestMapping(value = "list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController Start list...");
		int total = es.total(); //Emp Count -> 14
		System.out.println("EmpController total=>" + total);
		
		System.out.println("currentPage=>" + currentPage);
		//						14	  null(0,1,...)
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart()); //시작시 1
		emp.setEnd(pg.getEnd());	 //시작시 10
		
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()=>" + listEmp.size());
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		
		return "list";
	}
	
	/**
	 * 게시글 상세 정보 조회
	 */
	@GetMapping(value = "detail")
	public String detail(int empno, Model model) {
		System.out.println("_controller: " + empno);
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		System.out.println("_controller" + emp);
		return "detail";
	}
	
	/**
	 * 게시글 수정 입력 폼
	 */
	@GetMapping(value = "updateForm")
	public String updateForm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		return "updateForm";
	}
	
	/**
	 * 게시글 수정 
	 */
	@PostMapping(value = "update")
	public String update(Emp emp, Model model) {
		int uptCnt = es.update(emp);
		model.addAttribute("uptCnt", uptCnt);
		model.addAttribute("kk3", "Message Text");
		return "forward:list";
	}
	
	/**
	 * 게시글 작성 입력 폼
	 */
	@RequestMapping(value = "writeForm")
	public String writeForm(Model model) {
		// 관리자사번만 get
		List<Emp> empList = es.listManager();
		model.addAttribute("empMngList", empList);
//		List<Dept> deptList = es.deptSelect();
//		model.addAttribute("deptList", deptList);
		return "writeForm";
	}
}
