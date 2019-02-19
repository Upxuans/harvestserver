package service.app.domain;

public class AffairsModel extends CopyModel {

	private Integer affairsId;
	private String affairsTitle;
	private String affairsContent;
	private String affairsDate1;
	private String affairsDate2;
	private String affairsAuthors = "";
	
	public Integer getAffairsId() {
		return affairsId;
	}
	
	public void setAffairsId(Integer affairsId) {
		this.affairsId = affairsId;
	}

	public String getAffairsTitle() {
		return affairsTitle;
	}

	public void setAffairsTitle(String affairsTitle) {
		this.affairsTitle = affairsTitle;
	}

	public String getAffairsContent() {
		return affairsContent;
	}

	public void setAffairsContent(String affairsContent) {
		this.affairsContent = affairsContent;
	}

	public String getAffairsDate1() {
		return affairsDate1;
	}

	public void setAffairsDate1(String affairsDate1) {
		this.affairsDate1 = affairsDate1;
	}

	public String getAffairsDate2() {
		return affairsDate2;
	}

	public void setAffairsDate2(String affairsDate2) {
		this.affairsDate2 = affairsDate2;
	}

	public String getAffairsAuthors() {
		return affairsAuthors;
	}

	public void setAffairsAuthors(String affairsAuthors) {
		this.affairsAuthors = affairsAuthors;
	}
}
