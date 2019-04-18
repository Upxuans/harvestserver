package service.app.domain;

public class SoftwareModel extends CopyModel {

	private Integer softwareId;
	private String softwareName;
	private String softwareNum;
	private String softwareAcquireType;
	private String softwareDate;
	private String softwareAuthors = "";
	
	public Integer getSoftwareId() {
		return softwareId;
	}
	
	public void setSoftwareId(Integer softwareId) {
		this.softwareId = softwareId;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getSoftwareNum() {
		return softwareNum;
	}

	public void setSoftwareNum(String softwareNum) {
		this.softwareNum = softwareNum;
	}

	public String getSoftwareAcquireType() {
		return softwareAcquireType;
	}

	public void setSoftwareAcquireType(String softwareAcquireType) {
		this.softwareAcquireType = softwareAcquireType;
	}

	public String getSoftwareAuthors() {
		return softwareAuthors;
	}

	public void setSoftwareAuthors(String softwareAuthors) {
		this.softwareAuthors = softwareAuthors;
	}

	public String getSoftwareDate() {
		return softwareDate;
	}

	public void setSoftwareDate(String softwareDate) {
		this.softwareDate = softwareDate;
	}
}
