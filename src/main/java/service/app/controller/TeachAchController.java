package service.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import service.app.service.TeachAchService;
import service.app.tramodel.RequestData;
import service.app.tramodel.response.AchResponse;
import service.app.tramodel.response.LogInResponse;

@RestController
public class TeachAchController {
	@Autowired
	TeachAchService tas;
	
	@RequestMapping(value = "/teacher.json")
	@ResponseBody
	public List<Object> TeachAch(HttpServletResponse response,RequestData data) {
//		AchResponse tas = new AchResponse();
//		tas.
		List<Object> resp = new ArrayList<Object>();
		resp = tas.getmanager();
		return resp;
	}
}
