package service.app.domain;

public class BaseModel {
	
	private String firstAuthor;
	private String belong;
	private String submitName;
	private String reviseName;
	
	public String getSubmitName() {
		return submitName;
	}

	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}
	
	public String getFirstAuthor() {
		return firstAuthor;
	}

	public void setFirstAuthor(String firstAuthor) {
		this.firstAuthor = firstAuthor;
	}

	public String getBelong() {
		return belong;
	}
	
	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getReviseName() {
		return reviseName;
	}

	public void setReviseName(String reviseName) {
		this.reviseName = reviseName;
	}
}
