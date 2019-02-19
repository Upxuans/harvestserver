package service.app.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.AffairsModel;

@Mapper
public interface AffairsDao {

	//找ID为userid(教师/学生)的公共事务
	@Select("SELECT * FROM affairs AS t1 WHERE t1.affairs_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=7 AND t2.tach_source=0)")
	List<AffairsModel> getAffairsDataFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM affairs_copy AS t1 WHERE t1.review=3 AND t1.affairs_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=7 AND t2.tach_source=1)")
	List<AffairsModel> getAffairsCopyFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Select("SELECT * FROM affairs AS t1 WHERE t1.affairs_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=7 AND t2.sach_source=0)")
	List<AffairsModel> getAffairsDataFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM affairs_copy AS t1 WHERE t1.review=3 AND t1.affairs_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=7 AND t2.sach_source=1)")
	List<AffairsModel> getAffairsCopyFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Insert("INSERT INTO affairs "
			+ "(affairs_title, affairs_content, affairs_date1, affairs_date2)"
			+ " VALUES "
			+ "(#{title}, #{content}, #{date1}, #{date2})")
	public void insertAffairsToMyStockDao(
			@Param(value = "title") String title,
			@Param(value = "content") String content,
			@Param(value = "date1") Date date1,
			@Param(value = "date2") Date date2
	);
	
	@Insert("INSERT INTO affairs_copy "
			+ "(affairs_title, affairs_content, affairs_date1, affairs_date2, review, submitId)"
			+ " VALUES "
			+ "(#{title}, #{content}, #{date1}, #{date2}, #{review}, #{submitId})")
	public void insertAffairsToMyCopyStockDao(
			@Param(value = "title") String title,
			@Param(value = "content") String content,
			@Param(value = "date1") Date date1,
			@Param(value = "date2") Date date2,
			@Param(value = "review") int review,
			@Param(value = "submitId") int submitId
	);

	@Select("SELECT MAX(affairs_id) FROM affairs")
	int getAffairsInsertNoteId();

	@Select("SELECT MAX(affairs_id) FROM affairs_copy")
	int getAffairsCopyInsertNoteId();

	@Update("UPDATE affairs SET "
			+ "affairs_title=#{title}, affairs_content=#{content}, affairs_date1=#{date1}, affairs_date2=#{date2} "
			+ "WHERE affairs_id=#{harId}")
	void updateAffairsDao(
			@Param(value = "harId") int harId, 
			@Param(value = "title") String title,
			@Param(value = "content") String content,
			@Param(value = "date1") Date date1,
			@Param(value = "date2") Date date2
	);

	@Update("UPDATE affairs_copy SET "
			+ "affairs_title=#{title}, affairs_content=#{content}, affairs_date1=#{date1}, affairs_date2=#{date2} "
			+ "WHERE affairs_id=#{harId}")
	void updateAffairsCopyDao(
			@Param(value = "harId") int harId, 
			@Param(value = "title") String title,
			@Param(value = "content") String content,
			@Param(value = "date1") Date date1,
			@Param(value = "date2") Date date2
	);

	@Update("UPDATE affairs_copy SET review=#{review},reviseId=#{reviseId} WHERE affairs_id=#{harId}")
	void updateAffairsCopyReviewDao(@Param(value = "harId") int harId, @Param(value = "review") int review, @Param(value = "reviseId") int reviseId);
}
