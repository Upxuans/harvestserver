package service.app.domain;

public class ProjectModel extends CopyModel {
	
	private Integer projectId;
	private String projectType;
	private String projectTitle;
	private String projectNum;
	private String projectDate1;
	private String projectDate2;
	private String projectMoney;
	private String projectAuthors = "";
	
	public Integer getProjectId() {
		return projectId;
	}
	
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectType() {
		return projectType;
	}
	
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	public String getProjectTitle() {
		return projectTitle;
	}
	
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	
	public String getProjectNum() {
		return projectNum;
	}
	
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	
	public String getProjectDate1() {
		return projectDate1;
	}

	public void setProjectDate1(String projectDate1) {
		this.projectDate1 = projectDate1;
	}

	public String getProjectDate2() {
		return projectDate2;
	}

	public void setProjectDate2(String projectDate2) {
		this.projectDate2 = projectDate2;
	}

	public String getProjectMoney() {
		return projectMoney;
	}
	
	public void setProjectMoney(String projectMoney) {
		this.projectMoney = projectMoney;
	}
	
	public String getProjectAuthors() {
		return projectAuthors;
	}
	
	public void setProjectAuthors(String projectAuthors) {
		this.projectAuthors = projectAuthors;
	}
}
