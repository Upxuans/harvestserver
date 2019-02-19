package service.app.tramodel;

import service.app.domain.StudentModel;
import service.app.domain.TeacherModel;

public class ManageUserRequest extends BaseRequest implements Cloneable {

	private int handleType;//handleType=0是添加，1是更新
	private TeacherModel teacherModel;
	private StudentModel studentModel;
	
	public int getHandleType() {
		return handleType;
	}
	
	public void setHandleType(int handleType) {
		this.handleType = handleType;
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
