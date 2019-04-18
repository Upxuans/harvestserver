/**
 * By Upxuan
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.PatentDao;
import service.app.domain.PatentModel;

@Service
public class PatentService {
	
	@Autowired
	PatentDao patentDao;

	public List<PatentModel> getPatentDataFromTeacherService(int userId, int load) {
		List<PatentModel> patentModels = patentDao.getPatentDataFromTeacherDao(userId, load);
		List<PatentModel> copyModel = patentDao.getPatentCopyFromTeacherDao(userId, load);
		if(copyModel != null) 
			patentModels.addAll(copyModel);
		return patentModels;
	}

	public List<PatentModel> getPatentDataFromStudentService(int userId, int load) {
		List<PatentModel> patentModels = patentDao.getPatentDataFromStudentDao(userId, load);
		List<PatentModel> copyModel = patentDao.getPatentCopyFromStudentDao(userId, load);
		if(copyModel != null) 
			patentModels.addAll(copyModel);
		return patentModels;
	}

	public int insertPatentToStockService(int userType, int userId, PatentModel data) {
		
		int d1 = 0, d2 = 0, id = 0;
		if(data.getPatentType().equals("发明专利"))
			d1 = 1;
		else if(data.getPatentType().equals("实用新型"))
			d1 = 2;
		else if(data.getPatentType().equals("外观设计"))
			d1 = 3;
		
		if(data.getPatentStatus().equals("申请"))
			d2 = 1;
		else if(data.getPatentStatus().equals("授权"))
			d2 = 2;
		
		if(userType == 1 || userType == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
			patentDao.insertPatentToMyStockDao(
				data.getPatentTitle(), 
				data.getPatentNum(),
				data.getPatentNoteNum(),
				data.getPatentUnit(),
				d1,
				d2,
				java.sql.Date.valueOf(data.getPatentApplyDate()), 
				java.sql.Date.valueOf(data.getPatentEffectDate1()), 
				java.sql.Date.valueOf(data.getPatentEffectDate2()), 
				data.getPatentBelong()
			);
			id = patentDao.getPatentInsertNoteId();
		}else if(userType == 2) {
			patentDao.insertPatentToMyCopyStockDao(
				data.getPatentTitle(), 
				data.getPatentNum(),
				data.getPatentNoteNum(),
				data.getPatentUnit(),
				d1,
				d2,
				java.sql.Date.valueOf(data.getPatentApplyDate()), 
				java.sql.Date.valueOf(data.getPatentEffectDate1()), 
				java.sql.Date.valueOf(data.getPatentEffectDate2()), 
				data.getPatentBelong(),
				1,
				userId
			);
			id = patentDao.getPatentCopyInsertNoteId();
		}
		return id;
	}

	public Boolean updatePatentService(PatentModel data, int harId, int source) {
		
		Boolean flag = true;
		int d1 = 0, d2 = 0;
		
		if(data.getPatentType().equals("发明专利"))
			d1 = 1;
		else if(data.getPatentType().equals("实用新型"))
			d1 = 2;
		else if(data.getPatentType().equals("外观设计"))
			d1 = 3;
		
		if(data.getPatentStatus().equals("申请"))
			d2 = 1;
		else if(data.getPatentStatus().equals("授权"))
			d2 = 2;
		
		try {
			if(source == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
				patentDao.updatePatentDao(
					harId,
					data.getPatentTitle(), 
					data.getPatentNum(),
					data.getPatentNoteNum(),
					data.getPatentUnit(),
					d1,
					d2,
					java.sql.Date.valueOf(data.getPatentApplyDate()), 
					java.sql.Date.valueOf(data.getPatentEffectDate1()), 
					java.sql.Date.valueOf(data.getPatentEffectDate2()), 
					data.getPatentBelong()
				);
			}else if(source == 1) {
				patentDao.updatePatentCopyDao(
					harId,
					data.getPatentTitle(), 
					data.getPatentNum(),
					data.getPatentNoteNum(),
					data.getPatentUnit(),
					d1,
					d2,
					java.sql.Date.valueOf(data.getPatentApplyDate()), 
					java.sql.Date.valueOf(data.getPatentEffectDate1()), 
					java.sql.Date.valueOf(data.getPatentEffectDate2()), 
					data.getPatentBelong()
				);
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public void updatePatentCopyReviewService(int harId, int review, int reviseId) {
		patentDao.updatePatentCopyReviewDao(harId, review, reviseId);
	}

}
