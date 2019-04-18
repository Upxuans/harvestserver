/**
 * By Upxuan
 * 
 * Created in 2018/12/4
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
import service.app.service.MyStuService;
import service.app.tramodel.BaseRequest;
import service.app.tramodel.response.MyStudentResponse;

@Controller
public class MyStuController {
	
	@Autowired
	MyStuService myStuService;
	
	@RequestMapping("/myStudentCount")
	@ResponseBody
	public List<MyStudentResponse> myStudentCount(HttpServletResponse response, BaseRequest data) {
		
		//通过老师的id到student表中获得其学生信息(name,id),然后根据id到sach中找到不同harType的userId=id且load=1
		List<MyStudentResponse> resp = new ArrayList<MyStudentResponse>();
		List<StudentModel> model = myStuService.getStuByTeachersIdService(data.getUserId());
		
		for(int i=0; i<model.size(); i++) {
			MyStudentResponse myStudentData = new MyStudentResponse();
			myStudentData.setStuId(model.get(i).getId());
			myStudentData.setStuName(model.get(i).getName());
			myStudentData.setUsername(model.get(i).getUsername());
			myStudentData.setJpaperNum(myStuService.getHarNumService(model.get(i).getId(), 1));
			myStudentData.setMpaperNum(myStuService.getHarNumService(model.get(i).getId(), 2));
			myStudentData.setPatentNum(myStuService.getHarNumService(model.get(i).getId(), 3));
			myStudentData.setProjectNum(myStuService.getHarNumService(model.get(i).getId(), 4));
			myStudentData.setSubjectNum(myStuService.getHarNumService(model.get(i).getId(), 5));
			myStudentData.setSoftwareNum(myStuService.getHarNumService(model.get(i).getId(), 6));
			myStudentData.setAffairsNum(myStuService.getHarNumService(model.get(i).getId(), 7));
			resp.add(myStudentData);
		}
		return resp;
	}
}
