/**
 * By Upxuan
 * 
 * Created in 2018/12/6
 */
package service.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.app.domain.StudentModel;
import service.app.service.AffairsService;
import service.app.service.AuditService;
import service.app.service.JpaperService;
import service.app.service.ManageHarService;
import service.app.service.ManageUserService;
import service.app.service.MpaperService;
import service.app.service.MyStuService;
import service.app.service.PatentService;
import service.app.service.ProjectService;
import service.app.service.SoftwareService;
import service.app.service.SubjectService;
import service.app.tramodel.BaseRequest;
import service.app.tramodel.response.HarvestResponse;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;
import service.app.util.HarvestUtil;

@Controller
public class AuditController {
	
	@Autowired
	HarvestUtil harvestUtil;
	
	@Autowired
	AuditService auditService;
	
	@Autowired
	MyStuService myStuService;
	
	@Autowired
	ManageUserService manageUserService;
	
	@Autowired
	ManageHarService manageHarService;
	
	@Autowired
	JpaperService jpaperService;
	
	@Autowired
	MpaperService mpaperService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	PatentService patentService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	SoftwareService softwareService;
	
	@Autowired
	AffairsService affairsService;
	
	@RequestMapping("/myAudit")
	@ResponseBody
	public HarvestResponse myAuditController(HttpServletResponse response, BaseRequest data) {
		
		System.out.println(data.getUserId());
		HarvestResponse resp = new HarvestResponse();
		int id = data.getUserId();
		boolean gal = true;
		boolean flag = true;
		try {
			resp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(auditService.getJpaperCopyBySubmitIdService(id))));
			resp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(auditService.getMpaperCopyBySubmitIdService(id))));
			resp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(auditService.getPatentCopyBySubmitIdService(id))));
			resp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(auditService.getProjectCopyBySubmitIdService(id))));
			resp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(auditService.getSubjectCopyBySubmitIdService(id))));
			resp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(auditService.getSoftwareCopyBySubmitIdService(id))));
			resp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(auditService.getAffairsCopyBySubmitIdService(id))));
		} catch (Exception e) {
			flag = false;
		}
		
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	//删除学生手动录入的信息（包括成果和关系）
	@RequestMapping("/delMyAuditHarvest")
	@ResponseBody
	public BaseResponse delMyAuditHarvestController(HttpServletResponse response, BaseRequest data) {
		
		BaseResponse resp = new BaseResponse();
		Boolean flag = false;
		flag = auditService.delHarvestCopyService(data.getHarType(), data.getHarId());
		
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/audit")
	@ResponseBody
	public HarvestResponse auditController(HttpServletResponse response, BaseRequest data) {
		
		HarvestResponse resp = new HarvestResponse();
		List<StudentModel> model = new ArrayList<StudentModel>();
		
		
		boolean gal = false;
		boolean flag = true;
		try {
			if(data.getUserType() == 0) {
				resp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(manageHarService.getAllJpaperCopyService())));
				resp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(manageHarService.getAllMpaperCopyService())));
				resp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(manageHarService.getAllPatentCopyService())));
				resp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(manageHarService.getAllProjectCopyService())));
				resp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(manageHarService.getAllSubjectCopyService())));
				resp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(manageHarService.getAllSoftwareCopyService())));
				resp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(manageHarService.getAllAffairsCopyService())));
			
			}else if(data.getUserType() == 1) {
				model = myStuService.getStuByTeachersIdService(data.getUserId());
				int id;
				if(model.size()>0) {
					id = model.get(0).getId();
					resp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(auditService.getJpaperCopyBySubmitIdService(id))));
					resp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(auditService.getMpaperCopyBySubmitIdService(id))));
					resp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(auditService.getPatentCopyBySubmitIdService(id))));
					resp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(auditService.getProjectCopyBySubmitIdService(id))));
					resp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(auditService.getSubjectCopyBySubmitIdService(id))));
					resp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(auditService.getSoftwareCopyBySubmitIdService(id))));
					resp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(auditService.getAffairsCopyBySubmitIdService(id))));
					for( int i=1; i<model.size(); i++) {
						id = model.get(i).getId();
						resp.getJpaperModels().addAll(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(auditService.getJpaperCopyBySubmitIdService(id))));
						resp.getMpaperModels().addAll(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(auditService.getMpaperCopyBySubmitIdService(id))));
						resp.getPatentModels().addAll(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(auditService.getPatentCopyBySubmitIdService(id))));
						resp.getProjectModels().addAll(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(auditService.getProjectCopyBySubmitIdService(id))));
						resp.getSubjectModels().addAll(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(auditService.getSubjectCopyBySubmitIdService(id))));
						resp.getSoftwareModels().addAll(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(auditService.getSoftwareCopyBySubmitIdService(id))));
						resp.getAffairsModels().addAll(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(auditService.getAffairsCopyBySubmitIdService(id))));
					}
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	//审核“通过”review=3并更新审核人
	@RequestMapping("/passMyAuditHarvest")
	@ResponseBody
	public BaseResponse passMyAuditHarvestController(HttpServletResponse response, BaseRequest data) {
		
		BaseResponse resp = new BaseResponse();
		boolean flag = false;
		
		int userId = data.getUserId();
		if(data.getUserType() == 0) {
			userId = -2;
		}
		flag = auditService.UpdateReviewService(userId, data.getHarType(), data.getHarId(), 3);
		
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	//审核“否决”review=2并更新审核人
	@RequestMapping("/rejectMyAuditHarvest")
	@ResponseBody
	public BaseResponse rejectMyAuditHarvestController(HttpServletResponse response, BaseRequest data) {
		
		BaseResponse resp = new BaseResponse();
		boolean flag = false;
		
		int userId = data.getUserId();
		if(data.getUserType() == 0) {
			userId = -2;
		}
		flag = auditService.UpdateReviewService(userId, data.getHarType(), data.getHarId(), 2);
		
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
}
