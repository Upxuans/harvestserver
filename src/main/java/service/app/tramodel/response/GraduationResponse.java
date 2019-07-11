package service.app.tramodel.response;

import java.util.List;

public class GraduationResponse extends BaseResponse {

	private List<String> fileList;
	private int folderSize;
	
	private Boolean devices;
	private String deviceDescribe;
	private Boolean keyss;
	private Boolean works;
	private String workDescribe;
	
	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
	
	public int getFolderSize() {
		return folderSize;
	}

	public void setFolderSize(int folderSize) {
		this.folderSize = folderSize;
	}

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
		return "GraduationResponse [fileList=" + fileList + ", folderSize=" + folderSize + ", devices=" + devices
				+ ", deviceDescribe=" + deviceDescribe + ", keyss=" + keyss + ", works=" + works + ", workDescribe="
				+ workDescribe + "]";
	}
	
}
