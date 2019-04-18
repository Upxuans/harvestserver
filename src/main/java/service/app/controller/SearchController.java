/**
 * By Upxuan
 * 
 * Created in 2019/3
 * */
package service.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.app.domain.AffairsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.Param;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;
import service.app.service.AuditService;
import service.app.service.ManageHarService;
import service.app.tramodel.SearchRequest;
import service.app.tramodel.response.HarvestResponse;
import service.app.tramodel.response.SearchResponse;
import service.app.util.HarvestUtil;

@Controller
@RestController
public class SearchController {
	
	@Autowired
	AuditService auditService;
	
	@Autowired
	HarvestUtil harvestUtil;
	
	@Autowired
	ManageHarService manageHarService;
	
	@RequestMapping("/search")
	public List<SearchResponse> search(HttpServletResponse response, SearchRequest data) {
		
		List<SearchResponse> resp = new ArrayList<SearchResponse>();
		HarvestResponse harResp = new HarvestResponse();
		boolean gal = false;
		
		harResp.setJpaperModels(auditService.arrangeJpaperService(gal, harvestUtil.setJpaperDataAuthors(manageHarService.getAllJpaperAndCopyService())));
		harResp.setMpaperModels(auditService.arrangeMpaperService(gal, harvestUtil.setMpaperDataAuthors(manageHarService.getAllMpaperAndCopyService())));
		harResp.setPatentModels(auditService.arrangePatentService(gal, harvestUtil.setPatentDataAuthors(manageHarService.getAllPatentAndCopyService())));
		harResp.setProjectModels(auditService.arrangeProjectService(gal, harvestUtil.setProjectDataAuthors(manageHarService.getAllProjectAndCopyService())));
		harResp.setSubjectModels(auditService.arrangeSubjectService(gal, harvestUtil.setSubjectDataAuthors(manageHarService.getAllSubjectAndCopyService())));
		harResp.setSoftwareModels(auditService.arrangeSoftwareService(gal, harvestUtil.setSoftwareDataAuthors(manageHarService.getAllSoftwareAndCopyService())));
		harResp.setAffairsModels(auditService.arrangeAffairsService(gal, harvestUtil.setAffairsDataAuthors(manageHarService.getAllAffairsAndCopyService())));
		
		for(int i = 0; i<harResp.getJpaperModels().size(); i++) {
			JpaperModel model = harResp.getJpaperModels().get(i);
			SearchResponse respDemo = new SearchResponse();
			respDemo.setId(model.getJpaperId());
			respDemo.setType("期刊论文");
			respDemo.setTitle(model.getJpaperTitle());
			respDemo.setAuthors(model.getJpaperAuthors());
			
			List<Param> params = new ArrayList<Param>();
			params.add(new Param("收录", model.getJpaperType()));//setJpaperDataAuthors已配置
			params.add(new Param("期刊名称", model.getJpaperPaperName()));
			params.add(new Param("状态", model.getJpaperStatus()));
			params.add(new Param("发表日期", model.getJpaperPublishDate()));
			params.add(new Param("卷期号", model.getJpaperReel() + "-" + model.getJpaperIssue()));
			params.add(new Param("起止页码", model.getJpaperPage1() + "-" + model.getJpaperPage2()));
			params.add(new Param("DOI", model.getJpaperDoi()));
			params.add(new Param("第一作者", model.getFirstAuthor()));
			if(model.getReview() != null) {
				params.add(new Param("毕业条件归属者", model.getBelong()));
				params.add(new Param("提交审核者", model.getSubmitName()));
				params.add(new Param("审核人", model.getReviseName()));
				params.add(new Param("审核状态", model.getReviseStatus()));
			}
			respDemo.setParams(params);
			resp.add(respDemo);
		}
		
		for(int i = 0; i<harResp.getMpaperModels().size(); i++) {
			MpaperModel model = harResp.getMpaperModels().get(i);
			SearchResponse respDemo = new SearchResponse();
			respDemo.setId(model.getMpaperId());
			respDemo.setType("会议论文");
			respDemo.setTitle(model.getMpaperTitle());
			respDemo.setAuthors(model.getMpaperAuthors());
			
			List<Param> params = new ArrayList<Param>();
			params.add(new Param("收录", model.getMpaperType()));//setJpaperDataAuthors已配置
			params.add(new Param("发表日期", model.getMpaperPublishDate()));
			params.add(new Param("起止页码", model.getMpaperPage1() + "-" + model.getMpaperPage2()));
			params.add(new Param("会议名称", model.getMpaperMeetName()));
			params.add(new Param("会议日期", model.getMpaperMeetDate1() + "-" + model.getMpaperMeetDate2()));
			params.add(new Param("会议地点", model.getMpaperMeetPlace()));
			if(model.getReview() != null) {
				params.add(new Param("毕业条件归属者", model.getBelong()));
				params.add(new Param("提交审核者", model.getSubmitName()));
				params.add(new Param("审核人", model.getReviseName()));
				params.add(new Param("审核状态", model.getReviseStatus()));
			}
			respDemo.setParams(params);
			resp.add(respDemo);
		}
		
		for(int i = 0; i<harResp.getPatentModels().size(); i++) {
			PatentModel model = harResp.getPatentModels().get(i);
			SearchResponse respDemo = new SearchResponse();
			respDemo.setId(model.getPatentId());
			respDemo.setType("专利");
			respDemo.setTitle(model.getPatentTitle());
			respDemo.setAuthors(model.getPatentAuthors());
			
			List<Param> params = new ArrayList<Param>();
			params.add(new Param("申请(专利)号", model.getPatentNum()));//setJpaperDataAuthors已配置
			params.add(new Param("公开(公告)号", model.getPatentNoteNum()));
			params.add(new Param("发证单位", model.getPatentUnit()));
			params.add(new Param("专利类别", model.getPatentType()));
			params.add(new Param("专利状态", model.getPatentStatus()));
			params.add(new Param("申请日期", model.getPatentApplyDate()));
			params.add(new Param("生效日期", model.getPatentEffectDate1() + "-" + model.getPatentEffectDate2()));
			params.add(new Param("专利权人", model.getPatentBelong()));
			if(model.getReview() != null) {
				params.add(new Param("毕业条件归属者", model.getBelong()));
				params.add(new Param("提交审核者", model.getSubmitName()));
				params.add(new Param("审核人", model.getReviseName()));
				params.add(new Param("审核状态", model.getReviseStatus()));
			}
			respDemo.setParams(params);
			resp.add(respDemo);
		}
		
		for(int i = 0; i<harResp.getProjectModels().size(); i++) {
			ProjectModel model = harResp.getProjectModels().get(i);
			SearchResponse respDemo = new SearchResponse();
			respDemo.setId(model.getProjectId());
			respDemo.setType("项目");
			respDemo.setTitle(model.getProjectTitle());
			respDemo.setAuthors(model.getProjectAuthors());
			
			List<Param> params = new ArrayList<Param>();
			params.add(new Param("类别", model.getProjectType()));//setJpaperDataAuthors已配置
			params.add(new Param("编号", model.getProjectNum()));
			params.add(new Param("起止日期", model.getProjectDate1() + "-" + model.getProjectDate2()));
			params.add(new Param("金额", model.getProjectMoney()));
			if(model.getReview() != null) {
				params.add(new Param("毕业条件归属者", model.getBelong()));
				params.add(new Param("提交审核者", model.getSubmitName()));
				params.add(new Param("审核人", model.getReviseName()));
				params.add(new Param("审核状态", model.getReviseStatus()));
			}
			respDemo.setParams(params);
			resp.add(respDemo);
		}
		
		for(int i = 0; i<harResp.getSubjectModels().size(); i++) {
			SubjectModel model = harResp.getSubjectModels().get(i);
			SearchResponse respDemo = new SearchResponse();
			respDemo.setId(model.getSubjectId());
			respDemo.setType("学术专著");
			respDemo.setTitle(model.getSubjectTitle());
			respDemo.setAuthors(model.getSubjectAuthors());
			
			List<Param> params = new ArrayList<Param>();
			params.add(new Param("丛书名称", model.getSubjectBookName()));//setJpaperDataAuthors已配置
			params.add(new Param("出版状态", model.getSubjectStatus()));
			params.add(new Param("ISBN号", model.getSubjectISBN()));
			params.add(new Param("出版社", model.getSubjectPublish()));
			params.add(new Param("出版日期", model.getSubjectPublishDate()));
			params.add(new Param("DOI", model.getSubjectDOI()));
			if(model.getReview() != null) {
				params.add(new Param("毕业条件归属者", model.getBelong()));
				params.add(new Param("提交审核者", model.getSubmitName()));
				params.add(new Param("审核人", model.getReviseName()));
				params.add(new Param("审核状态", model.getReviseStatus()));
			}
			respDemo.setParams(params);
			resp.add(respDemo);
		}
		
		for(int i = 0; i<harResp.getSoftwareModels().size(); i++) {
			SoftwareModel model = harResp.getSoftwareModels().get(i);
			SearchResponse respDemo = new SearchResponse();
			respDemo.setId(model.getSoftwareId());
			respDemo.setType("软件著作权");
			respDemo.setTitle(model.getSoftwareName());
			respDemo.setAuthors(model.getSoftwareAuthors());
			
			List<Param> params = new ArrayList<Param>();
			params.add(new Param("登记号", model.getSoftwareNum()));//setJpaperDataAuthors已配置
			params.add(new Param("权利获得方式", model.getSoftwareAcquireType()));
			if(model.getReview() != null) {
				params.add(new Param("毕业条件归属者", model.getBelong()));
				params.add(new Param("提交审核者", model.getSubmitName()));
				params.add(new Param("审核人", model.getReviseName()));
				params.add(new Param("审核状态", model.getReviseStatus()));
			}
			respDemo.setParams(params);
			resp.add(respDemo);
		}
		
		for(int i = 0; i<harResp.getAffairsModels().size(); i++) {
			AffairsModel model = harResp.getAffairsModels().get(i);
			SearchResponse respDemo = new SearchResponse();
			respDemo.setId(model.getAffairsId());
			respDemo.setType("公共事务");
			respDemo.setTitle(model.getAffairsTitle());
			respDemo.setAuthors(model.getAffairsAuthors());
			
			List<Param> params = new ArrayList<Param>();
			params.add(new Param("内容", model.getAffairsContent()));//setJpaperDataAuthors已配置
			params.add(new Param("起止日期", model.getAffairsDate1() + "-" + model.getAffairsDate2()));
			if(model.getReview() != null) {
				params.add(new Param("毕业条件归属者", model.getBelong()));
				params.add(new Param("提交审核者", model.getSubmitName()));
				params.add(new Param("审核人", model.getReviseName()));
				params.add(new Param("审核状态", model.getReviseStatus()));
			}
			respDemo.setParams(params);
			resp.add(respDemo);
		}
		
		return resp;
	}
	
}
