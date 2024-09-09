package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softgv.cda.service.StudentProfileService;

@CrossOrigin(origins = "*", originPatterns = "*")
@RestController
@RequestMapping(value = "/studentprofiles")
public class StudentProfileController {
	@Autowired
	StudentProfileService studentProfileService;

	@PostMapping
	public ResponseEntity<?> saveStudentProfile(@RequestParam int uid, @RequestParam MultipartFile file) {
		return studentProfileService.saveStudentProfile(uid, file);
	}

}
