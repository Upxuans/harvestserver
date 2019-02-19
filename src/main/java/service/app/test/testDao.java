package service.app.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import service.app.domain.StudentModel;

@Mapper
public interface testDao {

	@Select("SELECT * FROM student WHERE username=#{username}")
	public StudentModel getTestMsg(@Param(value = "username") String username);

	
}
