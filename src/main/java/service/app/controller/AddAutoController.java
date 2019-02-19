/**
 * By Upxuan
 * 
 * Created in 2018/12/4
 */
package service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.app.service.HandleHarService;
import service.app.tramodel.AutoAddRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;

@Controller
public class AddAutoController {

	@Autowired
	HandleHarService handleHarService;
	
	@RequestMapping("/autoAdd")
	@ResponseBody
	public BaseResponse AutoAdd(@RequestBody AutoAddRequest data) {
		
		BaseResponse baseResp = new BaseResponse();
		Boolean flag = false;
		if(data.getUserType() == 1) {
			for(int i = 0; i<data.getSelections().size(); i++) {
				flag = handleHarService.setTachLoadService(data.getUserId(), data.getHarType(), data.getSelections().get(i).getId(), data.getSelections().get(i).getSource(), 1);
				if(!flag)break;
			}
		}else if(data.getUserType() == 2) {
			for(int i = 0; i<data.getSelections().size(); i++) {
				flag = handleHarService.setSachLoadService(data.getUserId(), data.getHarType(), data.getSelections().get(i).getId(), data.getSelections().get(i).getSource(), 1);
				if(!flag)break;
			}
		}
//		System.out.println(flag);
		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
}
