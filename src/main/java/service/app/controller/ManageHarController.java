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

import service.app.service.AuditService;
import service.app.service.ManageHarService;
import service.app.tramodel.BaseListRequest;
import service.app.tramodel.BaseRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;
import service.app.tramodel.response.HarvestResponse;
import service.app.util.HarvestUtil;

@Controller
public class ManageHarController {

	@Autowired
	ManageHarService manageHarService;
	
	@Autowired
	HarvestUtil harvestUtil;
	
	@Autowired
	AuditService auditService;
	
	@RequestMapping("/getHarvest")
	@ResponseBody
	public HarvestResponse getHarvestController(HttpServletResponse response, BaseRequest data) {
		
		HarvestResponse resp = new HarvestResponse();
		boolean flag = true;
		boolean gal = true;
		
		try {
			resp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(manageHarService.getAllJpaperService())));
			resp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(manageHarService.getAllMpaperService())));
			resp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(manageHarService.getAllPatentService())));
			resp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(manageHarService.getAllProjectService())));
			resp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(manageHarService.getAllSubjectService())));
			resp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(manageHarService.getAllSoftwareService())));
			resp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(manageHarService.getAllAffairsService())));
		} catch (Exception e) {
//			System.out.println(e);
			flag = false;
		}
		
		if(flag)resp.setErrCode(ErrCode.SETTING_OK); else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/getHarvestCopy")
	@ResponseBody
	public HarvestResponse getHarvestCopyController(HttpServletResponse response, BaseRequest data) {
		
		HarvestResponse resp = new HarvestResponse();
		boolean flag = true;
		boolean gal = true;
		
		try {
			resp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(manageHarService.getAllJpaperCopyService())));
			resp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(manageHarService.getAllMpaperCopyService())));
			resp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(manageHarService.getAllPatentCopyService())));
			resp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(manageHarService.getAllProjectCopyService())));
			resp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(manageHarService.getAllSubjectCopyService())));
			resp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(manageHarService.getAllSoftwareCopyService())));
			resp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(manageHarService.getAllAffairsCopyService())));
		} catch (Exception e) {
//			System.out.println(e);
			flag = false;
		}
		
		if(flag)resp.setErrCode(ErrCode.SETTING_OK); else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/delHarvest")
	@ResponseBody
	public BaseResponse delHarvestController(@RequestBody BaseListRequest data) {
		
		BaseResponse resp = new BaseResponse();
		Boolean flag = false;
//		System.out.println(data.getBaseHarvestList().get(0).getHarType() + "," + data.getBaseHarvestList().get(0).getHarId());
		for(int i=0; i<data.getBaseHarvestList().size(); i++) {
			BaseRequest har = data.getBaseHarvestList().get(i);
			flag = manageHarService.delHarvestService(har.getHarType(), har.getHarId());
			if(!flag)break;
		}
		
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		
		return resp;
	}
	
	@RequestMapping("/delHarvestCopy")
	@ResponseBody
	public BaseResponse delHarvestCopyController(@RequestBody BaseListRequest data) {
		
		BaseResponse resp = new BaseResponse();
		Boolean flag = false;
//		System.out.println(data.getBaseHarvestList().get(0).getHarType() + "," + data.getBaseHarvestList().get(0).getHarId());
		for(int i=0; i<data.getBaseHarvestList().size(); i++) {
			BaseRequest har = data.getBaseHarvestList().get(i);
			flag = manageHarService.delHarvestCopyService(har.getHarType(), har.getHarId());
			if(!flag)break;
		}
		
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		
		return resp;
	}
}
