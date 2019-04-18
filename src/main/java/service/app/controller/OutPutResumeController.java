/**
 * Created by Upxuan
 * 
 * 2019/2/22
 */
package service.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.app.service.AffairsService;
import service.app.service.AuditService;
import service.app.service.JpaperService;
import service.app.service.ManageHarService;
import service.app.service.ManageUserService;
import service.app.service.MpaperService;
import service.app.service.PatentService;
import service.app.service.ProjectService;
import service.app.service.SoftwareService;
import service.app.service.SubjectService;
import service.app.tramodel.LogRequest;
import service.app.tramodel.ManageUserRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;
import service.app.tramodel.response.HarvestResponse;
import service.app.tramodel.response.LogInUserResponse;
import service.app.util.HarvestUtil;

@Controller
@RestController
public class OutPutResumeController {

	@Autowired
	AuditService auditService;
	
	@Autowired
	HarvestUtil harvestUtil;
	
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
	
	@RequestMapping("/harvest")
	public HarvestResponse harvestController(HttpServletResponse response, LogRequest data) {
		
		HarvestResponse resp = new HarvestResponse();
		String username = data.getUsername();
		int id ;
		int load = 1;
		boolean gal = false;
		boolean flag = true;
		try {
			if(data.getUserType() == 0) {
				//获取所有的harvest
				resp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(manageHarService.getAllJpaperAndCopyService())));
				resp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(manageHarService.getAllMpaperAndCopyService())));
				resp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(manageHarService.getAllPatentAndCopyService())));
				resp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(manageHarService.getAllProjectAndCopyService())));
				resp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(manageHarService.getAllSubjectAndCopyService())));
				resp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(manageHarService.getAllSoftwareAndCopyService())));
				resp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(manageHarService.getAllAffairsAndCopyService())));
			}else if(data.getUserType() == 1) {
				id = manageUserService.getTeacherIdByUsernameService(username);
				resp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(jpaperService.getJpaperDataFromTeacherService(id, load))));
				resp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(mpaperService.getMpaperDataFromTeacherService(id, load))));
				resp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(patentService.getPatentDataFromTeacherService(id, load))));
				resp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(projectService.getProjectDataFromTeacherService(id, load))));
				resp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(subjectService.getSubjectDataFromTeacherService(id, load))));
				resp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(softwareService.getSoftwareDataFromTeacherService(id, load))));
				resp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(affairsService.getAffairsDataFromTeacherService(id, load))));
			}else if(data.getUserType() == 2) {
				id = manageUserService.getStudentIdByUsernameService(username);
				resp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(jpaperService.getJpaperDataFromStudentService(id, load))));
				resp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(mpaperService.getMpaperDataFromStudentService(id, load))));
				resp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(patentService.getPatentDataFromStudentService(id, load))));
				resp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(projectService.getProjectDataFromStudentService(id, load))));
				resp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(subjectService.getSubjectDataFromStudentService(id, load))));
				resp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(softwareService.getSoftwareDataFromStudentService(id, load))));
				resp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(affairsService.getAffairsDataFromStudentService(id, load))));
			}
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/getTeacherMsg")
	public LogInUserResponse getTeacherMsgController(HttpServletResponse response, LogRequest data) {
		LogInUserResponse resp = new LogInUserResponse();
		boolean flag = true;
		try {
			if(data.getUsername().length() == 5) {
				resp.setTeacherModel(manageUserService.getTeacherMsgService(data.getUsername()));
			}else if(data.getUsername().length() == 9) {}
		} catch (Exception e) {
			flag = false;
		}
		
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	//本质是存储新信息到teacher表中
	@RequestMapping("/generateResume")
	public BaseResponse generateResumeController(@RequestBody ManageUserRequest data) {
		BaseResponse resp = new BaseResponse();
		boolean flag = false;
		if(data.getUserType() == 1) {
			flag = manageUserService.generateResumeForTeacherService(data.getUserId(), data.getTeacherModel());
		}else if(data.getUserType() == 2) {}
		if (flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
}