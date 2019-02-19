/**
 * By Upxuan
 * 
 * Created in 2018/12/4
 */
package service.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.app.service.HandleHarService;
import service.app.tramodel.HarvestRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;

@Controller
public class HandleHarController {

	@Autowired
	HandleHarService harhandleService;
	
	//成果展示页删除成果
	@RequestMapping("/delMyHarvest")
	@ResponseBody
	public BaseResponse delMyHarvestController(HttpServletResponse response, HarvestRequest data) {
//		System.out.println(data.getUserType() + ":" + data.getUserId() + ":" + data.getHarType() + ":" + data.getHarId());
		
		BaseResponse baseResp = new BaseResponse();
		Boolean flag = false;
		if(data.getUserType() == 1) 
			flag = harhandleService.setTachLoadService(data.getUserId(), data.getHarType(), data.getHarId(), data.getSource(), 0);
		else if(data.getUserType() == 2) 
			flag = harhandleService.setSachLoadService(data.getUserId(), data.getHarType(), data.getHarId(), data.getSource(), 0);
		
		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
}
