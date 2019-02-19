package service.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import service.app.domain.StudentModel;

@Mapper
public interface MyStuDao {

	@Select("SELECT * FROM student AS s WHERE s.first=#{userId} OR s.second=#{userId}")
	public List<StudentModel> getStuByTeachersIdDao(@Param(value = "userId") int userId);

	@Select("SELECT count(*) FROM sach AS s WHERE s.sach_userId=#{userId} AND s.sach_harType=#{harType} AND s.sach_load=1")
	public Integer getHarNumDao(@Param(value = "harType") int harType, @Param(value = "userId") int userId);

	
}
