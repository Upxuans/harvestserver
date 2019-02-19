package service.app.tramodel.response;

import service.app.domain.ManagerModel;
import service.app.domain.StudentModel;
import service.app.domain.TeacherModel;

public class LogInUserResponse extends BaseResponse{ //登陆扩充，加入登陆信息和错误码

	private int type;
	private ManagerModel managerModel;
	private TeacherModel teacherModel;
	private StudentModel studentModel;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ManagerModel getManagerModel() {
		return managerModel;
	}

	public void setManagerModel(ManagerModel managerModel) {
		this.managerModel = managerModel;
	}

	public TeacherModel getTeacherModel() {
		return teacherModel;
	}

	public void setTeacherModel(TeacherModel teacherModel) {
		this.teacherModel = teacherModel;
	}

	public StudentModel getStudentModel() {
		return studentModel;
	}

	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}
}
