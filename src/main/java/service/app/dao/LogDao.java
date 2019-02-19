package service.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import service.app.domain.ManagerModel;
import service.app.domain.StudentModel;
import service.app.domain.TeacherModel;

@Mapper
public interface LogDao {
	
	@Select("SELECT * FROM manager WHERE username = #{username}")
	public ManagerModel getManagerDao(@Param(value = "username") String username);
	
	@Select("SELECT * FROM teacher WHERE username = #{username}")
	public TeacherModel getTeacherDao(@Param(value = "username") String username);
	
	@Select("SELECT * FROM student WHERE username = #{username}")
	public StudentModel getStudentDao(@Param(value = "username") String username);
	
	@Select("SELECT name FROM teacher WHERE id = #{first}")
	public String getTeacherNameByStudent(@Param(value = "first") int first);
	
}
