/**
 * By Upxuan
 *
 */
package service.app.controller;
import service.app.domain.ManagerModel;
import service.app.domain.StudentModel;
import service.app.domain.TeacherModel;
import service.app.service.LogService;
import service.app.tramodel.LogRequest;
import service.app.tramodel.response.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogController{
	
	@Autowired
	LogService logs;
	
	@RequestMapping("/login")
	@ResponseBody
	public LogInUserResponse logIn(HttpServletResponse response,LogRequest data){
		LogInUserResponse resp = new LogInUserResponse();
		if(data.getUsername().equals("Admin")) {
			ManagerModel managerModel = logs.getManagerService(data.getUsername());
			if(managerModel==null||!managerModel.getPassword().equals(data.getPassword())) {
				resp.setErrCode(ErrCode.LOGIN_ERR_INFO);//用户名或密码错误
			}else{
				resp.setErrCode(ErrCode.LOGIN_OK);//登录成功
				resp.setManagerModel(managerModel);
				resp.getManagerModel().setPassword("");
				resp.setType(0);
			}
		}else if(data.getUsername().length() == 5) {
			TeacherModel teacherModel = logs.getTeacherService(data.getUsername());
			if(teacherModel==null||!teacherModel.getPassword().equals(data.getPassword())) {
				resp.setErrCode(ErrCode.LOGIN_ERR_INFO);
			}else{
				resp.setErrCode(ErrCode.LOGIN_OK);
				resp.setTeacherModel(teacherModel);
				resp.getTeacherModel().setPassword("");
				resp.setType(1);
			}
		}else if(data.getUsername().length() == 9){
			StudentModel studentModel = logs.getStudentService(data.getUsername());
			if(studentModel==null||!studentModel.getPassword().equals(data.getPassword())) {
				resp.setErrCode(ErrCode.LOGIN_ERR_INFO);
			}else{
				resp.setErrCode(ErrCode.LOGIN_OK);
				resp.setStudentModel(studentModel);
				resp.getStudentModel().setPassword("");
				resp.setType(2);
			}
		}
		return resp;
	}
	
	
	//登出放在这儿。
}