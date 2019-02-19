/**
 * By Upxuan
 * 
 * Created in 2018/11/5
 */
package service.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReviseInfoDao {

	@Update("UPDATE teacher AS t "
			+ "SET t.link=#{link}, t.tel=#{tel}, t.email=#{email}, t.direction=#{direction} "
			+ "WHERE t.id=#{userId}")
	public void updateInfoInTeacherDao(@Param(value = "userId") int userId, @Param(value = "link") String link, @Param(value = "tel") String tel, @Param(value = "email") String email, @Param(value = "direction") String direction);

	@Update("UPDATE student AS s "
			+ "SET s.tel=#{tel}, s.email=#{email}, s.direction=#{direction} "
			+ "WHERE s.id=#{userId}")
	public void updateInfoInStudentDao(@Param(value = "userId") int userId, @Param(value = "tel") String tel, @Param(value = "email") String email, @Param(value = "direction") String direction);

	
	@Select("SELECT m.password FROM manager AS m WHERE m.id=#{userId}")
	public String findManagerPwdByIdDao(@Param(value = "userId") int userId);
	
	@Select("SELECT t.password FROM teacher AS t WHERE t.id=#{userId}")
	public String findTeacherPwdByIdDao(@Param(value = "userId") int userId);
	
	@Select("SELECT s.password FROM student AS s WHERE s.id=#{userId}")
	public String findStudentPwdByIdDao(@Param(value = "userId") int userId);
	
	
	@Update("UPDATE manager AS m SET m.password=#{checkPass} WHERE m.id=#{userId}")
	public void updatePwdInManagerDao(@Param(value = "userId") int userId, @Param(value = "checkPass") String checkPass);
	
	@Update("UPDATE teacher AS t SET t.password=#{checkPass} WHERE t.id=#{userId}")
	public void updatePwdInTeacherDao(@Param(value = "userId") int userId, @Param(value = "checkPass") String checkPass);

	@Update("UPDATE student AS s SET s.password=#{checkPass} WHERE s.id=#{userId}")
	public void updatePwdInStudentDao(@Param(value = "userId") int userId, @Param(value = "checkPass") String checkPass);

	


	

}
