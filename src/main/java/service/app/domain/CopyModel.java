/**
 * By Upxuan
 * 
 * Created in 2018/12/6
 */
package service.app.domain;

import java.util.List;

public class CopyModel extends BaseModel {
	
	private Integer review;//审核状态
	private Integer submitId;//提交的作者id
	private Integer reviseId;//审核的教师id
	private List<AuthorsModel> authorsList;
	
	public Integer getReview() {
		return review;
	}
	
	public void setReview(Integer review) {
		this.review = review;
	}
	
	public Integer getSubmitId() {
		return submitId;
	}
	
	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}
	
	public Integer getReviseId() {
		return reviseId;
	}
	
	public void setReviseId(Integer reviseId) {
		this.reviseId = reviseId;
	}

	public List<AuthorsModel> getAuthorsList() {
		return authorsList;
	}

	public void setAuthorsList(List<AuthorsModel> authorsList) {
		this.authorsList = authorsList;
	}
}
