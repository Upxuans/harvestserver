/**
 * By Upxuan
 * 
 * Created in 2018/11/23
 */
package service.app.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.JpaperModel;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface JpaperDao {
	
	//1.找ID为userid(教师/学生)的期刊论文:
	//source=0表示是从jpaper表找，source=1表示从jpaper_copy表找的。
	//jpaper_copy表中review=3时才可是通过状态
	@Select("SELECT * FROM jpaper AS t1 WHERE t1.jpaper_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=1 AND t2.tach_source=0)")
	public List<JpaperModel> getJpaperDataFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM jpaper_copy AS t1 WHERE t1.review=3 AND t1.jpaper_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=1 AND t2.tach_source=1)")
	public List<JpaperModel> getJpaperCopyFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM jpaper AS t1 WHERE t1.jpaper_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=1 AND t2.sach_source=0)")
	public List<JpaperModel> getJpaperDataFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM jpaper_copy AS t1 WHERE t1.review=3 AND t1.jpaper_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=1 AND t2.sach_source=1)")
	public List<JpaperModel> getJpaperCopyFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	//在jpaper表中手动插入记录，返回jpaperId
	@Insert("INSERT INTO jpaper "
			+ "(jpaper_type1, jpaper_type2, jpaper_type3, jpaper_title, jpaper_paperName, jpaper_status, jpaper_publishDate, jpaper_reel, jpaper_issue, jpaper_page1, jpaper_page2, jpaper_doi)"
			+ " VALUES "
			+ "( #{type1}, #{type2}, #{type3}, #{title}, #{paperName}, #{status}, #{publishDate}, #{reel}, #{issue}, #{page1}, #{page2}, #{doi})")
	public void insertJpaperToMyStockDao(
			@Param(value = "type1") String type1,
			@Param(value = "type2") String type2,
			@Param(value = "type3") String type3,
			@Param(value = "title") String title,
			@Param(value = "paperName") String paperName,
			@Param(value = "status") int status,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "reel") String reel,
			@Param(value = "issue") String issue,
			@Param(value = "page1") String page1,
			@Param(value = "page2") String page2,
			@Param(value = "doi") String doi
	);

	@Insert("INSERT INTO jpaper_copy "
			+ "(jpaper_type1, jpaper_type2, jpaper_type3, jpaper_title, jpaper_paperName, jpaper_status, jpaper_publishDate, jpaper_reel, jpaper_issue, jpaper_page1, jpaper_page2, jpaper_doi, review, submitId)"
			+ " VALUES "
			+ "(#{type1}, #{type2}, #{type3}, #{title}, #{paperName}, #{status}, #{publishDate}, #{reel}, #{issue}, #{page1}, #{page2}, #{doi}, #{review}, #{submitId})")
	public void insertJpaperToMyCopyStockDao(
			@Param(value = "type1") String type1,
			@Param(value = "type2") String type2,
			@Param(value = "type3") String type3,
			@Param(value = "title") String title,
			@Param(value = "paperName") String paperName,
			@Param(value = "status") int status,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "reel") String reel,
			@Param(value = "issue") String issue,
			@Param(value = "page1") String page1,
			@Param(value = "page2") String page2,
			@Param(value = "doi") String doi,
			@Param(value = "review") int review,
			@Param(value = "submitId") int submitId
	);

	@Select("SELECT MAX(jpaper_id) FROM jpaper")
	public int getJpaperInsertNoteId();

	@Select("SELECT MAX(jpaper_id) FROM jpaper_copy")
	public int getJpaperCopyInsertNoteId();

	@Update("UPDATE jpaper SET "
			+ "jpaper_type1=#{type1},jpaper_type2=#{type2},jpaper_type3=#{type3},jpaper_title=#{title},jpaper_paperName=#{paperName},jpaper_status=#{status},jpaper_publishDate=#{publishDate},jpaper_reel=#{reel},jpaper_issue=#{issue},jpaper_page1=#{page1},jpaper_page2=#{page2},jpaper_doi=#{doi} "
			+ "WHERE jpaper_id=#{harId}")
	public void updateJpaperDao(
			@Param(value = "harId") int harId,
			@Param(value = "type1") String type1,
			@Param(value = "type2") String type2,
			@Param(value = "type3") String type3,
			@Param(value = "title") String title,
			@Param(value = "paperName") String paperName,
			@Param(value = "status") int status,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "reel") String reel,
			@Param(value = "issue") String issue,
			@Param(value = "page1") String page1,
			@Param(value = "page2") String page2,
			@Param(value = "doi") String doi
	);
	
	@Update("UPDATE jpaper_copy SET "
			+ "jpaper_type1=#{type1},jpaper_type2=#{type2},jpaper_type3=#{type3},jpaper_title=#{title},jpaper_paperName=#{paperName},jpaper_status=#{status},jpaper_publishDate=#{publishDate},jpaper_reel=#{reel},jpaper_issue=#{issue},jpaper_page1=#{page1},jpaper_page2=#{page2},jpaper_doi=#{doi} "
			+ "WHERE jpaper_id=#{harId}")
	public void updateJpaperCopyDao(
			@Param(value = "harId") int harId,
			@Param(value = "type1") String type1,
			@Param(value = "type2") String type2,
			@Param(value = "type3") String type3,
			@Param(value = "title") String title,
			@Param(value = "paperName") String paperName,
			@Param(value = "status") int status,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "reel") String reel,
			@Param(value = "issue") String issue,
			@Param(value = "page1") String page1,
			@Param(value = "page2") String page2,
			@Param(value = "doi") String doi
	);

	@Update("UPDATE jpaper_copy SET review=#{review},reviseId=#{reviseId} WHERE jpaper_id=#{harId}")
	public void updateJpaperCopyReviewDao(@Param(value = "harId") int harId, @Param(value = "review") int review, @Param(value = "reviseId") int reviseId);

	//union的使用(success)
//	@Select("SELECT t1.jpaper_id,t1.jpaper_type1,t1.jpaper_type2,t1.jpaper_type3,t1.jpaper_title,t1.jpaper_status,\"\",\"\",\"\" FROM jpaper AS t1 WHERE t1.jpaper_id IN"
//			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userid} AND t2.tach_load=#{load} AND t2.tach_harType=1 AND t2.tach_show=2)"
//			+ " UNION "
//		  + "SELECT t1.jpaper_id,t1.jpaper_type1,t1.jpaper_type2,t1.jpaper_type3,t1.jpaper_title,t1.jpaper_status,review,submitId,reviseId FROM jpaper_copy AS t1 WHERE t1.jpaper_id IN"
//			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userid} AND t2.tach_load=#{load} AND t2.tach_harType=1 AND t2.tach_show=1)")
//	public List<JpaperModel> getTestDao(@Param(value = "userid") int userid, @Param(value = "load") int load);
}
