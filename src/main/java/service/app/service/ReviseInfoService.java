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
	
	public void updateInfoInTeacherOneService(int userId, String link, String tel, String email, String direction) {
		reviseInfoDao.updateInfoInTeacherOneDao(userId, link, tel, email, direction);
		return ;
	}
	
	public void updateInfoInStudentOneService(int userId, String tel, String email, String direction) {
		reviseInfoDao.updateInfoInStudentOneDao(userId, tel, email, direction);
		return ;
	}
	
	public void updateInfoInTeacherTwoService(int userId, String imgUrl, String link, String tel, String email, String direction) {
		reviseInfoDao.updateInfoInTeacherTwoDao(userId, imgUrl, link, tel, email, direction);
		return ;
	}

	public void updateInfoInStudentTwoService(int userId, String imgUrl, String tel, String email, String direction) {
		reviseInfoDao.updateInfoInStudentTwoDao(userId, imgUrl, tel, email, direction);
		return ;
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

	public String getImgUrlService(int userType, int userId) {
		String imgurl = "";
		if(userType == 1) {
			imgurl = reviseInfoDao.getTeacherImgUrlDao(userId);
		}else if(userType == 2) {
			imgurl = reviseInfoDao.getStudentImgUrlDao(userId);
		}
		return imgurl;
	}
}
