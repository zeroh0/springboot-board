package com.oracle.oBootMybatis03.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;import org.springframework.objenesis.instantiator.basic.DelegatingToExoticInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;


@Controller
@Log
public class UploadController {
	
	@RequestMapping(value = "uploadFormStart")
	public String uploadFormStart(Model model) {
		log.info("UploadController uploadFormStart");
		return "uploadFormStart";
	}
	
	@GetMapping(value = "uploadForm")
	public void uploadForm() {
		log.info("uploadForm GET");
	}
	
	/**
	 * 이미지 파일 업로드
	 */
	@PostMapping(value = "uploadForm")
	public String uploadForm(HttpServletRequest request, MultipartFile file1, Model model) throws Exception {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/"); // oBootMybatis03
		log.info("uploadForm POST");
		log.info("originalName : " + file1.getOriginalFilename()); // 파일의 원본 이름
//		log.info("title" + title);
		log.info("size : " + file1.getSize());
		log.info("contentType : " + file1.getContentType());
		log.info("uploadPath" + uploadPath);
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath); // 업로드 method 
		log.info("savedName" + savedName);
		model.addAttribute("savedName", savedName);
		return "uploadResult";
	}

	private String uploadFile(String originalname, byte[] fileData, String uploadPath) throws Exception {
		UUID uid = UUID.randomUUID();
		log.info("uploadPath" + uploadPath);
		File fileDirectory = new File(uploadPath); // 파일명 충돌방지
		if(!fileDirectory.exists()) { // 폴더 존재 여부 확인 없으면 생성
			fileDirectory.mkdirs();
			log.info("업로드용 폴더 생성" + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalname;
		log.info("savedName" + savedName);
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target); // byte[]를 지정한 File에 복사
		
		return savedName;
	}
	
	/**
	 * 이미지 파일 삭제
	 */
	@PostMapping(value = "uploadFileDelete")
	public String uploadDelete(HttpServletRequest request, Model model) throws Exception {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath + "fa6e1e04-68d3-4f67-b177-7e62d86556cf_jung1.jpg";
		log.info("deleteFile:" + deleteFile);
		log.info("uploadDelete POST");
		int delResult = upFileDelete(deleteFile);
		log.info("deleteFile result" + delResult);
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
		
		return "uploadResult";
	}

	private int upFileDelete(String deleteFileName) throws Exception {
		int result = 0;
		log.info("upFileDelete" + deleteFileName);
		File file = new File(deleteFileName);
		if(file.exists()) {
			if(file.delete()) {
				log.info("파일 삭제 성공");
				result = 1;
			} else {
				log.info("파일 삭제 실패");
				result = 0;
			}
		} else {
			log.info("파일이 존재하지 않습니다.");
			result = -1;
		}
		return result;
	}
	
}
