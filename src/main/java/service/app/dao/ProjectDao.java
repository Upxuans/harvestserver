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

import service.app.domain.ProjectModel;

@Mapper
public interface ProjectDao {
	
	//找ID为userid(教师/学生)的会议论文
	@Select("SELECT * FROM project AS t1 WHERE t1.project_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=4 AND t2.tach_source=0)")
	List<ProjectModel> getProjectDataFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);

	@Select("SELECT * FROM project_copy AS t1 WHERE t1.review=3 AND t1.project_id IN"
			+ "(SELECT t2.tach_harId FROM tach AS t2 WHERE t2.tach_userId=#{userId} AND t2.tach_load=#{load} AND t2.tach_harType=4 AND t2.tach_source=1)")
	List<ProjectModel> getProjectCopyFromTeacherDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM project AS t1 WHERE t1.project_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=4 AND t2.sach_source=0)")
	List<ProjectModel> getProjectDataFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Select("SELECT * FROM project_copy AS t1 WHERE t1.review=3 AND t1.project_id IN"
			+ "(SELECT t2.sach_harId FROM sach AS t2 WHERE t2.sach_userId=#{userId} AND t2.sach_load=#{load} AND t2.sach_harType=4 AND t2.sach_source=1)")
	List<ProjectModel> getProjectCopyFromStudentDao(@Param(value = "userId") int userId, @Param(value = "load") int load);
	
	@Insert("INSERT INTO project "
			+ "(project_type, project_title, project_num, project_date1, project_date2, project_money)"
			+ " VALUES "
			+ "(#{type}, #{title}, #{num}, #{date1}, #{date2}, #{money})")
	public void insertProjectToMyStockDao(
			@Param(value = "type") String type,
			@Param(value = "title") String title,
			@Param(value = "num") String num,
			@Param(value = "date1") Date date1,
			@Param(value = "date2") Date date2,
			@Param(value = "money") String money
	);
	
	@Insert("INSERT INTO project_copy "
			+ "(project_type, project_title, project_num, project_date1, project_date2, project_money, review, submitId)"
			+ " VALUES "
			+ "(#{type}, #{title}, #{num}, #{date1}, #{date2}, #{money}, #{review}, #{submitId})")
	public void insertProjectToMyCopyStockDao(
			@Param(value = "type") String type,
			@Param(value = "title") String title,
			@Param(value = "num") String num,
			@Param(value = "date1") Date date1,
			@Param(value = "date2") Date date2,
			@Param(value = "money") String money,
			@Param(value = "review") int review,
			@Param(value = "submitId") int submitId
	);

	@Select("SELECT MAX(project_id) FROM project")
	int getProjectInsertNoteId();

	@Select("SELECT MAX(project_id) FROM project_copy")
	int getProjectCopyInsertNoteId();

	@Update("UPDATE project SET "
			+ "project_type=#{type}, project_title=#{title}, project_num=#{num}, project_date1=#{date1}, project_date2=#{date2}, project_money=#{money} "
			+ "WHERE project_id=#{harId}")
	void updateProjectDao(
			@Param(value = "harId") int harId,
			@Param(value = "type") String type,
			@Param(value = "title") String title,
			@Param(value = "num") String num,
			@Param(value = "date1") Date date1,
			@Param(value = "date2") Date date2,
			@Param(value = "money") String money
	);

	@Update("UPDATE project_copy SET "
			+ "project_type=#{type}, project_title=#{title}, project_num=#{num}, project_date1=#{date1}, project_date2=#{date2}, project_money=#{money} "
			+ "WHERE project_id=#{harId}")
	void updateProjectCopyDao(
			@Param(value = "harId") int harId,
			@Param(value = "type") String type,
			@Param(value = "title") String title,
			@Param(value = "num") String num,
			@Param(value = "date1") Date date1,
			@Param(value = "date2") Date date2,
			@Param(value = "money") String money
	);

	@Update("UPDATE project_copy SET review=#{review},reviseId=#{reviseId} WHERE project_id=#{harId}")
	void updateProjectCopyReviewDao(@Param(value = "harId") int harId, @Param(value = "review") int review, @Param(value = "reviseId") int reviseId);
}
