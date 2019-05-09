/**
 * By Upxuan
 * 
 * Created in 2019/04
 */
package service.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.GraduationDao;
import service.app.tramodel.response.GraduationResponse;

@Service
public class GraduationService {
	
	@Autowired
	GraduationDao graduationDao;

	public GraduationResponse getGraduationMsgService(int userId) {
		GraduationResponse model = graduationDao.getGraduationMsgDao(userId);
		return model;
	}

	public void updateStudentGraduationService(int id , Boolean devices, String deviceDescribe, Boolean keyss, Boolean works, String workDescribe) {
		graduationDao.updateStudentGraduationDao(id, devices, deviceDescribe, keyss, works, workDescribe);
	}
}
