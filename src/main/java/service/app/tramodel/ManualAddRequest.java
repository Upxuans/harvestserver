package service.app.tramodel;

import java.util.List;

import service.app.domain.AffairsModel;
import service.app.domain.AuthorsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;

public class ManualAddRequest extends BaseRequest implements Cloneable {

	private int handleType;
	private int source;//只在更新时启用
	private JpaperModel jpaperModel;
	private MpaperModel mpaperModel;
	private PatentModel patentModel;
	private ProjectModel projectModel;
	private SubjectModel subjectModel;
	private SoftwareModel softwareModel;
	private AffairsModel affairsModel;
	private List<AuthorsModel> authorsModels;

	public int getHandleType() {
		return handleType;
	}

	public void setHandleType(int handleType) {
		this.handleType = handleType;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public JpaperModel getJpaperModel() {
		return jpaperModel;
	}

	public void setJpaperModel(JpaperModel jpaperModel) {
		this.jpaperModel = jpaperModel;
	}
	
	public MpaperModel getMpaperModel() {
		return mpaperModel;
	}

	public void setMpaperModel(MpaperModel mpaperModel) {
		this.mpaperModel = mpaperModel;
	}

	public PatentModel getPatentModel() {
		return patentModel;
	}

	public void setPatentModel(PatentModel patentModel) {
		this.patentModel = patentModel;
	}

	public ProjectModel getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}

	public SubjectModel getSubjectModel() {
		return subjectModel;
	}

	public void setSubjectModel(SubjectModel subjectModel) {
		this.subjectModel = subjectModel;
	}

	public SoftwareModel getSoftwareModel() {
		return softwareModel;
	}

	public void setSoftwareModel(SoftwareModel softwareModel) {
		this.softwareModel = softwareModel;
	}

	public AffairsModel getAffairsModel() {
		return affairsModel;
	}

	public void setAffairsModel(AffairsModel affairsModel) {
		this.affairsModel = affairsModel;
	}

	public List<AuthorsModel> getAuthorsModels() {
		return authorsModels;
	}

	public void setAuthorsModels(List<AuthorsModel> authorsModels) {
		this.authorsModels = authorsModels;
	}
}
