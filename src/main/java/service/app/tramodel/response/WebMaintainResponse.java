package service.app.tramodel.response;

import service.app.domain.AchievementModel;

public class WebMaintainResponse extends BaseResponse {

	AchievementModel achievement;

	public AchievementModel getAchievement() {
		return achievement;
	}

	public void setAchievement(AchievementModel achievement) {
		this.achievement = achievement;
	}
}
