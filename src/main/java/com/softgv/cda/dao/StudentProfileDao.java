package com.softgv.cda.dao;

import java.util.List;

import com.softgv.cda.entity.StudentProfile;

public interface StudentProfileDao {

	StudentProfile saveStudentProfile(StudentProfile studentProfile);

	List<StudentProfile> findAllStudentProfiles();

}
