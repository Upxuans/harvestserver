package service.app.tramodel;

public class BaseRequest implements Cloneable {
	
	private int userType;
	private int userId;
	private int harType;
	private int harId;
	
	public int getUserType() {
		return userType;
	}
	
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getHarType() {
		return harType;
	}
	
	public void setHarType(int harType) {
		this.harType = harType;
	}
	
	public int getHarId() {
		return harId;
	}
	
	public void setHarId(int harId) {
		this.harId = harId;
	}
}
