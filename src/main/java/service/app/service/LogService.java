/**
 * 
 */
/**
 * @author Upxuan
 *
 */
package service.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.dao.LogDao;
import service.app.domain.ManagerModel;
import service.app.domain.StudentModel;
import service.app.domain.TeacherModel;

@Service
public class LogService {

    @Autowired
    LogDao logDao;
    
    public ManagerModel getManagerService(String username) {
    	ManagerModel managerInfo = logDao.getManagerDao(username);
		return managerInfo;
    }
    
    public TeacherModel getTeacherService(String username) {
    	TeacherModel teacherInfo = logDao.getTeacherDao(username);
		return teacherInfo;
    }
    
    public StudentModel getStudentService(String username) {
    	StudentModel studentInfo = logDao.getStudentDao(username);
    	studentInfo.setFirst(logDao.getTeacherNameByStudent(Integer.parseInt(studentInfo.getFirst())));
    	studentInfo.setSecond(logDao.getTeacherNameByStudent(Integer.parseInt(studentInfo.getSecond())));
    	if(Integer.parseInt(studentInfo.getDegree()) == 0)
    		studentInfo.setDegree("学硕");
    	else
    		studentInfo.setDegree("专硕");
		return studentInfo;
    }
}