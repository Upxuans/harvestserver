/**
 * By Upxuan
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.SoftwareDao;
import service.app.domain.SoftwareModel;

@Service
public class SoftwareService {

	@Autowired
	SoftwareDao softwareDao;

	public List<SoftwareModel> getSoftwareDataFromTeacherService(int userId, int load) {
		List<SoftwareModel> softwareModel = softwareDao.getSoftwareDataFromTeacherDao(userId, load);
		List<SoftwareModel> copyModel = softwareDao.getSoftwareCopyFromTeacherDao(userId, load);
		if(copyModel != null) 
			softwareModel.addAll(copyModel);
		return softwareModel;
	}

	public List<SoftwareModel> getSoftwareDataFromStudentService(int userId, int load) {
		List<SoftwareModel> softwareModel = softwareDao.getSoftwareDataFromStudentDao(userId, load);
		List<SoftwareModel> copyModel = softwareDao.getSoftwareCopyFromStudentDao(userId, load);
		if(copyModel != null) 
			softwareModel.addAll(copyModel);
		return softwareModel;
	}

	public int insertSoftwareToStockService(int userType, int userId, SoftwareModel data) {
		int d = 0, id = 0;
		if(data.getSoftwareAcquireType().equals("原始取得")) 
			d = 1;
		else if(data.getSoftwareAcquireType().equals("继受取得")) 
			d = 2; 
		
		if(userType == 1 || userType == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
			softwareDao.insertSoftwareToMyStockDao(data.getSoftwareName(), data.getSoftwareNum(), d);
			id = softwareDao.getSoftwareInsertNoteId();
		}else if(userType == 2) {
			softwareDao.insertSoftwareToMyCopyStockDao(data.getSoftwareName(), data.getSoftwareNum(), d, 1, userId);
			id = softwareDao.getSoftwareCopyInsertNoteId();
		}
		return id;
	}

	public Boolean updateSoftwareService(SoftwareModel data, int harId, int source) {
		
		Boolean flag = true;
		int d = 0;
		if(data.getSoftwareAcquireType().equals("原始取得")) 
			d = 1;
		else if(data.getSoftwareAcquireType().equals("继受取得")) 
			d = 2; 
		try {
			if(source == 0) {//*重构的时候可以合并冗余，只是表名不同，可以设置表名为参数*
				softwareDao.updateSoftwareDao(harId, data.getSoftwareName(), data.getSoftwareNum(), d);
			}else if(source == 1) {
				softwareDao.updateSoftwareCopyDao(harId, data.getSoftwareName(), data.getSoftwareNum(), d);
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public void updateSoftwareCopyReviewService(int harId, int review, int reviseId) {
		softwareDao.updateSoftwareCopyReviewDao(harId, review, reviseId);
	}
}
