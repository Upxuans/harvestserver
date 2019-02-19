package service.app.domain;

public class PatentModel extends CopyModel{

	private Integer patentId;
	private String patentTitle;
	private String patentNum;
	private String patentNoteNum;
	private String patentUnit;
	private String patentType;
	private String patentStatus;
	private String patentApplyDate;
	private String patentEffectDate1;
	private String patentEffectDate2;
	private String patentBelong;
	private String patentAuthors = "";
	
	public Integer getPatentId() {
		return patentId;
	}
	
	public void setPatentId(Integer patentId) {
		this.patentId = patentId;
	}

	public String getPatentTitle() {
		return patentTitle;
	}

	public void setPatentTitle(String patentTitle) {
		this.patentTitle = patentTitle;
	}

	public String getPatentNum() {
		return patentNum;
	}

	public void setPatentNum(String patentNum) {
		this.patentNum = patentNum;
	}

	public String getPatentNoteNum() {
		return patentNoteNum;
	}

	public void setPatentNoteNum(String patentNoteNum) {
		this.patentNoteNum = patentNoteNum;
	}

	public String getPatentUnit() {
		return patentUnit;
	}

	public void setPatentUnit(String patentUnit) {
		this.patentUnit = patentUnit;
	}

	public String getPatentType() {
		return patentType;
	}

	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}

	public String getPatentStatus() {
		return patentStatus;
	}

	public void setPatentStatus(String patentStatus) {
		this.patentStatus = patentStatus;
	}

	public String getPatentApplyDate() {
		return patentApplyDate;
	}

	public void setPatentApplyDate(String patentApplyDate) {
		this.patentApplyDate = patentApplyDate;
	}

	public String getPatentEffectDate1() {
		return patentEffectDate1;
	}

	public void setPatentEffectDate1(String patentEffectDate1) {
		this.patentEffectDate1 = patentEffectDate1;
	}

	public String getPatentEffectDate2() {
		return patentEffectDate2;
	}

	public void setPatentEffectDate2(String patentEffectDate2) {
		this.patentEffectDate2 = patentEffectDate2;
	}

	public String getPatentBelong() {
		return patentBelong;
	}

	public void setPatentBelong(String patentBelong) {
		this.patentBelong = patentBelong;
	}

	public String getPatentAuthors() {
		return patentAuthors;
	}

	public void setPatentAuthors(String patentAuthors) {
		this.patentAuthors = patentAuthors;
	}
}
