package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.ManageHarDao;
import service.app.domain.AffairsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;
import service.app.util.HarvestUtil;

@Service
public class ManageHarService {
	
	@Autowired
	ManageHarDao manageHarDao;
	
	@Autowired
	HarvestUtil harvestUtil;

	//获得所有harvest
	public List<JpaperModel> getAllJpaperService() {
		List<JpaperModel> jpaperModels = manageHarDao.getAllJpaperDao();
		return jpaperModels;
	}

	public List<MpaperModel> getAllMpaperService() {
		List<MpaperModel> mpaperModels = manageHarDao.getAllMpaperDao();
		return mpaperModels;
	}

	public List<PatentModel> getAllPatentService() {
		List<PatentModel> patentModels = manageHarDao.getAllPatentDao();
		return patentModels;
	}

	public List<ProjectModel> getAllProjectService() {
		List<ProjectModel> projectModels = manageHarDao.getAllProjectDao();
		return projectModels;
	}

	public List<SubjectModel> getAllSubjectService() {
		List<SubjectModel> subjectModels = manageHarDao.getAllSubjectDao();
		return subjectModels;
	}

	public List<SoftwareModel> getAllSoftwareService() {
		List<SoftwareModel> softwareModels = manageHarDao.getAllSoftwareDao();
		return softwareModels;
	}

	public List<AffairsModel> getAllAffairsService() {
		List<AffairsModel> affairsModels = manageHarDao.getAllAffairsDao();
		return affairsModels;
	}

	//获得所有harvestCopy
	public List<JpaperModel> getAllJpaperCopyService() {
		List<JpaperModel> jpaperModels = manageHarDao.getAllJpaperCopyDao();
		return jpaperModels;
	}

	public List<MpaperModel> getAllMpaperCopyService() {
		List<MpaperModel> mpaperModels = manageHarDao.getAllMpaperCopyDao();
		return mpaperModels;
	}

	public List<PatentModel> getAllPatentCopyService() {
		List<PatentModel> patentModels = manageHarDao.getAllPatentCopyDao();
		return patentModels;
	}

	public List<ProjectModel> getAllProjectCopyService() {
		List<ProjectModel> projectModels = manageHarDao.getAllProjectCopyDao();
		return projectModels;
	}

	public List<SubjectModel> getAllSubjectCopyService() {
		List<SubjectModel> subjectModels = manageHarDao.getAllSubjectCopyDao();
		return subjectModels;
	}

	public List<SoftwareModel> getAllSoftwareCopyService() {
		List<SoftwareModel> softwareModels = manageHarDao.getAllSoftwareCopyDao();
		return softwareModels;
	}

	public List<AffairsModel> getAllAffairsCopyService() {
		List<AffairsModel> affairsModels = manageHarDao.getAllAffairsCopyDao();
		return affairsModels;
	}
	
	//删除成果记录
	public Boolean delHarvestService(int harType, int harId) {
		Boolean flag = true;
		try {
			if(harType == 1)
				manageHarDao.delJpaperNoteDao(harId);
			else if(harType == 2)
				manageHarDao.delMpaperNoteDao(harId);
			else if(harType == 3)
				manageHarDao.delPatentNoteDao(harId);
			else if(harType == 4)
				manageHarDao.delProjectNoteDao(harId);
			else if(harType == 5)
				manageHarDao.delSubjectNoteDao(harId);
			else if(harType == 6)
				manageHarDao.delSoftwareNoteDao(harId);
			else if(harType == 7)
				manageHarDao.delAffairsNoteDao(harId);
			harvestUtil.delHarvestRelation(harType, harId, 0);//source=0
		}catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public Boolean delHarvestCopyService(int harType, int harId) {
		Boolean flag = true;
		try {
			if(harType == 1)
				manageHarDao.delJpaperCopyNoteDao(harId);
			else if(harType == 2)
				manageHarDao.delMpaperCopyNoteDao(harId);
			else if(harType == 3)
				manageHarDao.delPatentCopyNoteDao(harId);
			else if(harType == 4)
				manageHarDao.delProjectCopyNoteDao(harId);
			else if(harType == 5)
				manageHarDao.delSubjectCopyNoteDao(harId);
			else if(harType == 6)
				manageHarDao.delSoftwareCopyNoteDao(harId);
			else if(harType == 7)
				manageHarDao.delAffairsCopyNoteDao(harId);
			harvestUtil.delHarvestRelation(harType, harId, 1);//source=1
		}catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}
}
