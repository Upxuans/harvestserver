/**
 * By Upxuan
 *
 */
package service.app.controller;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.app.domain.AffairsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;
import service.app.service.AffairsService;
import service.app.service.AuditService;
import service.app.service.HarvestService;
import service.app.service.JpaperService;
import service.app.service.MpaperService;
import service.app.service.PatentService;
import service.app.service.ProjectService;
import service.app.service.SoftwareService;
import service.app.service.SubjectService;
import service.app.tramodel.HarvestRequest;
import service.app.util.HarvestUtil;;

@Controller
public class HarvestController {
	
	@Autowired
	HarvestService harvests;
	
	@Autowired
	HarvestUtil harvestUtil;
	
	@Autowired
	AuditService auditService;
	
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
	
	@RequestMapping("/harJpaper")
	@ResponseBody
	public List<JpaperModel> harJpaper(HttpServletResponse response, HarvestRequest data) {
		
		boolean gal = true;
		List<JpaperModel> resp = new ArrayList<JpaperModel>();
		if(data.getUserType() == 1)//teacher
			resp = auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(jpaperService.getJpaperDataFromTeacherService(data.getUserId(), data.getLoad())));
		else if(data.getUserType() == 2)//student
			resp = auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(jpaperService.getJpaperDataFromStudentService(data.getUserId(), data.getLoad())));
		
		return resp;
	}
	
	@RequestMapping("/harMpaper")
	@ResponseBody
	public List<MpaperModel> harMpaper(HttpServletResponse response, HarvestRequest data) {
		
		boolean gal = true;
		List<MpaperModel> resp = new ArrayList<MpaperModel>();
		if(data.getUserType() == 1)//teacher
			resp = auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(mpaperService.getMpaperDataFromTeacherService(data.getUserId(), data.getLoad())));
		else if(data.getUserType() == 2)//student
			resp = auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(mpaperService.getMpaperDataFromStudentService(data.getUserId(), data.getLoad())));
		return resp;
	}
	
	@RequestMapping("/harPatent")
	@ResponseBody
	public List<PatentModel> harPatent(HttpServletResponse response, HarvestRequest data) {
		
		boolean gal = true;
		List<PatentModel> resp = new ArrayList<PatentModel>();
		if(data.getUserType() == 1)//teacher
			resp = auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(patentService.getPatentDataFromTeacherService(data.getUserId(), data.getLoad())));
		else if(data.getUserType() == 2)//student
			resp = auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(patentService.getPatentDataFromStudentService(data.getUserId(), data.getLoad())));
		return resp;
	}
	
	@RequestMapping("/harProject")
	@ResponseBody
	public List<ProjectModel> harProject(HttpServletResponse response, HarvestRequest data) {
		
		boolean gal = true;
		List<ProjectModel> resp = new ArrayList<ProjectModel>();
		if(data.getUserType() == 1)//teacher
			resp = auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(projectService.getProjectDataFromTeacherService(data.getUserId(), data.getLoad())));
		else if(data.getUserType() == 2)//student
			resp = auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(projectService.getProjectDataFromStudentService(data.getUserId(), data.getLoad())));
		return resp;
	}
	
	@RequestMapping("/harSubject")
	@ResponseBody
	public List<SubjectModel> harSubject(HttpServletResponse response, HarvestRequest data) {
		
		boolean gal = true;
		List<SubjectModel> resp = new ArrayList<SubjectModel>();
		if(data.getUserType() == 1) {//teacher
			resp = auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(subjectService.getSubjectDataFromTeacherService(data.getUserId(), data.getLoad())));
		}
		else if(data.getUserType() == 2)//student
			resp = auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(subjectService.getSubjectDataFromStudentService(data.getUserId(), data.getLoad())));
		return resp;
	}
	
	@RequestMapping("/harSoftware")
	@ResponseBody
	public List<SoftwareModel> harSoftware(HttpServletResponse response, HarvestRequest data) {
		
		boolean gal = true;
		List<SoftwareModel> resp = new ArrayList<SoftwareModel>();
		if(data.getUserType() == 1) {//teacher
			resp = auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(softwareService.getSoftwareDataFromTeacherService(data.getUserId(), data.getLoad())));
		}
		else if(data.getUserType() == 2)//student
			resp = auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(softwareService.getSoftwareDataFromStudentService(data.getUserId(), data.getLoad())));
		return resp;
	}
	
	@RequestMapping("/harAffairs")
	@ResponseBody
	public List<AffairsModel> harAffairs(HttpServletResponse response, HarvestRequest data) {
		
		boolean gal = true;
		List<AffairsModel> resp = new ArrayList<AffairsModel>();
		if(data.getUserType() == 1) //teacher
			resp = auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(affairsService.getAffairsDataFromTeacherService(data.getUserId(), data.getLoad())));
		else if(data.getUserType() == 2) //student
			resp = auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(affairsService.getAffairsDataFromStudentService(data.getUserId(), data.getLoad())));
		return resp;
	}
}
