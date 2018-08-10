/**
 * 
 */
/**
 * @author Bran
 *
 */
package service.app.controller;
import service.app.domain.ManagerInfo;
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
	LogService ls;
	
	
	@RequestMapping("/login.json")
	@ResponseBody
	public LogInResponse login(HttpServletResponse response,RequestData data){
		LogInResponse resp = new LogInResponse();
		ManagerInfo managerInfo = ls.getLogInData(data.getUsername());
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		if(managerInfo==null||
				!managerInfo.getPassword().equals(data.getPassword())) {
			resp.setErrCode(ErrCode.LOGIN_ERR_INFO);//用户名或密码错误
//			resp.setToken();  //搁置起来+
			
		}
			
		else
			{
				resp.setErrCode(ErrCode.LOGIN_OK);//登录成功
				resp.setManagerInfo(managerInfo);
			}
		return resp;
	}
}