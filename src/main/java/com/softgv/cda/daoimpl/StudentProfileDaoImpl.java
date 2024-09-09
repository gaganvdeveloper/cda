package com.softgv.cda.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softgv.cda.dao.StudentProfileDao;
import com.softgv.cda.entity.StudentProfile;
import com.softgv.cda.repository.StudentProfileRepository;

@Service
public class StudentProfileDaoImpl implements StudentProfileDao {

	@Autowired
	private StudentProfileRepository studentProfileRepository;

	@Override
	public StudentProfile saveStudentProfile(StudentProfile studentProfile) {
		return studentProfileRepository.save(studentProfile);
	}

}
