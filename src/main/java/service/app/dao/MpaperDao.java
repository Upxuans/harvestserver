/**
 * By Upxuan
 */
package service.app.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.MpaperModel;

@Mapper
public interface MpaperDao {

	//1.找ID为userid(教师/学生)的会议论文
	@Select("SELECT * FROM mpaper AS t1 WHERE t1.mpaper_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=2 AND t2.tach_source=0)")
	public List<MpaperModel> getMpaperDataFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Select("SELECT * FROM mpaper_copy AS t1 WHERE t1.review=3 AND t1.mpaper_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=2 AND t2.tach_source=1)")
	public List<MpaperModel> getMpaperCopyFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM mpaper AS t1 WHERE t1.mpaper_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=2 AND t2.sach_source=0)")
	public List<MpaperModel> getMpaperDataFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Select("SELECT * FROM mpaper_copy AS t1 WHERE t1.review=3 AND t1.mpaper_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=2 AND t2.sach_source=1)")
	public List<MpaperModel> getMpaperCopyFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Insert("INSERT INTO mpaper "
			+ "(mpaper_type1, mpaper_type2, mpaper_type3, mpaper_title, mpaper_publishDate, mpaper_page1, mpaper_page2, mpaper_meetName, mpaper_meetDate1, mpaper_meetDate2, mpaper_meetPlace)"
			+ " VALUES "
			+ "(#{type1}, #{type2}, #{type3}, #{title}, #{publishDate}, #{page1}, #{page2}, #{meetName}, #{meetDate1}, #{meetDate2}, #{meetPlace})")
	public void insertMpaperToMyStockDao(
			@Param(value = "type1") String type1,
			@Param(value = "type2") String type2,
			@Param(value = "type3") String type3,
			@Param(value = "title") String title,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "page1") String page1,
			@Param(value = "page2") String page2,
			@Param(value = "meetName") String meetName,
			@Param(value = "meetDate1") Date meetDate1,
			@Param(value = "meetDate2") Date meetDate2,
			@Param(value = "meetPlace") String meetPlace
	);

	@Insert("INSERT INTO mpaper_copy "
			+ "(mpaper_type1, mpaper_type2, mpaper_type3, mpaper_title, mpaper_publishDate, mpaper_page1, mpaper_page2, mpaper_meetName, mpaper_meetDate1, mpaper_meetDate2, mpaper_meetPlace, review, submitId)"
			+ " VALUES "
			+ "(#{type1}, #{type2}, #{type3}, #{title}, #{publishDate}, #{page1}, #{page2}, #{meetName}, #{meetDate1}, #{meetDate2}, #{meetPlace}, #{review}, #{submitId})")
	public void insertMpaperToMyCopyStockDao(
			@Param(value = "type1") String type1,
			@Param(value = "type2") String type2,
			@Param(value = "type3") String type3,
			@Param(value = "title") String title,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "page1") String page1,
			@Param(value = "page2") String page2,
			@Param(value = "meetName") String meetName,
			@Param(value = "meetDate1") Date meetDate1,
			@Param(value = "meetDate2") Date meetDate2,
			@Param(value = "meetPlace") String meetPlace,
			@Param(value = "review") int review,
			@Param(value = "submitId") int submitId
	);

	@Select("SELECT MAX(mpaper_id) FROM mpaper")
	public int getMpaperInsertNoteId();

	@Select("SELECT MAX(mpaper_id) FROM mpaper_copy")
	public int getMpaperCopyInsertNoteId();

	@Update("UPDATE mpaper SET "
			+ "mpaper_type1=#{type1},mpaper_type2=#{type2},mpaper_type3=#{type3},mpaper_title=#{title},mpaper_publishDate=#{publishDate},mpaper_page1=#{page1},mpaper_page2=#{page2},mpaper_meetName=#{meetName},mpaper_meetDate1=#{meetDate1},mpaper_meetDate2=#{meetDate2},mpaper_meetPlace=#{meetPlace} "
			+ "WHERE mpaper_id=#{harId}")
	public void updateMpaperDao(
			@Param(value = "harId") int harId,
			@Param(value = "type1") String type1,
			@Param(value = "type2") String type2,
			@Param(value = "type3") String type3,
			@Param(value = "title") String title,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "page1") String page1,
			@Param(value = "page2") String page2,
			@Param(value = "meetName") String meetName,
			@Param(value = "meetDate1") Date meetDate1,
			@Param(value = "meetDate2") Date meetDate2,
			@Param(value = "meetPlace") String meetPlace
	);

	@Update("UPDATE mpaper_copy SET "
			+ "mpaper_type1=#{type1}, mpaper_type2=#{type2}, mpaper_type3=#{type3}, mpaper_title=#{title}, mpaper_publishDate=#{publishDate}, mpaper_page1=#{page1}, mpaper_page2=#{page2}, mpaper_meetName=#{meetName}, mpaper_meetDate1=#{meetDate1}, mpaper_meetDate2=#{meetDate2}, mpaper_meetPlace=#{meetPlace} "
			+ "WHERE mpaper_id=#{harId}")
	public void updateMpaperCopyDao(
			@Param(value = "harId") int harId,
			@Param(value = "type1") String type1,
			@Param(value = "type2") String type2,
			@Param(value = "type3") String type3,
			@Param(value = "title") String title,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "page1") String page1,
			@Param(value = "page2") String page2,
			@Param(value = "meetName") String meetName,
			@Param(value = "meetDate1") Date meetDate1,
			@Param(value = "meetDate2") Date meetDate2,
			@Param(value = "meetPlace") String meetPlace
	);

	@Update("UPDATE mpaper_copy SET review=#{review},reviseId=#{reviseId} WHERE mpaper_id=#{harId}")
	public void updateMpaperCopyReviewDao(@Param(value = "harId") int harId, @Param(value = "review") int review, @Param(value = "reviseId") int reviseId);
}
