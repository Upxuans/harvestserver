package service.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.WebMaintainDao;
import service.app.domain.AchievementModel;

@Service
public class WebMaintainService {
	
	@Autowired
	WebMaintainDao webMaintainDao;

	public AchievementModel getAchievementService() {
		AchievementModel model = webMaintainDao.getAchievementDao();
		return model;
	}

	public void updateAchievementService(String project1, String project2, String paper) {
		webMaintainDao.updateAchievementDao(project1, project2, paper);
	}
}
