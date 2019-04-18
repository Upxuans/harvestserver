package service.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.AchievementModel;

@Mapper
public interface WebMaintainDao {
	
	@Select("SELECT * FROM achievement")
	AchievementModel getAchievementDao();

	@Update("UPDATE achievement SET project1=#{project1}, project2=#{project2}, paper=#{paper} WHERE id=1")
	void updateAchievementDao(@Param(value = "project1") String project1, @Param(value = "project2") String project2, @Param(value = "paper") String paper);

}
