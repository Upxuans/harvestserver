/**
 * By Upxuan
 * 
 * Created in 2018/11/5
 */
package service.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.ReviseInfoDao;

@Service
public class ReviseInfoService {

	@Autowired
	ReviseInfoDao reviseInfoDao;
	
	public boolean updateInfoInTeacherService(int userId, String link, String tel, String email, String direction) {
		
		Boolean flag = true;
		try {
			reviseInfoDao.updateInfoInTeacherDao(userId, link, tel, email, direction);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public boolean updateInfoInStudentService(int userId, String tel, String email, String direction) {
		Boolean flag = true;
		try {
			reviseInfoDao.updateInfoInStudentDao(userId, tel, email, direction);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public String findPwdByIdService(int userType, int userId) {
		String password = "";
		if(userType == 0) {
			password = reviseInfoDao.findManagerPwdByIdDao(userId);
		}else if(userType == 1) {
			password = reviseInfoDao.findTeacherPwdByIdDao(userId);
		}else if(userType == 2) {
			password = reviseInfoDao.findStudentPwdByIdDao(userId);
		}
		return password;
	}
	
	public int updatePwdService(int userType, int userId, String checkPass) {
		int flag = 1;
		try {
			if(userType == 0) {
				reviseInfoDao.updatePwdInManagerDao(userId, checkPass);
			}else if(userType == 1) {
				reviseInfoDao.updatePwdInTeacherDao(userId, checkPass);
			}else if(userType == 2) {
				reviseInfoDao.updatePwdInStudentDao(userId, checkPass);
			}
		} catch (Exception e) {
			flag = 2;
		}
		return flag;
	}

	
}
