/**
 * By Upxuan
 * 
 * Created in 2018/12/5
 */
package service.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.app.service.ReviseInfoService;
import service.app.tramodel.ReviseInfoRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;

@Controller
public class ReviseInfoController {
	
	@Autowired
	ReviseInfoService reviseInfoService;
	
	@RequestMapping("/reviseBaseInfo")
	@ResponseBody
	public BaseResponse reviseBaseInfo(HttpServletResponse response, ReviseInfoRequest data) {

		BaseResponse resp = new BaseResponse();
		boolean flag = false;
		if(data.getUserType() == 1) {
			flag = reviseInfoService.updateInfoInTeacherService(data.getUserId(), data.getLink(), data.getTel(), data.getEmail(), data.getDirection());
		} else if (data.getUserType() == 2) {
			flag = reviseInfoService.updateInfoInStudentService(data.getUserId(), data.getTel(), data.getEmail(), data.getDirection());
		}
		
		if (flag) 
			resp.setErrCode(ErrCode.SETTING_OK);
		else 
			resp.setErrCode(ErrCode.SETTING_ERR);
		
		return resp;
	}
	
	@RequestMapping("/revisePwd")
	@ResponseBody
	public BaseResponse revisePwd(HttpServletResponse response, ReviseInfoRequest data) {

		BaseResponse resp = new BaseResponse();
		int flag = 2;
		String password = reviseInfoService.findPwdByIdService(data.getUserType(), data.getUserId());
		if(password.equals(data.getPass())) {
			flag = reviseInfoService.updatePwdService(data.getUserType(), data.getUserId(), data.getCheckPass());
		}else {
			flag = 3;
		}
		
		if(flag == 1) 
			resp.setErrCode(ErrCode.SETTING_OK);
		else if(flag == 2)
			resp.setErrCode(ErrCode.SETTING_ERR);
		else if(flag == 3)
			resp.setErrCode(ErrCode.SETTING_PASS_ERR);
		return resp;
	}
}
