package service.app.tramodel.response;

import java.util.List;

import service.app.domain.AffairsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;

public class HarvestResponse extends BaseResponse{

	protected List<JpaperModel> jpaperModels;
	protected List<MpaperModel> mpaperModels;
	protected List<PatentModel> patentModels;
	protected List<ProjectModel> projectModels;
	protected List<SubjectModel> subjectModels;
	protected List<SoftwareModel> softwareModels;
	protected List<AffairsModel> affairsModels;
	
	public List<JpaperModel> getJpaperModels() {
		return jpaperModels;
	}
	
	public void setJpaperModels(List<JpaperModel> jpaperModels) {
		this.jpaperModels = jpaperModels;
	}

	public List<MpaperModel> getMpaperModels() {
		return mpaperModels;
	}

	public void setMpaperModels(List<MpaperModel> mpaperModels) {
		this.mpaperModels = mpaperModels;
	}

	public List<PatentModel> getPatentModels() {
		return patentModels;
	}

	public void setPatentModels(List<PatentModel> patentModels) {
		this.patentModels = patentModels;
	}

	public List<ProjectModel> getProjectModels() {
		return projectModels;
	}

	public void setProjectModels(List<ProjectModel> projectModels) {
		this.projectModels = projectModels;
	}

	public List<SubjectModel> getSubjectModels() {
		return subjectModels;
	}

	public void setSubjectModels(List<SubjectModel> subjectModels) {
		this.subjectModels = subjectModels;
	}

	public List<SoftwareModel> getSoftwareModels() {
		return softwareModels;
	}

	public void setSoftwareModels(List<SoftwareModel> softwareModels) {
		this.softwareModels = softwareModels;
	}

	public List<AffairsModel> getAffairsModels() {
		return affairsModels;
	}

	public void setAffairsModels(List<AffairsModel> affairsModels) {
		this.affairsModels = affairsModels;
	}
}
