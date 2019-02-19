package service.app.domain;

public class SubjectModel extends CopyModel {

	private Integer subjectId;
	private String subjectTitle;
	private String subjectBookName;
	private String subjectStatus;
	private String subjectISBN;
	private String subjectPublish;
	private String subjectPublishDate;
	private String subjectDOI;
	private String subjectAuthors = "";
	
	public Integer getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getSubjectBookName() {
		return subjectBookName;
	}

	public void setSubjectBookName(String subjectBookName) {
		this.subjectBookName = subjectBookName;
	}

	public String getSubjectStatus() {
		return subjectStatus;
	}

	public void setSubjectStatus(String subjectStatus) {
		this.subjectStatus = subjectStatus;
	}

	public String getSubjectISBN() {
		return subjectISBN;
	}

	public void setSubjectISBN(String subjectISBN) {
		this.subjectISBN = subjectISBN;
	}

	public String getSubjectPublish() {
		return subjectPublish;
	}

	public void setSubjectPublish(String subjectPublish) {
		this.subjectPublish = subjectPublish;
	}

	public String getSubjectPublishDate() {
		return subjectPublishDate;
	}

	public void setSubjectPublishDate(String subjectPublishDate) {
		this.subjectPublishDate = subjectPublishDate;
	}

	public String getSubjectDOI() {
		return subjectDOI;
	}

	public void setSubjectDOI(String subjectDOI) {
		this.subjectDOI = subjectDOI;
	}

	public String getSubjectAuthors() {
		return subjectAuthors;
	}

	public void setSubjectAuthors(String subjectAuthors) {
		this.subjectAuthors = subjectAuthors;
	}
}
