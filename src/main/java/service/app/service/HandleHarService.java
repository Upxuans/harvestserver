package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.HandleHarDao;
import service.app.domain.AuthorsModel;
import service.app.domain.OthersModel;
import service.app.domain.SachModel;
import service.app.domain.TachModel;

@Service
public class HandleHarService {
	
	@Autowired
	HandleHarDao harhandleDao;

	//删除教师成果（使成果不显示tachload=0）
	public Boolean setTachLoadService(int userId, int harType, int harId, int source, int load) {
		try {
			int unload = 0;
			if(load == 0)
				unload = 1;
			else if(load == 1)
				unload = 0;
			harhandleDao.setTachLoadDao(userId, harType, harId, source, load, unload);
			return true;
		} catch (Exception e){
//			System.out.println(e);
			return false;
		}
	}
	
	//删除学生成果（使成果不显示tachload=0）
	public Boolean setSachLoadService(int userId, int harType, int harId, int source, int load) {
		try {
			int unload = 0;
			if(load == 0)
				unload = 1;
			else if(load == 1)
				unload = 0;
			harhandleDao.setSachLoadDao(userId, harType, harId, source, load, unload);
			return true;
		} catch (Exception e){
			System.out.println(e);
			return false;
		}
	}

	//找到authors的id；验证是否是teacher表中的作者，若是则插入tach表，若不是创建新作者后插入tach关系表
	public Boolean insertCorrelation(int userType, int userId, List<AuthorsModel> authors, int harType, int harId, int source) {
		
		Boolean flag = true;
		try {
			for(int i=0; i<authors.size(); i++) {
				int load=0, firstAuthor=0, belong=0;
				AuthorsModel model = authors.get(i);
				if(model.getNature1()) firstAuthor = 1; //是否是第一作者
				if(model.getDegree() == 1) {//作者身份是教师
					Integer authorId = harhandleDao.selectTeacherByNameDao(model.getName());//寻找teacher表中是否有该名，有则返回其id否则为null
					if(authorId != null) {//找到user
						if(userType == 1 && authorId == userId) load = 1;//自己录入的文章是要显示的(身份和id都要对应)
						harhandleDao.insertTach(authorId, harType, harId, firstAuthor, load, source);
					}else {//没找到user，先插入user并获userid再插入。
						harhandleDao.insertTeacher(model.getName());
						authorId = harhandleDao.getTeacherInsertId();
						harhandleDao.insertTach(authorId, harType, harId, firstAuthor, 0, source);
					}
				}else if(model.getDegree() == 2) {//作者身份是学生
					if(model.getNature2()) belong = 1;//对于学生来说是否是毕业条件归属人
					Integer authorId = harhandleDao.selectStudentByNameDao(model.getName());
					if(authorId != null) {
						harhandleDao.insertSach(authorId, harType, harId, firstAuthor, belong, 0, source);//学生刚录入的时候是没有加载0的，待审核1的。
					}else {
						harhandleDao.insertStudent(model.getName());
						authorId = harhandleDao.getStudentInsertId();
						harhandleDao.insertSach(authorId, harType, harId, firstAuthor, belong, 0, source);
					}
				}else if(model.getDegree() == 3) {//作者身份是others
					harhandleDao.insertOthers(model.getName(), harType, harId, firstAuthor, source);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	//更新的时候先删除再插入好了
	public Boolean updateCorrelation(int userType, int userId, List<AuthorsModel> authorsModel, int harType, int harId, int source) {

		System.out.println(userType + "," + userId + "," + harType + "," + harId + "," + source);

		Boolean flag = true;
		int i, j;
		try {
			//找到所有的(harType, harId, source)然后逐个处理增删改查，添加used主要是为了删除做准备。
			List<TachModel> tachModels = harhandleDao.getTachNotesDao(harType, harId, source);
			List<SachModel> sachModels = harhandleDao.getSachNotesDao(harType, harId, source);
			List<OthersModel> othersModels = harhandleDao.getOthersNotesDao(harType, harId, source);
			
//			System.out.println("tachModels:");
//			for(i=0; i<tachModels.size(); i++)
//				System.out.println(tachModels.get(i).getTachUserId() + "," + tachModels.get(i).getTachHarType() + "," + tachModels.get(i).getTachHarId() + "," + tachModels.get(i).getUsed());
//			System.out.println("sachModels:");
//			for(i=0; i<sachModels.size(); i++)
//				System.out.println(sachModels.get(i).getSachUserId() + "," + sachModels.get(i).getSachHarType() + "," + sachModels.get(i).getSachHarId() + "," + sachModels.get(i).getUsed());
//			System.out.println("othersModels:");
//			for(i=0; i<othersModels.size(); i++)
//				System.out.println(othersModels.get(i).getOthersName() + "," + othersModels.get(i).getOthersHarType() + "," + othersModels.get(i).getOthersHarId() + "," + othersModels.get(i).getUsed());
//			System.out.println("-------------------------");
			
			for( i=0; i<authorsModel.size(); i++) {
				int firstAuthor=0, belong=0;
				boolean nullId = false;
				boolean findIt = false;
				AuthorsModel model = authorsModel.get(i);
				if(model.getNature1()) firstAuthor = 1; //是否是第一作者
				if(model.getDegree() == 1) {//作者身份是教师  到tachModel中找
					Integer authorId = harhandleDao.selectTeacherByNameDao(model.getName());//寻找teacher表中是否有该名，有则返回其id否则为null
					if(authorId != null) {//找到user就判断是否是已插入在关系表中
						for(j=0; j<tachModels.size(); j++) {//遍历结束还没找打lg=false则需要insertTach()
							TachModel mod = tachModels.get(j);
							if(authorId == mod.getTachUserId()) {//找到已插入在关系表中未删需要修改的关系*******************************************************updateTach***/
								findIt = true;
								mod.setUsed(1);
								harhandleDao.updateTach(firstAuthor, authorId, harType, harId, source);
								break;
							}
						}
					}else {//在user表中没找到user就新建一个，并获得authorId,且肯定是新user需要insertTach()
						nullId = true;
						harhandleDao.insertTeacher(model.getName());
						authorId = harhandleDao.getTeacherInsertId();
					}
					if(!findIt || nullId) //遍历了一遍却没有找到，说明是新增的作者，需要插入*||*在user表中没找到user就新建一个，并获得id,且肯定是新user需要插入*******************insertTach******/
						harhandleDao.insertTach(authorId, harType, harId, firstAuthor, 0, source);
				}else if(model.getDegree() == 2) {//作者身份是学生  到sachModel中找
					if(model.getNature2()) belong = 1;//对于学生来说是否是毕业条件归属人
					Integer authorId = harhandleDao.selectStudentByNameDao(model.getName());
					if(authorId != null) {
						for(j=0; j<sachModels.size(); j++) {//遍历结束还没找打lg=false则需要insertTach()
							SachModel mod = sachModels.get(j);
							if(authorId == mod.getSachUserId()) {//找到已插入在关系表中未删需要修改的关系*******************************************************updateTach***/
								findIt = true;
								mod.setUsed(1);
								harhandleDao.updateSach(firstAuthor, belong, authorId, harType, harId, source);
								break;
							}
						}
					}else {
						nullId = true;
						harhandleDao.insertStudent(model.getName());
						authorId = harhandleDao.getStudentInsertId();
					}
					if(!findIt || nullId) //遍历了一遍却没有找到，说明是新增的作者，需要插入*||*在user表中没找到user就新建一个，并获得id,且肯定是新user需要插入*********************insertSach******/
						harhandleDao.insertSach(authorId, harType, harId, firstAuthor, belong, 0, source);
				}else if(model.getDegree() == 3) {//作者身份是others 到othersModel中找
					for(j=0; j<othersModels.size(); j++) {//遍历结束还没找打lg=false则需要insertTach()
						OthersModel mod = othersModels.get(j);
						if(model.getName().equals(mod.getOthersName())) {//找到已插入在关系表中未删需要修改的关系*****************************************************updateSach***/
							findIt = true;
							mod.setUsed(1);
							harhandleDao.updateOthers(firstAuthor, mod.getOthersId(), harType, harId, source);
							break;
						}
					}
					if(!findIt) //遍历了一遍却没有找到，说明是新增的作者，需要插入*||*在user表中没找到user就新建一个，并获得id,且肯定是新user需要插入*******************************insertOthers******/
						harhandleDao.insertOthers(model.getName(), harType, harId, firstAuthor, source);
				}
			}
			
			//剩下的used未被渲染为1仍为0将之则删除
			for(i=0; i<tachModels.size(); i++) {
				TachModel mod = tachModels.get(i);
				if(mod.getUsed() == 0) //***********************************************************************************************************deleteTach***/
					harhandleDao.deleteTach(mod.getTachUserId(), harType, harId, source);
			}
			for(i=0; i<sachModels.size(); i++) {
				SachModel mod = sachModels.get(i);
				if(mod.getUsed() == 0) //***********************************************************************************************************deleteTach***/
					harhandleDao.deleteSach(mod.getSachUserId(), harType, harId, source);
			}
			for(i=0; i<othersModels.size(); i++) {
				OthersModel mod = othersModels.get(i);
				if(mod.getUsed() == 0) //***********************************************************************************************************deleteTach***/
					harhandleDao.deleteOthers(mod.getOthersId(), harType, harId, source);
			}
			
//			harhandleDao.delTachNotesDao(harType, harId, source);
//			harhandleDao.delSachNotesDao(harType, harId, source);
//			harhandleDao.delOthersNotesDao(harType, harId, source);
//			insertCorrelation(handleType, userType, userId, authors, harType, harId, source);
			
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}
}
