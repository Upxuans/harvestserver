/**
 * By Upxuan
 * 
 * Created in 2019/4/2
 * */
package service.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.app.service.WebMaintainService;
import service.app.tramodel.BaseRequest;
import service.app.tramodel.WebMaintainRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;
import service.app.tramodel.response.WebMaintainResponse;

@Controller
@RestController
public class WebMaintainController {
	
	@Autowired
	WebMaintainService webMaintainService;
	
	@RequestMapping("getAchievement")
	public WebMaintainResponse getAchievementController(HttpServletResponse response, BaseRequest data) {
		WebMaintainResponse resp = new WebMaintainResponse();
		boolean flag = true; 
		
		try {
			resp.setAchievement(webMaintainService.getAchievementService());
		} catch (Exception e) {
			flag = false;
		}
		
		if(flag)resp.setErrCode(ErrCode.SETTING_OK); else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/updateWebmaintain")
	public BaseResponse updateWebmaintainController(@RequestBody WebMaintainRequest data) {
		BaseResponse resp = new BaseResponse();
		boolean flag = true; 
//		System.out.println(data.toString());
		
		try {
			webMaintainService.updateAchievementService(data.getProject1(), data.getProject2(), data.getPaper());
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		
		if(flag)resp.setErrCode(ErrCode.SETTING_OK); else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
}
