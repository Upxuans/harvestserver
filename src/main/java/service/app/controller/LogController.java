/**
 * 
 */
/**
 * @author Bran
 *
 */
package service.app.controller;
import service.app.domain.ManagerModel;
import service.app.service.LogService;
import service.app.tramodel.ErrCode;
import service.app.tramodel.RequestData;
import service.app.tramodel.response.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController{
	
	@Autowired
	LogService logs;
	
	@RequestMapping("/login")
	@ResponseBody
	public LogInResponse logIn(HttpServletResponse response,RequestData data){
		//所有请求数据封装成为一个类放在tramodel中
		LogInResponse resp = new LogInResponse();
		//对response数据分类别封装放在tramodel中
		ManagerModel managerInfo = logs.getLogInData(data.getUsername());
//		response.setHeader("Access-Control-Allow-Origin", "*");
		if(managerInfo==null||
				!managerInfo.getPassword().equals(data.getPassword())) {
			resp.setErrCode(ErrCode.LOGIN_ERR_INFO);//用户名或密码错误
		}else{
			resp.setErrCode(ErrCode.LOGIN_OK);//登录成功
			resp.setManagerInfo(managerInfo);
		}
		return resp;
	}
	
	//登出放在这儿。
}