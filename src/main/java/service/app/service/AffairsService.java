/**
 * By Upxuan
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.AffairsDao;
import service.app.domain.AffairsModel;

@Service
public class AffairsService {

	@Autowired
	AffairsDao affairsDao;

	public List<AffairsModel> getAffairsDataFromTeacherService(int userId, int load) {
		List<AffairsModel> affairsModel = affairsDao.getAffairsDataFromTeacherDao(userId, load);
		List<AffairsModel> copyModel = affairsDao.getAffairsCopyFromTeacherDao(userId, load);
		if(copyModel != null) 
			affairsModel.addAll(copyModel);
		return affairsModel;
	}

	public List<AffairsModel> getAffairsDataFromStudentService(int userId, int load) {
		List<AffairsModel> affairsModel = affairsDao.getAffairsDataFromStudentDao(userId, load);
		List<AffairsModel> copyModel = affairsDao.getAffairsCopyFromStudentDao(userId, load);
		if(copyModel != null) 
			affairsModel.addAll(copyModel);
		return affairsModel;
	}

	public int insertAffairsToStockService(int userType, int userId, AffairsModel data) {
		int id = 0;
		if(userType == 1 || userType == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
			affairsDao.insertAffairsToMyStockDao(
				data.getAffairsTitle(),
				data.getAffairsContent(),
				java.sql.Date.valueOf(data.getAffairsDate1()), 
				java.sql.Date.valueOf(data.getAffairsDate2())
			);
			id = affairsDao.getAffairsInsertNoteId();
		}else if(userType == 2) {
			affairsDao.insertAffairsToMyCopyStockDao(
				data.getAffairsTitle(),
				data.getAffairsContent(),
				java.sql.Date.valueOf(data.getAffairsDate1()), 
				java.sql.Date.valueOf(data.getAffairsDate2()),
				1,
				userId
			);
			id = affairsDao.getAffairsCopyInsertNoteId();
		}
		return id;
	}

	public Boolean updateAffairsService(AffairsModel data, int harId, int source) {
		
		Boolean flag = true;
		try {
			if(source == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
				affairsDao.updateAffairsDao(
					harId,
					data.getAffairsTitle(),
					data.getAffairsContent(),
					java.sql.Date.valueOf(data.getAffairsDate1()), 
					java.sql.Date.valueOf(data.getAffairsDate2())
				);
			}else if(source == 1) {
				affairsDao.updateAffairsCopyDao(
					harId,
					data.getAffairsTitle(),
					data.getAffairsContent(),
					java.sql.Date.valueOf(data.getAffairsDate1()), 
					java.sql.Date.valueOf(data.getAffairsDate2())
				);
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public void updateAffairsCopyReviewService(int harId, int review, int reviseId) {
		affairsDao.updateAffairsCopyReviewDao(harId, review, reviseId);
	}
}
