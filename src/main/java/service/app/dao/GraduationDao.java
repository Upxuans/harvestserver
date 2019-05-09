/**
 * By Upxuan
 * 
 * Created in 2019/04
 */
package service.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import service.app.tramodel.response.GraduationResponse;

@Mapper
public interface GraduationDao {

	@Select("SELECT devices,devicedescribe, keyss, works, workDescribe FROM student WHERE id=#{id}")
	GraduationResponse getGraduationMsgDao(@Param(value = "id") int id);

	@Select("UPDATE student SET devices=#{devices}, devicedescribe=#{deviceDescribe}, keyss=#{keyss}, works=#{works}, workdescribe=#{workDescribe} WHERE id=#{id}")
	void updateStudentGraduationDao(
			@Param(value = "id") int id,
			@Param(value = "devices") Boolean devicess, 
			@Param(value = "deviceDescribe") String deviceDescribe,
			@Param(value = "keyss") Boolean keyss, 
			@Param(value = "works") Boolean works, 
			@Param(value = "workDescribe") String workDescribe
	);
	
}
