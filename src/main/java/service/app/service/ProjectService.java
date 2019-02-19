/**
 * By Upxuan
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.ProjectDao;
import service.app.domain.ProjectModel;

@Service
public class ProjectService {

	@Autowired
	ProjectDao projectDao;

	public List<ProjectModel> getProjectDataFromTeacherService(int userId, int load) {
		List<ProjectModel> projectModel = projectDao.getProjectDataFromTeacherDao(userId, load);
		List<ProjectModel> copyModel = projectDao.getProjectCopyFromTeacherDao(userId, load);
		if(copyModel != null) 
			projectModel.addAll(copyModel);
		return projectModel;
	}

	public List<ProjectModel> getProjectDataFromStudentService(int userId, int load) {
		List<ProjectModel> projectModel = projectDao.getProjectDataFromStudentDao(userId, load);
		List<ProjectModel> copyModel = projectDao.getProjectCopyFromStudentDao(userId, load);
		if(copyModel != null) 
			projectModel.addAll(copyModel);
		return projectModel;
	}

	public int insertProjectToStockService(int userType, int userId, ProjectModel data) {
		
		int id = 0;
		if(userType == 1 || userType == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
			projectDao.insertProjectToMyStockDao(
				data.getProjectType(),
				data.getProjectTitle(),
				data.getProjectNum(),
				java.sql.Date.valueOf(data.getProjectDate1()), 
				java.sql.Date.valueOf(data.getProjectDate2()), 
				data.getProjectMoney()
			);
			id = projectDao.getProjectInsertNoteId();
		}else if(userType == 2) {
			projectDao.insertProjectToMyCopyStockDao(
				data.getProjectType(),
				data.getProjectTitle(),
				data.getProjectNum(),
				java.sql.Date.valueOf(data.getProjectDate1()), 
				java.sql.Date.valueOf(data.getProjectDate2()), 
				data.getProjectMoney(),
				1,
				userId
			);
			id = projectDao.getProjectCopyInsertNoteId();
		}
		return id;
	}

	public Boolean updateProjectService(ProjectModel data, int harId, int source) {
		Boolean flag = true;
		try {
			if(source == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
				projectDao.updateProjectDao(
					harId,
					data.getProjectType(),
					data.getProjectTitle(),
					data.getProjectNum(),
					java.sql.Date.valueOf(data.getProjectDate1()), 
					java.sql.Date.valueOf(data.getProjectDate2()), 
					data.getProjectMoney()
				);
			}else if(source == 1) {
				projectDao.updateProjectCopyDao(
					harId,
					data.getProjectType(),
					data.getProjectTitle(),
					data.getProjectNum(),
					java.sql.Date.valueOf(data.getProjectDate1()), 
					java.sql.Date.valueOf(data.getProjectDate2()), 
					data.getProjectMoney()
				);
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public void updateProjectCopyReviewService(int harId, int review, int reviseId) {
		projectDao.updateProjectCopyReviewDao(harId, review, reviseId);
	}
	
}
