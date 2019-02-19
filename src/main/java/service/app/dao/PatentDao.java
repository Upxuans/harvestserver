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

import service.app.domain.PatentModel;

@Mapper
public interface PatentDao {

	//找ID为userid(教师/学生)的专利
	@Select("SELECT * FROM patent AS t1 WHERE t1.patent_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=3 AND t2.tach_source=0)")
	List<PatentModel> getPatentDataFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Select("SELECT * FROM patent_copy AS t1 WHERE t1.review=3 AND t1.patent_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=3 AND t2.tach_source=1)")
	List<PatentModel> getPatentCopyFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM patent AS t1 WHERE t1.patent_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=3 AND t2.sach_source=0)")
	List<PatentModel> getPatentDataFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM patent_copy AS t1 WHERE t1.review=3 AND t1.patent_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=3 AND t2.sach_source=1)")
	List<PatentModel> getPatentCopyFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Insert("INSERT INTO patent "
			+ "(patent_title, patent_num, patent_noteNum, patent_unit, patent_type, patent_status, patent_applyDate, patent_effectDate1, patent_effectDate2, patent_belong)"
			+ " VALUE "
			+ "(#{title}, #{num}, #{noteNum}, #{unit}, #{type}, #{status}, #{applyDate}, #{effectDate1}, #{effectDate2}, #{belong})")
	public void insertPatentToMyStockDao(
			@Param(value = "title") String title,
			@Param(value = "num") String num,
			@Param(value = "noteNum") String noteNum,
			@Param(value = "unit") String unit,
			@Param(value = "type") int type,
			@Param(value = "status") int status,
			@Param(value = "applyDate") Date applyDate,
			@Param(value = "effectDate1") Date effectDate1,
			@Param(value = "effectDate2") Date effectDate2,
			@Param(value = "belong") String belong
	);
	
	@Insert("INSERT INTO patent_copy "
			+ "(patent_title, patent_num, patent_noteNum, patent_unit, patent_type, patent_status, patent_applyDate, patent_effectDate1, patent_effectDate2, patent_belong, review, submitId)"
			+ " VALUE "
			+ "(#{title}, #{num}, #{noteNum}, #{unit}, #{type}, #{status}, #{applyDate}, #{effectDate1}, #{effectDate2}, #{belong}, #{review}, #{submitId})")
	public void insertPatentToMyCopyStockDao(
			@Param(value = "title") String title,
			@Param(value = "num") String num,
			@Param(value = "noteNum") String noteNum,
			@Param(value = "unit") String unit,
			@Param(value = "type") int type,
			@Param(value = "status") int status,
			@Param(value = "applyDate") Date applyDate,
			@Param(value = "effectDate1") Date effectDate1,
			@Param(value = "effectDate2") Date effectDate2,
			@Param(value = "belong") String belong,
			@Param(value = "review") int review,
			@Param(value = "submitId") int submitId
	);

	@Select("SELECT MAX(patent_id) FROM patent")
	int getPatentInsertNoteId();

	@Select("SELECT MAX(patent_id) FROM patent_copy")
	int getPatentCopyInsertNoteId();

	@Update("UPDATE patent SET "
			+ "patent_title=#{title}, patent_num=#{num}, patent_noteNum=#{noteNum}, patent_unit=#{unit}, patent_type=#{type}, patent_status=#{status}, patent_applyDate=#{applyDate}, patent_effectDate1=#{effectDate1}, patent_effectDate2=#{effectDate2}, patent_belong=#{belong} "
			+ "WHERE patent_id=#{harId}")
	void updatePatentDao(
			@Param(value = "harId") int harId,
			@Param(value = "title") String title,
			@Param(value = "num") String num,
			@Param(value = "noteNum") String noteNum,
			@Param(value = "unit") String unit,
			@Param(value = "type") int type,
			@Param(value = "status") int status,
			@Param(value = "applyDate") Date applyDate,
			@Param(value = "effectDate1") Date effectDate1,
			@Param(value = "effectDate2") Date effectDate2,
			@Param(value = "belong") String belong
	);

	@Update("UPDATE patent_copy SET "
			+ "patent_title=#{title}, patent_num=#{num}, patent_noteNum=#{noteNum}, patent_unit=#{unit}, patent_type=#{type}, patent_status=#{status}, patent_applyDate=#{applyDate}, patent_effectDate1=#{effectDate1}, patent_effectDate2=#{effectDate2}, patent_belong=#{belong} "
			+ "WHERE patent_id=#{harId}")
	void updatePatentCopyDao(
			@Param(value = "harId") int harId,
			@Param(value = "title") String title,
			@Param(value = "num") String num,
			@Param(value = "noteNum") String noteNum,
			@Param(value = "unit") String unit,
			@Param(value = "type") int type,
			@Param(value = "status") int status,
			@Param(value = "applyDate") Date applyDate,
			@Param(value = "effectDate1") Date effectDate1,
			@Param(value = "effectDate2") Date effectDate2,
			@Param(value = "belong") String belong
	);

	@Update("UPDATE patent_copy SET review=#{review},reviseId=#{reviseId} WHERE patent_id=#{harId}")
	void updatePatentCopyReviewDao(@Param(value = "harId") int harId, @Param(value = "review") int review, @Param(value = "reviseId") int reviseId);
}
