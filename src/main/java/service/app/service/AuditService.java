/**
 * By Upxuan
 * 
 * Created in 2018/12/15
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.AuditDao;
import service.app.domain.AffairsModel;
import service.app.domain.AuthorsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;
import service.app.util.HarvestUtil;

@Service
public class AuditService {

	@Autowired
	HarvestUtil harvestUtil;
	
	@Autowired
	AuditDao auditDao;

	//获取submitId=id的copy中的记录
	public List<JpaperModel> getJpaperCopyBySubmitIdService(int id) {
		List<JpaperModel> jpaperModels = auditDao.getJpaperCopyBySubmitIdDao(id);
		return jpaperModels;
	}
	
	public List<MpaperModel> getMpaperCopyBySubmitIdService(int id) {
		List<MpaperModel> mpaperModels = auditDao.getMpaperCopyBySubmitIdDao(id);
		return mpaperModels;
	}

	public List<PatentModel> getPatentCopyBySubmitIdService(int id) {
		List<PatentModel> patentModels = auditDao.getPatentCopyBySubmitIdDao(id);
		return patentModels;
	}

	public List<ProjectModel> getProjectCopyBySubmitIdService(int id) {
		List<ProjectModel> projectModels = auditDao.getProjectCopyBySubmitIdDao(id);
		return projectModels;
	}

	public List<SubjectModel> getSubjectCopyBySubmitIdService(int id) {
		List<SubjectModel> subjectModels = auditDao.getSubjectCopyBySubmitIdDao(id);
		return subjectModels;
	}

	public List<SoftwareModel> getSoftwareCopyBySubmitIdService(int id) {
		List<SoftwareModel> softwareModels = auditDao.getSoftwareCopyBySubmitIdDao(id);
		return softwareModels;
	}

	public List<AffairsModel> getAffairsCopyBySubmitIdService(int id) {
		List<AffairsModel> affairsModels = auditDao.getAffairsCopyBySubmitIdDao(id);
		return affairsModels;
	}
	
	//获取整理后的成果记录
	public List<JpaperModel> arrangeJpaperService(Boolean gal, List<JpaperModel> jpaperModels){
		int harType = 1;
		for (int i = 0; i < jpaperModels.size(); i++) {
			JpaperModel jModel = jpaperModels.get(i);
			int harId = jModel.getJpaperId();
			int source = 0;
			if(jModel.getReview() != null) source = 1;//是jpaper_copy表中的harType和harId
			jModel.setFirstAuthor(harvestUtil.getHarFirstAuthor(harType, harId, source));
			jModel.setBelong(harvestUtil.getHarBelong(harType, harId, source));
			if(jModel.getSubmitId() != null) {
				jModel.setReviseStatus(harvestUtil.getReviseStatus(jModel.getReview()));
				jModel.setSubmitName(harvestUtil.getHarSubmitName(jModel.getSubmitId()));//教师的时候需要提交者姓名
				jModel.setReviseName(harvestUtil.getHarReviseName(jModel.getReviseId()));
			}
			if(gal) {//需要获得AuthorsList
				List<AuthorsModel> models = harvestUtil.getAuthorsList(harType, jModel.getJpaperId(), source);
				jModel.setAuthorsList(models);
			}
		}
		return jpaperModels;
	}
	
	public List<MpaperModel> arrangeMpaperService(Boolean gal, List<MpaperModel> mpaperModels){
		int harType = 2;
		for (int i = 0; i < mpaperModels.size(); i++) {
			MpaperModel mModel = mpaperModels.get(i);
			int harId = mModel.getMpaperId();
			int source = 0;
			if(mModel.getReview() != null) source = 1;
			mModel.setFirstAuthor(harvestUtil.getHarFirstAuthor(harType, harId, source));
			mModel.setBelong(harvestUtil.getHarBelong(harType, harId, source));
			if(mModel.getSubmitId() != null) {
				mModel.setReviseStatus(harvestUtil.getReviseStatus(mModel.getReview()));
				mModel.setSubmitName(harvestUtil.getHarSubmitName(mModel.getSubmitId()));//教师的时候需要提交者姓名
				mModel.setReviseName(harvestUtil.getHarReviseName(mModel.getReviseId()));
			}
			if(gal) {//需要获得AuthorsList
				List<AuthorsModel> models = harvestUtil.getAuthorsList(harType, mModel.getMpaperId(), source);
				mModel.setAuthorsList(models);;
			}
		}
		return mpaperModels;
	}
	
	public List<PatentModel> arrangePatentService(Boolean gal, List<PatentModel> patentModels){
		int harType = 3;
		for (int i = 0; i < patentModels.size(); i++) {
			PatentModel pModel = patentModels.get(i);
			int harId = pModel.getPatentId();
			int source = 0;
			if(pModel.getReview() != null) source = 1;
			pModel.setFirstAuthor(harvestUtil.getHarFirstAuthor(harType, harId, source));
			pModel.setBelong(harvestUtil.getHarBelong(harType, harId, source));
			if(pModel.getSubmitId() != null) {
				pModel.setReviseStatus(harvestUtil.getReviseStatus(pModel.getReview()));
				pModel.setSubmitName(harvestUtil.getHarSubmitName(pModel.getSubmitId()));//教师的时候需要提交者姓名
				pModel.setReviseName(harvestUtil.getHarReviseName(pModel.getReviseId()));
			}
			if(gal) {//需要获得AuthorsList
				List<AuthorsModel> pmodels = harvestUtil.getAuthorsList(harType, pModel.getPatentId(), source);
				pModel.setAuthorsList(pmodels);;
			}
		}
		return patentModels;
	}
	
	public List<ProjectModel> arrangeProjectService(Boolean gal, List<ProjectModel> projectModels){
		int harType = 4;
		for (int i = 0; i < projectModels.size(); i++) {
			ProjectModel pModel = projectModels.get(i);
			int harId = pModel.getProjectId();
			int source = 0;
			if(pModel.getReview() != null) source = 1;
			pModel.setFirstAuthor(harvestUtil.getHarFirstAuthor(harType, harId, source));
			pModel.setBelong(harvestUtil.getHarBelong(harType, harId, source));
			if(pModel.getSubmitId() != null) {
				pModel.setReviseStatus(harvestUtil.getReviseStatus(pModel.getReview()));
				pModel.setSubmitName(harvestUtil.getHarSubmitName(pModel.getSubmitId()));//教师的时候需要提交者姓名
				pModel.setReviseName(harvestUtil.getHarReviseName(pModel.getReviseId()));
			}
			if(gal) {//需要获得AuthorsList
				List<AuthorsModel> pmodels = harvestUtil.getAuthorsList(harType, pModel.getProjectId(), source);
				pModel.setAuthorsList(pmodels);;
			}
		}
		return projectModels;
	}
	
	public List<SubjectModel> arrangeSubjectService(Boolean gal, List<SubjectModel> subjectModels){
		int harType = 5;
		for (int i = 0; i < subjectModels.size(); i++) {
			SubjectModel sModel = subjectModels.get(i);
			int harId = sModel.getSubjectId();
			int source = 0;
			if(sModel.getReview() != null) source = 1;
			sModel.setFirstAuthor(harvestUtil.getHarFirstAuthor(harType, harId, source));
			sModel.setBelong(harvestUtil.getHarBelong(harType, harId, source));
			if(sModel.getSubmitId() != null) {
				sModel.setReviseStatus(harvestUtil.getReviseStatus(sModel.getReview()));
				sModel.setSubmitName(harvestUtil.getHarSubmitName(sModel.getSubmitId()));//教师的时候需要提交者姓名
				sModel.setReviseName(harvestUtil.getHarReviseName(sModel.getReviseId()));
			}
			if(gal) {//需要获得AuthorsList
				List<AuthorsModel> smodels = harvestUtil.getAuthorsList(harType, sModel.getSubjectId(), source);
				sModel.setAuthorsList(smodels);;
			}
		}
		return subjectModels;
	}
	
	public List<SoftwareModel> arrangeSoftwareService(Boolean gal, List<SoftwareModel> softwareModels){
		int harType = 6;
		for (int i = 0; i < softwareModels.size(); i++) {
			SoftwareModel sModel = softwareModels.get(i);
			int harId = sModel.getSoftwareId();
			int source = 0;
			if(sModel.getReview() != null) source = 1;
			sModel.setFirstAuthor(harvestUtil.getHarFirstAuthor(harType, harId, source));
			sModel.setBelong(harvestUtil.getHarBelong(harType, harId, source));
			if(sModel.getSubmitId() != null) {
				sModel.setReviseStatus(harvestUtil.getReviseStatus(sModel.getReview()));
				sModel.setSubmitName(harvestUtil.getHarSubmitName(sModel.getSubmitId()));//教师的时候需要提交者姓名
				sModel.setReviseName(harvestUtil.getHarReviseName(sModel.getReviseId()));
			}
			if(gal) {//需要获得AuthorsList
				List<AuthorsModel> smodels = harvestUtil.getAuthorsList(harType, sModel.getSoftwareId(), source);
				sModel.setAuthorsList(smodels);;
			}
		}
		return softwareModels;
	}
	
	public List<AffairsModel> arrangeAffairsService(Boolean gal, List<AffairsModel> affairsModels){
		int harType = 7;
		for (int i = 0; i < affairsModels.size(); i++) {
			AffairsModel aModel = affairsModels.get(i);
			int harId = aModel.getAffairsId();
			int source = 0;
			if(aModel.getReview() != null) source = 1;
			aModel.setFirstAuthor(harvestUtil.getHarFirstAuthor(harType, harId, source));
			aModel.setBelong(harvestUtil.getHarBelong(harType, harId, source));
			if(aModel.getSubmitId() != null) {
				aModel.setReviseStatus(harvestUtil.getReviseStatus(aModel.getReview()));
				aModel.setSubmitName(harvestUtil.getHarSubmitName(aModel.getSubmitId()));//教师的时候需要提交者姓名
				aModel.setReviseName(harvestUtil.getHarReviseName(aModel.getReviseId()));
			}
			if(gal) {//需要获得AuthorsList
				List<AuthorsModel> amodels = harvestUtil.getAuthorsList(harType, aModel.getAffairsId(), source);
				aModel.setAuthorsList(amodels);;
			}
		}
		return affairsModels;
	}

	//删除学生手动录入的信息（包括成果和关系）
	public Boolean delHarvestCopyService(int harType, int harId) {
		Boolean flag = true;
		try {
			if(harType == 1)
				auditDao.delJpaperCopyNoteDao(harId);
			else if(harType == 2)
				auditDao.delMpaperCopyNoteDao(harId);
			else if(harType == 3)
				auditDao.delPatentCopyNoteDao(harId);
			else if(harType == 4)
				auditDao.delProjectCopyNoteDao(harId);
			else if(harType == 5)
				auditDao.delSubjectCopyNoteDao(harId);
			else if(harType == 6)
				auditDao.delSoftwareCopyNoteDao(harId);
			else if(harType == 7)
				auditDao.delAffairsCopyNoteDao(harId);
			harvestUtil.delHarvestRelation(harType, harId, 1);//删除和copy表的关系，所以source=1
		}catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	//将copy表中的review和reviseId更新
	public boolean UpdateReviewService(int userId, int harType, int harId, int review) {
		Boolean flag = true;
		try {
			if(harType == 1)
				auditDao.UpdateJpaperReviewDao(userId, harId, review);
			else if(harType == 2)
				auditDao.UpdateMpaperReviewDao(userId, harId, review);
			else if(harType == 3)
				auditDao.UpdatePatentReviewDao(userId, harId, review);
			else if(harType == 4)
				auditDao.UpdateProjectReviewDao(userId, harId, review);
			else if(harType == 5)
				auditDao.UpdateSubjectReviewDao(userId, harId, review);
			else if(harType == 6)
				auditDao.UpdateSoftwareReviewDao(userId, harId, review);
			else if(harType == 7)
				auditDao.UpdateAffairsReviewDao(userId, harId, review);
		}catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}
}
