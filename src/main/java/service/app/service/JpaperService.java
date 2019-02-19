/**
 * By Upxuan
 * 
 * Created in 2018/11/24
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.JpaperDao;
import service.app.domain.JpaperModel;

@Service
public class JpaperService {

	@Autowired
	JpaperDao jpaperDao;

	//根据（老师/学生）的userid获得所有jpaper成果
	public List<JpaperModel> getJpaperDataFromTeacherService(int userid, int load) {
		List<JpaperModel> jpaperModel = jpaperDao.getJpaperDataFromTeacherDao(userid, load);
		List<JpaperModel> copyModel = jpaperDao.getJpaperCopyFromTeacherDao(userid, load);
		if(copyModel != null) 
			jpaperModel.addAll(copyModel);
		return jpaperModel;
	}
	
	public List<JpaperModel> getJpaperDataFromStudentService(int userid, int load) {
		List<JpaperModel> jpaperModel = jpaperDao.getJpaperDataFromStudentDao(userid, load);
		List<JpaperModel> copyModel = jpaperDao.getJpaperCopyFromStudentDao(userid, load);
		if(copyModel != null) 
			jpaperModel.addAll(copyModel);
		return jpaperModel;
	}
	
	//在jpaper表中手动插入记录，返回jpaperId
	public int insertJpaperToStockService(int userType, int userId, JpaperModel data) {
		
		int d2 = 0, id = 0;
		String d1 = data.getJpaperStatus();
		
		if(d1.equals("已发表")) d2 = 1;
		else if(d1.equals("已接收")) d2 = 2; 
		
		if(userType == 1 || userType == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
			jpaperDao.insertJpaperToMyStockDao(
				data.getJpaperType1(), 
				data.getJpaperType2(), 
				data.getJpaperType3(), 
				data.getJpaperTitle(), 
				data.getJpaperPaperName(), 
				d2, 
				java.sql.Date.valueOf(data.getJpaperPublishDate()), 
				data.getJpaperReel(), 
				data.getJpaperIssue(), 
				data.getJpaperPage1(), 
				data.getJpaperPage2(), 
				data.getJpaperDoi()
			);
			id = jpaperDao.getJpaperInsertNoteId();
		}else if(userType == 2) {
			jpaperDao.insertJpaperToMyCopyStockDao(
				data.getJpaperType1(), 
				data.getJpaperType2(), 
				data.getJpaperType3(), 
				data.getJpaperTitle(), 
				data.getJpaperPaperName(), 
				d2, 
				java.sql.Date.valueOf(data.getJpaperPublishDate()), 
				data.getJpaperReel(), 
				data.getJpaperIssue(), 
				data.getJpaperPage1(), 
				data.getJpaperPage2(), 
				data.getJpaperDoi(),
				1,
				userId
			);
			id = jpaperDao.getJpaperCopyInsertNoteId();
		}
		return id;
	}

	public boolean updateJpaperService(JpaperModel data, int harId, int source) {

		Boolean flag = true;
		int d2 = 0;
		String d1 = data.getJpaperStatus();
		
		if(d1.equals("已发表")) d2 = 1;
		else if(d1.equals("已接收")) d2 = 2; 
		try {
			if(source == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
				jpaperDao.updateJpaperDao(
					harId,
					data.getJpaperType1(), 
					data.getJpaperType2(), 
					data.getJpaperType3(), 
					data.getJpaperTitle(), 
					data.getJpaperPaperName(), 
					d2, 
					java.sql.Date.valueOf(data.getJpaperPublishDate()),
					data.getJpaperReel(), 
					data.getJpaperIssue(), 
					data.getJpaperPage1(), 
					data.getJpaperPage2(), 
					data.getJpaperDoi()
				);
			}else if(source == 1) {
				jpaperDao.updateJpaperCopyDao(
					harId,
					data.getJpaperType1(), 
					data.getJpaperType2(), 
					data.getJpaperType3(), 
					data.getJpaperTitle(), 
					data.getJpaperPaperName(), 
					d2, 
					java.sql.Date.valueOf(data.getJpaperPublishDate()), 
					data.getJpaperReel(), 
					data.getJpaperIssue(), 
					data.getJpaperPage1(), 
					data.getJpaperPage2(), 
					data.getJpaperDoi()
				);
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public void updateJpaperCopyReviewService(int harId, int review, int reviseId) {
		jpaperDao.updateJpaperCopyReviewDao(harId, review, reviseId);
	}
}
