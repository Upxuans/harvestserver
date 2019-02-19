package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.MyStuDao;
import service.app.domain.StudentModel;

@Service
public class MyStuService {

	@Autowired
	MyStuDao myStuDao;
	
	public List<StudentModel> getStuByTeachersIdService(int userId) {
		List<StudentModel> studentModel = myStuDao.getStuByTeachersIdDao(userId);
		return studentModel;
	}

	public int getHarNumService(int userId, int harType) {
		return myStuDao.getHarNumDao(harType, userId);
	}

}
