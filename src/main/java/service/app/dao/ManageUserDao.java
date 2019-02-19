/**
 * By Upxuan
 * 
 * Created in 2018/12/22
 */
package service.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.StudentModel;
import service.app.domain.TeacherModel;

@Mapper
public interface ManageUserDao {

	@Select("SELECT * FROM teacher")
	public List<TeacherModel> getTeacherDao();
	
	@Select("SELECT * FROM student")
	public List<StudentModel> getStudentDao();

	@Delete("DELETE FROM teacher WHERE id=#{id}")
	public void delTeacherDao(@Param(value = "id") int id);

	@Delete("DELETE FROM student WHERE id=#{id}")
	public void delStudentDao(@Param(value = "id") int id);

	@Insert("INSERT INTO teacher (username, password, name, title, team, tel, email, direction, link) VALUE (#{username}, #{password}, #{name}, #{title}, #{team}, #{tel}, #{email}, #{direction}, #{link})")
	public void addTeacherDao(
		@Param(value = "username") String username, 
		@Param(value = "password") String password, 
		@Param(value = "name") String name, 
		@Param(value = "title") String title, 
		@Param(value = "team") String team,
		@Param(value = "tel") String tel, 
		@Param(value = "email") String email, 
		@Param(value = "direction") String direction, 
		@Param(value = "link") String link
	);

	@Insert("INSERT INTO student (username, password, name, degree, team, first, second, tel, email, direction) VALUE (#{username}, #{password}, #{name}, #{degree}, #{team}, #{first}, #{second}, #{tel}, #{email}, #{direction})")
	public void addStudentDao(
			@Param(value = "username") String username, 
			@Param(value = "password") String password, 
			@Param(value = "name") String name, 
			@Param(value = "degree") int degree,
			@Param(value = "team") String team,
			@Param(value = "first") int first, 
			@Param(value = "second") int second, 
			@Param(value = "tel") String tel, 
			@Param(value = "email") String email, 
			@Param(value = "direction") String direction
	);
	
	@Select("SELECT id FROM teacher WHERE username=#{username}")
	public Integer getTeacherUsernameDao(@Param(value = "username") String username);

	@Select("SELECT id FROM student WHERE username=#{username}")
	public Integer getStudentUsernameDao(@Param(value = "username") String username);

	@Update("UPDATE teacher SET name=#{name}, title=#{title}, team=#{team}, tel=#{tel}, email=#{email}, direction=#{direction}, link=#{link} WHERE id=#{id}")
	public void updateTeacherDao(
			@Param(value = "id") int id, 
			@Param(value = "name") String name, 
			@Param(value = "title") String title, 
			@Param(value = "team") String team,
			@Param(value = "tel") String tel, 
			@Param(value = "email") String email, 
			@Param(value = "direction") String direction, 
			@Param(value = "link") String link
	);

	@Update("UPDATE student SET name=#{name}, degree=#{degree}, team=#{team}, first=#{first}, second=#{second}, tel=#{tel}, email=#{email}, direction=#{direction} WHERE id=#{id}")
	public void updateStudentDao(
			@Param(value = "id") int id, 
			@Param(value = "name") String name, 
			@Param(value = "degree") int degree,
			@Param(value = "team") String team,
			@Param(value = "first") int first, 
			@Param(value = "second") int second, 
			@Param(value = "tel") String tel, 
			@Param(value = "email") String email, 
			@Param(value = "direction") String direction
	);

}
