package com.softgv.cda.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softgv.cda.dao.StudentProfileDao;
import com.softgv.cda.dao.UserDao;
import com.softgv.cda.entity.StudentProfile;
import com.softgv.cda.entity.User;
import com.softgv.cda.exceptionclasses.UserNotFoundException;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

	private static final String FOLDER_PATH = "C:\\Users\\gagan\\Documents\\My-React\\cda-react-app\\public\\images\\students\\";

	@Autowired
	StudentProfileDao studentProfileDao;

	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<?> saveStudentProfile(int uid, MultipartFile file) {
		Optional<User> optional = userDao.findUserById(uid);
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User ID : " + uid).build();
		User user = optional.get();
		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		StudentProfile studentProfile = StudentProfile.builder().id(uid).photo(photo).user(user).build();
		studentProfileDao.saveStudentProfile(studentProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Profile Saved Successfully...").body(studentProfile).build());
	}

	@Override
	public ResponseEntity<?> findAllStudentProfiles() {

		List<StudentProfile> studentProfiles = studentProfileDao.findAllStudentProfiles();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Student Profiles Found Successfully...").body(studentProfiles).build());
	}

}
