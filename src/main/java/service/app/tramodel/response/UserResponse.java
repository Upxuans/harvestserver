/**
 * By Upxuan
 * 
 * Created in 2018/12/22
 */
package service.app.tramodel.response;

import java.util.List;

import service.app.domain.StudentModel;
import service.app.domain.TeacherModel;

public class UserResponse extends BaseResponse{

	private List<TeacherModel> teacherModels;
	private List<StudentModel> studentModels;

	public List<TeacherModel> getTeacherModels() {
		return teacherModels;
	}

	public void setTeacherModels(List<TeacherModel> teacherModels) {
		this.teacherModels = teacherModels;
	}

	public List<StudentModel> getStudentModels() {
		return studentModels;
	}

	public void setStudentModels(List<StudentModel> studentModels) {
		this.studentModels = studentModels;
	}
}
