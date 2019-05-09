/**
 * By Upxuan
 * 
 * Created in 2018/12/22
 */
package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import service.app.dao.HandleHarDao;
import service.app.dao.ManageUserDao;
import service.app.domain.StudentModel;
import service.app.domain.TeacherModel;
import service.app.util.HarvestUtil;

@Service
public class ManageUserService {
	
	@Value("${default.password}")
	private String defaultPassword;
	
	@Autowired
	ManageUserDao manageUserDao;
	
	@Autowired
	HarvestUtil harvestUtil;
	
	@Autowired
	HandleHarDao harhandleDao;

	public List<TeacherModel> getTeacherService() {
		List<TeacherModel> teacherModels = manageUserDao.getTeacherDao();
		return teacherModels;
	}
	
	public List<StudentModel> getStudentService() {
		List<StudentModel> studentModels = manageUserDao.getStudentDao();
		for( int i=0; i<studentModels.size(); i++) {
			StudentModel model = studentModels.get(i);
			model.setFirst(harvestUtil.getHarReviseName(Integer.valueOf(model.getFirst())));
			model.setSecond(harvestUtil.getHarReviseName(Integer.valueOf(model.getSecond())));
			if(Integer.parseInt(model.getDegree()) == 0)
				model.setDegree("学硕");
	    	else
	    		model.setDegree("专硕");
		}
		return studentModels;
	}

	public boolean delTeacherService(List<Integer> idList) {
		boolean flag = true;
		try {
			for( int i=0; i<idList.size(); i++) {
				manageUserDao.delTeacherDao(idList.get(i));
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public boolean delStudentService(List<Integer> idList) {
		boolean flag = true;
		try {
			for( int i=0; i<idList.size(); i++) {
				manageUserDao.delStudentDao(idList.get(i));
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public int handleTeacherService(int handleType, TeacherModel data) {
		int flag = 0;
		try {
			if(handleType == 0) {
				String password = defaultPassword;//设置初始密码为
				manageUserDao.addTeacherDao(data.getUsername(), password, data.getName(), data.getTitle(), data.getTeam(), data.getTel(), data.getEmail(), data.getDirection(), data.getLink());
			}else {
				manageUserDao.updateTeacherDao(data.getId(), data.getName(), data.getTitle(), data.getTeam(), data.getTel(), data.getEmail(), data.getDirection(), data.getLink());
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = 1;
		}
		return flag;
	}
	
	public int handleStudentService(int handleType, StudentModel data) {
		int flag = 0;
		Integer firstId = null, secondId = null;
		try {
			firstId = harhandleDao.selectTeacherByNameDao(data.getFirst());
			if(firstId == null) flag = 3;
			else {
				if(data.getSecond() != null && !data.getSecond().replaceAll(" ", "").equals("")) {
					secondId = harhandleDao.selectTeacherByNameDao(data.getSecond());
					if(secondId == null) flag = 3;
				}else secondId = -1;
				if(flag==0) {
					if(handleType == 0) {
						data.setPassword(defaultPassword);//默认密码
						manageUserDao.addStudentDao(data.getUsername(), data.getPassword(), data.getName(), Integer.valueOf(data.getDegree()), data.getTeam(), firstId, secondId, data.getTel(), data.getEmail(), data.getDirection(), true, true, true);
					}else {
						manageUserDao.updateStudentDao(data.getId(), data.getName(), Integer.valueOf(data.getDegree()), data.getTeam(), firstId, secondId, data.getTel(), data.getEmail(), data.getDirection());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = 1;
		}
		return flag;
	}

	public Integer getTeacherUsernameService(String username) {
		Integer id = manageUserDao.getTeacherUsernameDao(username);
		return id;
	}

	public Integer getStudentUsernameService(String username) {
		Integer id = manageUserDao.getStudentUsernameDao(username);
		return id;
	}

	public boolean generateResumeForTeacherService(int userId, TeacherModel teacherModel) {
		boolean flag = true;
		try {
			manageUserDao.generateResumeForTeacherDao(userId, teacherModel.getRewards(), teacherModel.getAcademicwork(), teacherModel.getIntroduction());
		} catch (Exception e) {
//			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public TeacherModel getTeacherMsgService(String username) {
		TeacherModel model = manageUserDao.getTeacherMsgDao(username);
		return model;
	}

	public int getTeacherIdByUsernameService(String username) {
		int id = manageUserDao.getTeacherIdByUsernameDao(username);
		return id;
	}

	public int getStudentIdByUsernameService(String username) {
		int id = manageUserDao.getStudentIdByUsernameDao(username);
		return id;
	}
}
