package service.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.HarvestDao;
import service.app.domain.AuthorsModel;
import service.app.domain.OthersModel;
import service.app.domain.SachModel;
import service.app.domain.TachModel;

@Service
public class HarvestService {
	
	@Autowired
	HarvestDao harvestDao;
	
	//从tach获得(老师/学生/非实验室人员)成果的作者
	public List<String> getTeacherAuthorsByIdService(int harType, int harId, int source) {
		List<String> teacherStr = harvestDao.getAuthorsByTeacherIdDao(harType, harId, source);
		return teacherStr;
	}
	
	public List<String> getStudentAuthorsByIdService(int harType, int harId, int source) {
		List<String> studentStr = harvestDao.getAuthorsByStudentIdDao(harType, harId, source);
		return studentStr;
	}

	public List<String> getOthersAuthorsByIdService(int harType, int harId, int source) {
		List<String> othersStr = harvestDao.getAuthorsByOthersIdDao(harType, harId, source);
		return othersStr;
	}
	
	//找到一篇文章的第一作者(注意：可能在关系表中会重复1,1代表的可能是har表也有可能是harcopy表)
	public String getFirstAuthorService(int harType, int harId, int source) {
		String name = "";
		name = harvestDao.getFirstAuthorInTachDao(harType, harId, source);
		if(name == null) {//在tach表中没找到就到sach中找
			name = harvestDao.getFirstAuthorInSachDao(harType, harId, source);
			if(name == null) {//在sach表中没找到就到others中找
				name = harvestDao.getFirstAuthorInOthersDao(harType, harId, source);
				if(name == null) name = "";
			}
		}
		return name;
	}

	//找到毕业条件归属者
	public String getBelongService(int harType, int harId, int source) {
		String belong = harvestDao.getBelongInSachDao(harType, harId, source);
		if(belong == null) belong = "";
		return belong;
	}

	//找到提交者（学生）
	public String getHarSubmitNameService(int id) {
		String submitName = harvestDao.getHarSubmitNameDao(id);
		if(submitName == null) submitName = "";
		return submitName;
	}
	
	//找到审核者（老师）
	public String getHarReviseNameService(int id) {
		String name = harvestDao.getHarReviseNameDao(id);
		if(name == null) name = "";
		return name;
	}

	//删除关系source=0/1（harvest/harvest_copy）的关系
	public void delHarvestRelationService(int harType, int harId, int source) {
		harvestDao.delTachDao(harType, harId, source);
		harvestDao.delSachDao(harType, harId, source);
		harvestDao.delOthersDao(harType, harId, source);
	}

	//获得作者的List用于编辑中使用
	public List<AuthorsModel> getAuthorsListService(int harType, int harId, int source) {
		
		List<AuthorsModel> models = new ArrayList<AuthorsModel>();
		
		List<TachModel> tachModels = harvestDao.getTachAuthorsListDao(harType, harId, source);
		for(int i=0; i<tachModels.size(); i++) {
			AuthorsModel model = new AuthorsModel();
			model.setName(getHarReviseNameService(tachModels.get(i).getTachUserId()));
			model.setDegree(1);
			if(tachModels.get(i).getTachFirstAuthor() == 1) model.setNature1(true);
			models.add(model);
		}
		List<SachModel> sachModels = harvestDao.getSachAuthorsListDao(harType, harId, source);
		for(int i=0; i<sachModels.size(); i++) {
			AuthorsModel model = new AuthorsModel();
			model.setName(getHarSubmitNameService(sachModels.get(i).getSachUserId()));
			model.setDegree(2);
			if(sachModels.get(i).getSachFirstAuthor() == 1) model.setNature1(true);
			if(sachModels.get(i).getSachBelong() == 1) model.setNature2(true);
			models.add(model);
		}
		List<OthersModel> othersModels = harvestDao.getOthersAuthorsListDao(harType, harId, source);
		for(int i=0; i<othersModels.size(); i++) {
			AuthorsModel model = new AuthorsModel();
			model.setName(othersModels.get(i).getOthersName());
			model.setDegree(3);
			if(othersModels.get(i).getOthersFirstAuthor() == 1) model.setNature1(true);
			models.add(model);
		}
		
//		for (int i = 0; i < models.size(); i++) 
//			System.out.println(models.get(i).getName());
		
		return models;
	}

}
