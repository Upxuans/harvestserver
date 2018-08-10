package service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.domain.PaperInfo;
import service.app.domain.TeachAchInfo;
import service.app.domain.repository.LogRepository;
import service.app.domain.repository.PaperRepository;
import service.app.domain.repository.TeachAchRepository;

@Service
public class TeachAchService {
	
	 @Autowired
	 private TeachAchRepository teachAchRepository;
	 
	 @Autowired
	 private PaperRepository paperRepository;
	 
	 @Autowired
	 private LogRepository logRepository;
	 //根据教师成果查找
	 public List<TeachAchInfo> getTeachAchInfo(Integer tachteach) {
		 List<TeachAchInfo> teachAchInfos = teachAchRepository.findByTachteach(tachteach);
		 return teachAchInfos;
	 }
	 //显示manage表
	 public List<Object> getmanager() {
		return logRepository.selectAllTagView();
	}

}
