/**
 * By Upxuan
 * 
 * Created in 2018/11
 */
package service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.app.service.AffairsService;
import service.app.service.HandleHarService;
import service.app.service.JpaperService;
import service.app.service.MpaperService;
import service.app.service.PatentService;
import service.app.service.ProjectService;
import service.app.service.SoftwareService;
import service.app.service.SubjectService;
import service.app.tramodel.ManualAddRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;

@Controller
public class AddManualController {
	
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
	
	@Autowired
	HandleHarService handleharService;
	
	//手动录入页 添加jpaper成果
	@RequestMapping("/manualAddJpaper")
	@ResponseBody
	public BaseResponse ManualAddJpaperController (@RequestBody ManualAddRequest data) {
//		System.out.println("User:" + data.getUserType() + "," + data.getUserId());
//		System.out.println("Harvest:" + data.getJpaperModel().getJpaperPaperName() + "," + data.getJpaperModel().getJpaperPublishDate());
//		System.out.println("Authors:" + data.getAuthorsModels().get(0).getName() + "," + data.getAuthorsModels().get(0).getDegree() + "," + data.getAuthorsModels().get(0).getNature1() + "," + data.getAuthorsModels().get(0).getNature2());
		BaseResponse baseResp = new BaseResponse();
		
		Boolean flag = false;
		if (data.getHandleType() == 0) {//插入
			int harId = jpaperService.insertJpaperToStockService(data.getUserType(), data.getUserId(), data.getJpaperModel());//插入后获得该记录的id
			int source = 0;
			if(data.getUserType() == 2) source = 1;
			flag = handleharService.insertCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 1, harId, source);//根据作者插入到关系表中
		}else {//更新成果记录
//			System.out.println("update:" + data.getSource());
			if(data.getHandleType() == 2) jpaperService.updateJpaperCopyReviewService(data.getHarId(), 1, -1);//重新提交的handleType=2都变成“审核中”=1的状态，且reviseId=0审核者也要更新（审核成功的前端不能编辑了所以无需考虑）
			flag = jpaperService.updateJpaperService(data.getJpaperModel(), data.getHarId(), data.getSource());//更新后获得该记录的id
			if(flag) flag = handleharService.updateCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 1, data.getHarId(), data.getSource());//根据作者更新关系
		}
		
		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
	
	@RequestMapping("/manualAddMpaper")
	@ResponseBody
	public BaseResponse ManualAddMpaperController (@RequestBody ManualAddRequest data) {
		
		BaseResponse baseResp = new BaseResponse();
		Boolean flag = false;
		if (data.getHandleType() == 0) {//add
			int harId = mpaperService.insertMpaperToStockService(data.getUserType(), data.getUserId(), data.getMpaperModel());
			int source = 0;
			if(data.getUserType() == 2) source = 1;
			flag = handleharService.insertCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 2, harId, source);
		}else {//update
			if(data.getHandleType() == 2) mpaperService.updateMpaperCopyReviewService(data.getHarId(), 1, -1);
			flag = mpaperService.updateMpaperService(data.getMpaperModel(), data.getHarId(), data.getSource());//插入后获得该记录的id
			System.out.println(flag);
			if(flag) flag = handleharService.updateCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 2, data.getHarId(), data.getSource());//根据作者更新关系
		}
		
		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
	
	@RequestMapping("/manualAddPatent")
	@ResponseBody
	public BaseResponse ManualAddPatentController (@RequestBody ManualAddRequest data) {
		
		BaseResponse baseResp = new BaseResponse();
		Boolean flag = false;
		if (data.getHandleType() == 0) {//add
			int harId = patentService.insertPatentToStockService(data.getUserType(), data.getUserId(), data.getPatentModel());
			int source = 0;
			if(data.getUserType() == 2) source = 1;
			flag = handleharService.insertCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 3, harId, source);
		}else {//update
			if(data.getHandleType() == 2) patentService.updatePatentCopyReviewService(data.getHarId(), 1, -1);
			flag = patentService.updatePatentService(data.getPatentModel(), data.getHarId(), data.getSource());//插入后获得该记录的id
			if(flag) flag = handleharService.updateCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 3, data.getHarId(), data.getSource());//根据作者更新关系
		}
		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
	
	@RequestMapping("/manualAddProject")
	@ResponseBody
	public BaseResponse ManualAddProjectController (@RequestBody ManualAddRequest data) {
		
		BaseResponse baseResp = new BaseResponse();
		Boolean flag = false;
		if (data.getHandleType() == 0) {//add
			int harId = projectService.insertProjectToStockService(data.getUserType(), data.getUserId(), data.getProjectModel());
			int source = 0;
			if(data.getUserType() == 2) source = 1;
			flag = handleharService.insertCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 4, harId, source);
		}else {//update
			if(data.getHandleType() == 2) projectService.updateProjectCopyReviewService(data.getHarId(), 1, -1);
			flag = projectService.updateProjectService(data.getProjectModel(), data.getHarId(), data.getSource());//插入后获得该记录的id
			if(flag) flag = handleharService.updateCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 4, data.getHarId(), data.getSource());//根据作者更新关系
		}
		
		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
	
	@RequestMapping("/manualAddSubject")
	@ResponseBody
	public BaseResponse ManualAddSubjectController (@RequestBody ManualAddRequest data) {
		
		BaseResponse baseResp = new BaseResponse();
		Boolean flag = false;
		if (data.getHandleType() == 0) {//add
			int harId = subjectService.insertSubjectToStockService(data.getUserType(), data.getUserId(), data.getSubjectModel());
			int source = 0;
			if(data.getUserType() == 2) source = 1;
			flag = handleharService.insertCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 5, harId, source);
		}else {//update
			if(data.getHandleType() == 2) subjectService.updateSubjectCopyReviewService(data.getHarId(), 1, -1);
			flag = subjectService.updateSubjectService(data.getSubjectModel(), data.getHarId(), data.getSource());//插入后获得该记录的id
			if(flag) flag = handleharService.updateCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 5, data.getHarId(), data.getSource());//根据作者更新关系
		}
		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
	
	@RequestMapping("/manualAddSoftware")
	@ResponseBody
	public BaseResponse ManualAddSoftwareController (@RequestBody ManualAddRequest data) {
		
		BaseResponse baseResp = new BaseResponse();
		Boolean flag = false;
		if (data.getHandleType() == 0) {//add
			int harId = softwareService.insertSoftwareToStockService(data.getUserType(), data.getUserId(), data.getSoftwareModel());
			int source = 0;
			if(data.getUserType() == 2) source = 1;
			flag = handleharService.insertCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 6, harId, source);
		}else {//update
			if(data.getHandleType() == 2) softwareService.updateSoftwareCopyReviewService(data.getHarId(), 1, -1);
			flag = softwareService.updateSoftwareService(data.getSoftwareModel(), data.getHarId(), data.getSource());//插入后获得该记录的id
			if(flag) flag = handleharService.updateCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 6, data.getHarId(), data.getSource());//根据作者更新关系
		}

		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
	
	@RequestMapping("/manualAddAffairs")
	@ResponseBody
	public BaseResponse ManualAddAffairsController (@RequestBody ManualAddRequest data) {
		
		BaseResponse baseResp = new BaseResponse();
		Boolean flag = false;
		if (data.getHandleType() == 0) {//add
			int harId = affairsService.insertAffairsToStockService(data.getUserType(), data.getUserId(), data.getAffairsModel());
			int source = 0;
			if(data.getUserType() == 2) source = 1;
			flag = handleharService.insertCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 7, harId, source);
		}else {//update
			if(data.getHandleType() == 2) affairsService.updateAffairsCopyReviewService(data.getHarId(), 1, -1);
			flag = affairsService.updateAffairsService(data.getAffairsModel(), data.getHarId(), data.getSource());//插入后获得该记录的id
			if(flag) flag = handleharService.updateCorrelation(data.getUserType(), data.getUserId(), data.getAuthorsModels(), 7, data.getHarId(), data.getSource());//根据作者更新关系
		}
		
		if (flag) baseResp.setErrCode(ErrCode.SETTING_OK);
		else baseResp.setErrCode(ErrCode.SETTING_ERR);
		return baseResp;
	}
}
