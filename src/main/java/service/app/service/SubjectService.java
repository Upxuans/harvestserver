/**
 * By Upxuan
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.SubjectDao;
import service.app.domain.SubjectModel;

@Service
public class SubjectService {

	@Autowired
	SubjectDao subjectDao;

	public List<SubjectModel> getSubjectDataFromTeacherService(int userId, int load) {
		List<SubjectModel> subjectModel = subjectDao.getSubjectDataFromTeacherDao(userId, load);
		List<SubjectModel> copyModel = subjectDao.getSubjectCopyFromTeacherDao(userId, load);
		if(copyModel != null) 
			subjectModel.addAll(copyModel);
		return subjectModel;
	}

	public List<SubjectModel> getSubjectDataFromStudentService(int userId, int load) {
		List<SubjectModel> subjectModel = subjectDao.getSubjectDataFromStudentDao(userId, load);
		List<SubjectModel> copyModel = subjectDao.getSubjectCopyFromStudentDao(userId, load);
		if(copyModel != null) 
			subjectModel.addAll(copyModel);
		return subjectModel;
	}

	public int insertSubjectToStockService(int userType, int userId, SubjectModel data) {
		
		int d = 0, id = 0;
		if(data.getSubjectStatus().equals("已出版")) 
			d = 1;
		else if(data.getSubjectStatus().equals("待出版")) 
			d = 2; 
		
		if(userType == 1 || userType == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
			subjectDao.insertSubjectToMyStockDao(
				data.getSubjectTitle(),
				data.getSubjectBookName(),
				d, 
				data.getSubjectISBN(),
				data.getSubjectPublish(),
				java.sql.Date.valueOf(data.getSubjectPublishDate()),
				data.getSubjectDOI()
			);
			id = subjectDao.getSubjectInsertNoteId();
		}else if(userType == 2) {
			subjectDao.insertSubjectToMyCopyStockDao(
				data.getSubjectTitle(),
				data.getSubjectBookName(),
				d, 
				data.getSubjectISBN(),
				data.getSubjectPublish(),
				java.sql.Date.valueOf(data.getSubjectPublishDate()),
				data.getSubjectDOI(),
				1,
				userId
			);
			id = subjectDao.getSubjectCopyInsertNoteId();
		}
		return id;
	}

	public Boolean updateSubjectService(SubjectModel data, int harId, int source) {

		Boolean flag = true;
		int d = 0, id = 0;
		if(data.getSubjectStatus().equals("已出版")) 
			d = 1;
		else if(data.getSubjectStatus().equals("待出版")) 
			d = 2; 
		try {
			if(source == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
				subjectDao.updateSubjectDao(
					harId,
					data.getSubjectTitle(),
					data.getSubjectBookName(),
					d, 
					data.getSubjectISBN(),
					data.getSubjectPublish(),
					java.sql.Date.valueOf(data.getSubjectPublishDate()),
					data.getSubjectDOI()
				);
			}else if(source == 1) {
				subjectDao.updateSubjectCopyDao(
					harId,
					data.getSubjectTitle(),
					data.getSubjectBookName(),
					d, 
					data.getSubjectISBN(),
					data.getSubjectPublish(),
					java.sql.Date.valueOf(data.getSubjectPublishDate()),
					data.getSubjectDOI()
				);
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public void updateSubjectCopyReviewService(int harId, int review, int reviseId) {
		subjectDao.updateSubjectCopyReviewDao(harId, review, reviseId);
	}

}
