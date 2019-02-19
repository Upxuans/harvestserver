/**
 * By Upxuan
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.MpaperDao;
import service.app.domain.MpaperModel;

@Service
public class MpaperService {

	@Autowired
	MpaperDao mpaperDao;

	//根据（老师）的userid获得所有Mpaper成果
	public List<MpaperModel> getMpaperDataFromTeacherService(int userId, int load) {
		List<MpaperModel> mpaperModel = mpaperDao.getMpaperDataFromTeacherDao(userId, load);
		List<MpaperModel> copyModel = mpaperDao.getMpaperCopyFromTeacherDao(userId, load);
		if(copyModel != null) 
			mpaperModel.addAll(copyModel);
		return mpaperModel;
	}
	
	//根据（学生）的userid获得所有Mpaper成果
	public List<MpaperModel> getMpaperDataFromStudentService(int userId, int load) {
		List<MpaperModel> mpaperModel = mpaperDao.getMpaperDataFromStudentDao(userId, load);
		List<MpaperModel> copyModel = mpaperDao.getMpaperCopyFromStudentDao(userId, load);
		if(copyModel != null) 
			mpaperModel.addAll(copyModel);
		return mpaperModel;
	}

	public int insertMpaperToStockService(int userType, int userId, MpaperModel data) {
			
		int id = 0;
		if(userType == 1 || userType == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
			mpaperDao.insertMpaperToMyStockDao(
				data.getMpaperType1(), 
				data.getMpaperType2(), 
				data.getMpaperType3(), 
				data.getMpaperTitle(), 
				java.sql.Date.valueOf(data.getMpaperPublishDate()), 
				data.getMpaperPage1(),
				data.getMpaperPage2(),
				data.getMpaperMeetName(),
				java.sql.Date.valueOf(data.getMpaperMeetDate1()), 
				java.sql.Date.valueOf(data.getMpaperMeetDate2()), 
				data.getMpaperMeetPlace()
			);
			id = mpaperDao.getMpaperInsertNoteId();
		}else if(userType == 2) {
			mpaperDao.insertMpaperToMyCopyStockDao(
					data.getMpaperType1(), 
					data.getMpaperType2(), 
					data.getMpaperType3(), 
					data.getMpaperTitle(), 
					java.sql.Date.valueOf(data.getMpaperPublishDate()), 
					data.getMpaperPage1(),
					data.getMpaperPage2(),
					data.getMpaperMeetName(),
					java.sql.Date.valueOf(data.getMpaperMeetDate1()), 
					java.sql.Date.valueOf(data.getMpaperMeetDate2()), 
					data.getMpaperMeetPlace(),
					1,
					userId
			);
			id = mpaperDao.getMpaperCopyInsertNoteId();
		}
		return id;
	}

	public boolean updateMpaperService(MpaperModel data, int harId, int source) {
		
		Boolean flag = true;
		try {
			if(source == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
//				System.out.println("updateMpaper:" + data.getMpaperMeetDate1() + "," + data.getMpaperMeetDate2());
				mpaperDao.updateMpaperDao(
					harId,
					data.getMpaperType1(), 
					data.getMpaperType2(), 
					data.getMpaperType3(), 
					data.getMpaperTitle(), 
					java.sql.Date.valueOf(data.getMpaperPublishDate()), 
					data.getMpaperPage1(),
					data.getMpaperPage2(),
					data.getMpaperMeetName(),
					java.sql.Date.valueOf(data.getMpaperMeetDate1()), 
					java.sql.Date.valueOf(data.getMpaperMeetDate2()), 
					data.getMpaperMeetPlace()
				);
			}else if(source == 1) {
				mpaperDao.updateMpaperCopyDao(
					harId,
					data.getMpaperType1(), 
					data.getMpaperType2(), 
					data.getMpaperType3(), 
					data.getMpaperTitle(), 
					java.sql.Date.valueOf(data.getMpaperPublishDate()), 
					data.getMpaperPage1(),
					data.getMpaperPage2(),
					data.getMpaperMeetName(),
					java.sql.Date.valueOf(data.getMpaperMeetDate1()), 
					java.sql.Date.valueOf(data.getMpaperMeetDate2()), 
					data.getMpaperMeetPlace()
				);
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public void updateMpaperCopyReviewService(int harId, int review, int reviseId) {
		mpaperDao.updateMpaperCopyReviewDao(harId, review, reviseId);
	}
}
