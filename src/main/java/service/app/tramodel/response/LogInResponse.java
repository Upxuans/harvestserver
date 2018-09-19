package service.app.tramodel.response;

import service.app.domain.ManagerModel;

public class LogInResponse extends BaseResponse{ //登陆扩充，加入登陆信息和错误码

	private ManagerModel managerInfo;
	
	
	public ManagerModel getManagerInfo() {
		return managerInfo;
	}
	public void setManagerInfo(ManagerModel managerInfo) {
		this.managerInfo = managerInfo;
	}
}