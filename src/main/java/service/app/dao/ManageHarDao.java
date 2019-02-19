package service.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import service.app.domain.AffairsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;

@Mapper
public interface ManageHarDao {

	@Select("SELECT * FROM jpaper")
	List<JpaperModel> getAllJpaperDao();

	@Select("SELECT * FROM mpaper")
	List<MpaperModel> getAllMpaperDao();

	@Select("SELECT * FROM patent")
	List<PatentModel> getAllPatentDao();

	@Select("SELECT * FROM project")
	List<ProjectModel> getAllProjectDao();

	@Select("SELECT * FROM subject")
	List<SubjectModel> getAllSubjectDao();

	@Select("SELECT * FROM software")
	List<SoftwareModel> getAllSoftwareDao();

	@Select("SELECT * FROM affairs")
	List<AffairsModel> getAllAffairsDao();
	
	@Select("SELECT * FROM jpaper_copy")
	List<JpaperModel> getAllJpaperCopyDao();

	@Select("SELECT * FROM mpaper_copy")
	List<MpaperModel> getAllMpaperCopyDao();

	@Select("SELECT * FROM patent_copy")
	List<PatentModel> getAllPatentCopyDao();

	@Select("SELECT * FROM project_copy")
	List<ProjectModel> getAllProjectCopyDao();

	@Select("SELECT * FROM subject_copy")
	List<SubjectModel> getAllSubjectCopyDao();

	@Select("SELECT * FROM software_copy")
	List<SoftwareModel> getAllSoftwareCopyDao();

	@Select("SELECT * FROM affairs_copy")
	List<AffairsModel> getAllAffairsCopyDao();
	
	@Delete("DELETE FROM jpaper WHERE jpaper_id=#{harId}")
	void delJpaperNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM mpaper WHERE mpaper_id=#{harId}")
	void delMpaperNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM patent WHERE patent_id=#{harId}")
	void delPatentNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM project WHERE project_id=#{harId}")
	void delProjectNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM subject WHERE subject_id=#{harId}")
	void delSubjectNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM software WHERE software_id=#{harId}")
	void delSoftwareNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM affairs WHERE affairs_id=#{harId}")
	void delAffairsNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM jpaper_copy WHERE jpaper_id=#{harId}")
	void delJpaperCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM mpaper_copy WHERE mpaper_id=#{harId}")
	void delMpaperCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM patent_copy WHERE patent_id=#{harId}")
	void delPatentCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM project_copy WHERE project_id=#{harId}")
	void delProjectCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM subject_copy WHERE subject_id=#{harId}")
	void delSubjectCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM software_copy WHERE software_id=#{harId}")
	void delSoftwareCopyNoteDao(@Param(value = "harId") int harId);

	@Delete("DELETE FROM affairs_copy WHERE affairs_id=#{harId}")
	void delAffairsCopyNoteDao(@Param(value = "harId") int harId);
}
