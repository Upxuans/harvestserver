package service.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.AffairsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;

@Mapper
public interface AuditDao {

	//找到（学生）手动录入提交者的记录
	@Select("SELECT * FROM jpaper_copy WHERE submitId=#{userId}")
	public List<JpaperModel> getJpaperCopyBySubmitIdDao(@Param(value = "userId") int userId);
	
	@Select("SELECT * FROM mpaper_copy WHERE submitId=#{id}")
	public List<MpaperModel> getMpaperCopyBySubmitIdDao(@Param(value = "id") int id);
	
	@Select("SELECT * FROM patent_copy WHERE submitId=#{id}")
	List<PatentModel> getPatentCopyBySubmitIdDao(@Param(value = "id") int id);
	
	@Select("SELECT * FROM project_copy WHERE submitId=#{id}")
	List<ProjectModel> getProjectCopyBySubmitIdDao(@Param(value = "id") int id);
			
	@Select("SELECT * FROM software_copy WHERE submitId=#{id}")
	List<SoftwareModel> getSoftwareCopyBySubmitIdDao(@Param(value = "id") int id);
	
	@Select("SELECT * FROM subject_copy WHERE submitId=#{id}")
	List<SubjectModel> getSubjectCopyBySubmitIdDao(@Param(value = "id") int id);
	
	@Select("SELECT * FROM affairs_copy WHERE submitId=#{id}")
	List<AffairsModel> getAffairsCopyBySubmitIdDao(@Param(value = "id") int id);

	//删除学生手动录入的成果
	@Delete("DELETE FROM jpaper_copy WHERE jpaper_id=#{harId}")
	public void delJpaperCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM mpaper_copy WHERE mpaper_id=#{harId}")
	public void delMpaperCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM patent_copy WHERE patent_id=#{harId}")
	public void delPatentCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM project_copy WHERE project_id=#{harId}")
	public void delProjectCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM subject_copy WHERE subject_id=#{harId}")
	public void delSubjectCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM software_copy WHERE software_id=#{harId}")
	public void delSoftwareCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM affairs_copy WHERE affairs_id=#{harId}")
	public void delAffairsCopyNoteDao(@Param(value = "harId") int harId);

	//将copy表中的review更新
	@Update("UPDATE jpaper_copy SET review=#{review}, reviseId=#{userId} WHERE review=1 AND jpaper_id=#{harId}")
	public void UpdateJpaperReviewDao(@Param(value = "userId") int userId, @Param(value = "harId") int harId, @Param(value = "review") int review);

	@Update("UPDATE mpaper_copy SET review=#{review}, reviseId=#{userId} WHERE review=1 AND mpaper_id=#{harId}")
	public void UpdateMpaperReviewDao(@Param(value = "userId") int userId, @Param(value = "harId") int harId, @Param(value = "review") int review);

	@Update("UPDATE patent_copy SET review=#{review}, reviseId=#{userId} WHERE review=1 AND patent_id=#{harId}")
	public void UpdatePatentReviewDao(@Param(value = "userId") int userId, @Param(value = "harId") int harId, @Param(value = "review") int review);

	@Update("UPDATE project_copy SET review=#{review}, reviseId=#{userId} WHERE review=1 AND project_id=#{harId}")
	public void UpdateProjectReviewDao(@Param(value = "userId") int userId, @Param(value = "harId") int harId, @Param(value = "review") int review);

	@Update("UPDATE subject_copy SET review=#{review}, reviseId=#{userId} WHERE review=1 AND subject_id=#{harId}")
	public void UpdateSubjectReviewDao(@Param(value = "userId") int userId, @Param(value = "harId") int harId, @Param(value = "review") int review);

	@Update("UPDATE software_copy SET review=#{review}, reviseId=#{userId} WHERE review=1 AND software_id=#{harId}")
	public void UpdateSoftwareReviewDao(@Param(value = "userId") int userId, @Param(value = "harId") int harId, @Param(value = "review") int review);

	@Update("UPDATE affairs_copy SET review=#{review}, reviseId=#{userId} WHERE review=1 AND affairs_id=#{harId}")
	public void UpdateAffairsReviewDao(@Param(value = "userId") int userId, @Param(value = "harId") int harId, @Param(value = "review") int review);
	
}
