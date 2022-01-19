package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.service.DeptService;
import com.oracle.oBootMybatis03.service.EmpService;
import com.oracle.oBootMybatis03.service.Paging;

@Controller
public class EmpController {
	
	@Autowired private EmpService es;
	@Autowired private DeptService ds;
	
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
		// 부서 (코드, 부서명)
		List<Dept> deptList = ds.deptSelect();
		model.addAttribute("deptList", deptList);
		return "writeForm";
	}
	
	/**
	 * 게시글 작성
	 */
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(Emp emp, Model model) {
		int result = es.insert(emp);
		if(result > 0) {
			return "redirect:list";
		} else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	}
	
	/**
	 * 중복체크
	 */
	@GetMapping(value = "confirm")
	public String confirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if(emp != null) {
			model.addAttribute("msg", "중복된 사번입니다.");
			return "forward:writeForm";
		} else {
			model.addAttribute("msg", "사용 가능한 사번입니다.");
			return "forward:writeForm";
		}
	}
	
	@RequestMapping(value = "delete")
	public String delete(int empno, Model model) {
		int result = es.delete(empno);
		return "redirect:list";
	}
	
}
