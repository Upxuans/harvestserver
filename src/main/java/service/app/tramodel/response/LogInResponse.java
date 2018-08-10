package service.app.tramodel.response;

import service.app.domain.ManagerInfo;

public class LogInResponse extends BaseResponse{ //登陆扩充，加入登陆信息和错误码

	private ManagerInfo managerInfo;
	
	public ManagerInfo getManagerInfo() {
		return managerInfo;
	}
	public void setManagerInfo(ManagerInfo managerInfo) {
		this.managerInfo = managerInfo;
	}
	
}