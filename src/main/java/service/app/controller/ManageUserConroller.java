/**
 * By Upxuan
 * 
 * Created in 2018/12/21
 */
package service.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.app.service.ManageUserService;
import service.app.tramodel.BaseRequest;
import service.app.tramodel.ManageUserRequest;
import service.app.tramodel.UserRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;
import service.app.tramodel.response.UserResponse;

@Controller
public class ManageUserConroller {

	@Autowired
	ManageUserService manageUserService;
	
	@RequestMapping("/getTeacher")
	@ResponseBody
	public UserResponse getTeacherController (HttpServletResponse response, BaseRequest data) {
		
		UserResponse resp = new UserResponse();
		boolean flag = true;
			
		try {
			resp.setTeacherModels(manageUserService.getTeacherService());
		} catch (Exception e) {
//			System.out.println(e);
			flag = false;
		}
		
		if(flag)resp.setErrCode(ErrCode.SETTING_OK); else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/getStudent")
	@ResponseBody
	public UserResponse getStudentController (HttpServletResponse response, BaseRequest data) {
		
		UserResponse resp = new UserResponse();
		boolean flag = true;
			
		try {
			resp.setStudentModels(manageUserService.getStudentService());
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		
		if(flag)resp.setErrCode(ErrCode.SETTING_OK); else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/delTeacher")
	@ResponseBody
	public UserResponse delTeacherController (@RequestBody UserRequest data) {
		
		UserResponse resp = new UserResponse();
		boolean flag = true;
		flag = manageUserService.delTeacherService(data.getIdList());
		
		if(flag)resp.setErrCode(ErrCode.SETTING_OK); else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/delStudent")
	@ResponseBody
	public UserResponse delStudentController (@RequestBody UserRequest data) {
		
		UserResponse resp = new UserResponse();
		boolean flag = true;
		flag = manageUserService.delStudentService(data.getIdList());
		
		if(flag)resp.setErrCode(ErrCode.SETTING_OK); else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/handleTeacher")
	@ResponseBody
	public BaseResponse handleTeacherController(@RequestBody ManageUserRequest data) {

		BaseResponse resp = new BaseResponse();
		int flag = 0;
		if(data.getHandleType() == 0) {//add
			Integer id = manageUserService.getTeacherUsernameService(data.getTeacherModel().getUsername());
			if(id!=null) flag = 2; //用户名重复
			else {
				flag = manageUserService.handleTeacherService(data.getHandleType(), data.getTeacherModel());
			}
		}else {//update
			//可以加一个排除未更改的方法
			flag = manageUserService.handleTeacherService(data.getHandleType(), data.getTeacherModel());
		}
		
		if(flag==0)resp.setErrCode(ErrCode.SETTING_OK); 
		else if(flag==2) resp.setErrCode(ErrCode.SETTING_REG_USER_ERR); 
		else resp.setErrCode(ErrCode.SETTING_ERR);
		
		return resp;
	}
	
	@RequestMapping("/handleStudent")
	@ResponseBody
	public BaseResponse handleStudentController(@RequestBody ManageUserRequest data) {
		BaseResponse resp = new BaseResponse();
		int flag = 0;
//		System.out.println(data.getHandleType() + ", " + data.getStudentModel());
		if(data.getHandleType() == 0) {//add
			Integer id = manageUserService.getStudentUsernameService(data.getStudentModel().getUsername());
			if(id!=null) flag = 2;//用户名重复
			else {
				flag = manageUserService.handleStudentService(data.getHandleType(), data.getStudentModel());
			}
		}else {//update
			flag = manageUserService.handleStudentService(data.getHandleType(), data.getStudentModel());
//			System.out.println(flag);
		}
		
		if(flag==0)resp.setErrCode(ErrCode.SETTING_OK); 
		else if(flag==1) resp.setErrCode(ErrCode.SETTING_ERR);
		else if(flag==2) resp.setErrCode(ErrCode.SETTING_REG_USER_ERR); 
		else if(flag==3) resp.setErrCode(ErrCode.SETTING_NONE_USER_ERR);
		
		return resp;
	}
}
