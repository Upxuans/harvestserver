package service.app.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.SubjectModel;

@Mapper
public interface SubjectDao {

	//找ID为userid(教师/学生)的学术专著
	@Select("SELECT * FROM subject AS t1 WHERE t1.subject_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=5 AND t2.tach_source=0)")
	List<SubjectModel> getSubjectDataFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM subject_copy AS t1 WHERE t1.review=3 AND t1.subject_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=5 AND t2.tach_source=1)")
	List<SubjectModel> getSubjectCopyFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Select("SELECT * FROM subject AS t1 WHERE t1.subject_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=5 AND t2.sach_source=0)")
	List<SubjectModel> getSubjectDataFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Select("SELECT * FROM subject_copy AS t1 WHERE t1.review=3 AND t1.subject_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=5 AND t2.sach_source=1)")
	List<SubjectModel> getSubjectCopyFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Insert("INSERT INTO subject "
			+ "(subject_title, subject_bookName, subject_status, subject_isbn, subject_publish, subject_publishDate, subject_doi)"
			+ " VALUES "
			+ "(#{title}, #{bookName}, #{status}, #{isbn}, #{publish}, #{publishDate}, #{doi})")
	public void insertSubjectToMyStockDao(
			@Param(value = "title") String title,
			@Param(value = "bookName") String bookName,
			@Param(value = "status") int status,
			@Param(value = "isbn") String isbn,
			@Param(value = "publish") String publish,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "doi") String doi
	);
	
	@Insert("INSERT INTO subject_copy "
			+ "(subject_title, subject_bookName, subject_status, subject_isbn, subject_publish, subject_publishDate, subject_doi, review, submitId)"
			+ " VALUES "
			+ "(#{title}, #{bookName}, #{status}, #{isbn}, #{publish}, #{publishDate}, #{doi}, #{review}, #{submitId})")
	public void insertSubjectToMyCopyStockDao(
			@Param(value = "title") String title,
			@Param(value = "bookName") String bookName,
			@Param(value = "status") int status,
			@Param(value = "isbn") String isbn,
			@Param(value = "publish") String publish,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "doi") String doi,
			@Param(value = "review") int review,
			@Param(value = "submitId") int submitId
	);

	@Select("SELECT MAX(subject_id) FROM subject")
	int getSubjectInsertNoteId();

	@Select("SELECT MAX(subject_id) FROM subject_copy")
	int getSubjectCopyInsertNoteId();

	@Update("UPDATE subject SET "
			+ "subject_title=#{title}, subject_bookName=#{bookName}, subject_status=#{status}, subject_isbn=#{isbn}, subject_publish=#{publish}, subject_publishDate=#{publishDate}, subject_doi=#{doi}"
			+ "WHERE subject_id=#{harId}")
	void updateSubjectDao(
			@Param(value = "harId") int harId,
			@Param(value = "title") String title,
			@Param(value = "bookName") String bookName,
			@Param(value = "status") int status,
			@Param(value = "isbn") String isbn,
			@Param(value = "publish") String publish,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "doi") String doi
	);

	@Update("UPDATE subject_copy SET "
			+ "subject_title=#{title}, subject_bookName=#{bookName}, subject_status=#{status}, subject_isbn=#{isbn}, subject_publish=#{publish}, subject_publishDate=#{publishDate}, subject_doi=#{doi}"
			+ "WHERE subject_id=#{harId}")
	void updateSubjectCopyDao(
			@Param(value = "harId") int harId,
			@Param(value = "title") String title,
			@Param(value = "bookName") String bookName,
			@Param(value = "status") int status,
			@Param(value = "isbn") String isbn,
			@Param(value = "publish") String publish,
			@Param(value = "publishDate") Date publishDate,
			@Param(value = "doi") String doi
	);

	@Update("UPDATE subject_copy SET review=#{review},reviseId=#{reviseId} WHERE subject_id=#{harId}")
	void updateSubjectCopyReviewDao(@Param(value = "harId") int harId, @Param(value = "review") int review, @Param(value = "reviseId") int reviseId);
}
