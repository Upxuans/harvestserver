/**
 * By Upxuan
 * 
 * Created in 2019/04/25
 */
package service.app.tramodel;

public class GraduationMsgRequest extends BaseRequest implements Cloneable {

	private String username;
	private String filename;
	private String localpath;
//	private MultipartFile file;
	private Boolean devices;
	private String deviceDescribe;
	private Boolean keyss;
	private Boolean works;
	private String workDescribe;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getLocalpath() {
		return localpath;
	}
	
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}
	
//	public MultipartFile getFile() {
//		return file;
//	}
//	
//	public void setFile(MultipartFile file) {
//		this.file = file;
//	}

	public Boolean getDevices() {
		return devices;
	}

	public void setDevices(Boolean devices) {
		this.devices = devices;
	}

	public String getDeviceDescribe() {
		return deviceDescribe;
	}

	public void setDeviceDescribe(String deviceDescribe) {
		this.deviceDescribe = deviceDescribe;
	}

	public Boolean getKeyss() {
		return keyss;
	}

	public void setKeyss(Boolean keyss) {
		this.keyss = keyss;
	}

	public Boolean getWorks() {
		return works;
	}

	public void setWorks(Boolean works) {
		this.works = works;
	}

	public String getWorkDescribe() {
		return workDescribe;
	}

	public void setWorkDescribe(String workDescribe) {
		this.workDescribe = workDescribe;
	}

	@Override
	public String toString() {
		return "GraduationMsgRequest [username=" + username + ", filename=" + filename + ", devices=" + devices
				+ ", deviceDescribe=" + deviceDescribe + ", keyss=" + keyss + ", works=" + works + ", workDescribe="
				+ workDescribe + "]";
	}

}
