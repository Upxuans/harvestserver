package service.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.SoftwareModel;

@Mapper
public interface SoftwareDao {

	
	//找ID为userid(教师/学生)的软件著作权
	@Select("SELECT * FROM software AS t1 WHERE t1.software_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=6 AND t2.tach_source=0)")
	List<SoftwareModel> getSoftwareDataFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM software_copy AS t1 WHERE t1.review=3 AND t1.software_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=6 AND t2.tach_source=1)")
	List<SoftwareModel> getSoftwareCopyFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Select("SELECT * FROM software AS t1 WHERE t1.software_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=6 AND t2.sach_source=0)")
	List<SoftwareModel> getSoftwareDataFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM software_copy AS t1 WHERE t1.review=3 AND t1.software_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=6 AND t2.sach_source=1)")
	List<SoftwareModel> getSoftwareCopyFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Insert("INSERT INTO software "
			+ "(software_name, software_num, software_acquireType)"
			+ " VALUES "
			+ "(#{name}, #{num}, #{acquireType})")
	public void insertSoftwareToMyStockDao(
			@Param(value = "name") String name,
			@Param(value = "num") String num,
			@Param(value = "acquireType") int acquireType
	);
	
	@Insert("INSERT INTO software_copy "
			+ "(software_name, software_num, software_acquireType, review, submitId)"
			+ " VALUES "
			+ "(#{name}, #{num}, #{acquireType}, #{review}, #{submitId})")
	public void insertSoftwareToMyCopyStockDao(
			@Param(value = "name") String name,
			@Param(value = "num") String num,
			@Param(value = "acquireType") int acquireType,
			@Param(value = "review") int review,
			@Param(value = "submitId") int submitId
	);

	@Select("SELECT MAX(software_id) FROM software")
	int getSoftwareInsertNoteId();

	@Select("SELECT MAX(software_id) FROM software_copy")
	int getSoftwareCopyInsertNoteId();

	@Update("UPDATE software SET software_name=#{name}, software_num=#{num}, software_acquireType=#{acquireType} WHERE software_id=#{harId}")
	void updateSoftwareDao(@Param(value = "harId") int harId, @Param(value = "name") String name, @Param(value = "num") String num, @Param(value = "acquireType") int acquireType);

	@Update("UPDATE software_copy SET software_name=#{name}, software_num=#{num}, software_acquireType=#{acquireType} WHERE software_id=#{harId}")
	void updateSoftwareCopyDao(@Param(value = "harId") int harId, @Param(value = "name") String name, @Param(value = "num") String num, @Param(value = "acquireType") int acquireType);

	@Update("UPDATE software_copy SET review=#{review},reviseId=#{reviseId} WHERE software_id=#{harId}")
	void updateSoftwareCopyReviewDao(@Param(value = "harId") int harId, @Param(value = "review") int review, @Param(value = "reviseId") int reviseId);
}
