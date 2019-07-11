package service.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import service.app.domain.OthersModel;
import service.app.domain.SachModel;
import service.app.domain.TachModel;

@Mapper
public interface HandleHarDao {

	@Select("SELECT t.tach_load FROM tach AS t WHERE t.tach_userId=#{userId} AND t.tach_harType=#{harType} AND t.tach_harId=#{harId}")
	public int selectTeacherHarvestDao(@Param(value = "userId") int userId, @Param(value = "harType") int harType, @Param(value = "harId") int harId);
	
	@Update("UPDATE tach AS t SET t.tach_load=#{load} "
			+ "WHERE t.tach_load=#{unload} AND t.tach_userId=#{userId} AND t.tach_harType=#{harType} AND t.tach_harId=#{harId} AND t.tach_source=#{source}")
	public void setTachLoadDao(@Param(value = "userId") int userId, @Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source, @Param(value = "load") int load, @Param(value = "unload") int unload);
	
	@Update("UPDATE sach AS t SET t.sach_load=#{load} "
			+ "WHERE t.sach_load=#{unload} AND t.sach_userId=#{userId} AND t.sach_harType=#{harType} AND t.sach_harId=#{harId} AND t.sach_source=#{source}")
	public void setSachLoadDao(@Param(value = "userId") int userId, @Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source, @Param(value = "load") int load, @Param(value = "unload") int unload);

	@Select("SELECT t.id FROM teacher AS t WHERE t.name=#{name}")
	public Integer selectTeacherByNameDao(@Param(value = "name") String name);
	
	@Select("SELECT s.id FROM student AS s WHERE s.name=#{name}")
	public Integer selectStudentByNameDao(@Param(value = "name") String name);

	@Insert("INSERT INTO tach "
			+ "(tach_userId, tach_harType, tach_harId, tach_firstAuthor, tach_load, tach_source)"
			+ " VALUES "
			+ "(#{userId}, #{harType}, #{harId}, #{firstAuthor}, #{load}, #{source})")
	public void insertTach(
			@Param(value = "userId") Integer userId,
			@Param(value = "harType") int harType,
			@Param(value = "harId") int harId,
			@Param(value = "firstAuthor") int firstAuthor,
			@Param(value = "load") int load,
			@Param(value = "source") int source
	);
	
	@Insert("INSERT INTO sach "
			+ "(sach_userId, sach_harType, sach_harId, sach_firstAuthor, sach_belong, sach_load, sach_source)"
			+ " VALUE "
			+ "(#{userId}, #{harType}, #{harId}, #{firstAuthor}, #{belong}, #{load}, #{source})")
	public void insertSach(
			@Param(value = "userId") Integer userId,
			@Param(value = "harType") int harType,
			@Param(value = "harId") int harId,
			@Param(value = "firstAuthor") int firstAuthor,
			@Param(value = "belong") int belong,
			@Param(value = "load") int load,
			@Param(value = "source") int source
	);

	@Select("SELECT count(*) FROM teacher")
	public Integer getTeacherCount();
	
	@Select("SELECT count(*) FROM student")
	public Integer getStudentCount();
	
	@Select("SELECT count(*) FROM others")
	public Integer getOthersCount();

	@Insert("INSERT INTO teacher (name) VALUE (#{name})")
	public void insertTeacher(@Param(value = "name") String name);

	@Insert("INSERT INTO student (name, devices, keyss, works) VALUE (#{name}, #{devices}, #{keyss}, #{works})")
	public void insertStudent(@Param(value = "name") String name, @Param(value = "devices") int devices, @Param(value = "keyss") int keyss, @Param(value = "works") int works);

	@Insert("INSERT INTO others "
			+ "(others_name, others_harType, others_harId, others_firstAuthor, others_source)"
			+ " VALUE "
			+ "(#{name}, #{harType}, #{harId}, #{firstAuthor}, #{source})")
	public void insertOthers(
			@Param(value = "name") String name,
			@Param(value = "harType") int harType,
			@Param(value = "harId") int harId,
			@Param(value = "firstAuthor") int firstAuthor,
			@Param(value = "source") int source
	);
	
	@Select("SELECT MAX(id) FROM teacher")
	public int getTeacherInsertId();

	@Select("SELECT MAX(id) FROM student")
	public int getStudentInsertId();

	@Select("SELECT * FROM tach WHERE tach_harType=#{harType} AND tach_harId=#{harId} AND tach_source=#{source}")
	public List<TachModel> getTachNotesDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Select("SELECT * FROM sach WHERE sach_harType=#{harType} AND sach_harId=#{harId} AND sach_source=#{source}")
	public List<SachModel> getSachNotesDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Select("SELECT * FROM others WHERE others_harType=#{harType} AND others_harId=#{harId} AND others_source=#{source}")
	public List<OthersModel> getOthersNotesDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Update("UPDATE tach SET "
			+ "tach_firstAuthor=#{firstAuthor} "
			+ "WHERE tach_userId=#{userId} AND tach_harType=#{harType} AND tach_harId=#{harId} AND tach_source=#{source}")
	public void updateTach(
			@Param(value = "firstAuthor") int firstAuthor,
			@Param(value = "userId") int userId,
			@Param(value = "harType") int harType, 
			@Param(value = "harId") int harId, 
			@Param(value = "source") int source
	);

	@Update("UPDATE sach SET "
			+ "sach_firstAuthor=#{firstAuthor},sach_belong=#{belong} "
			+ "WHERE sach_userId=#{userId} AND sach_harType=#{harType} AND sach_harId=#{harId} AND sach_source=#{source}")
	public void updateSach(
			@Param(value = "firstAuthor") int firstAuthor, 
			@Param(value = "belong") int belong, 
			@Param(value = "userId") int userId, 
			@Param(value = "harType") int harType, 
			@Param(value = "harId") int harId, 
			@Param(value = "source") int source
	);

	@Update("UPDATE others SET "
			+ "others_firstAuthor=#{firstAuthor} "
			+ "WHERE others_id=#{id} AND others_harType=#{harType} AND others_harId=#{harId} AND others_source=#{source}")
	public void updateOthers(
			@Param(value = "firstAuthor") int firstAuthor,
			@Param(value = "id") int id,
			@Param(value = "harType") int harType, 
			@Param(value = "harId") int harId, 
			@Param(value = "source") int source
	);
	
	@Delete("DELETE FROM tach WHERE tach_userId=#{userId} AND tach_harType=#{harType} AND tach_harId=#{harId} AND tach_source=#{source}")
	public void deleteTach(@Param(value = "userId") int userId, @Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Delete("DELETE FROM sach WHERE sach_userId=#{userId} AND sach_harType=#{harType} AND sach_harId=#{harId} AND sach_source=#{source}")
	public void deleteSach(@Param(value = "userId") int userId, @Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Delete("DELETE FROM others WHERE others_id=#{id} AND others_harType=#{harType} AND others_harId=#{harId} AND others_source=#{source}")
	public void deleteOthers(@Param(value = "id") int id, @Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);



//	@Delete("DELETE FROM tach WHERE tach_harType=#{harType} AND tach_harId=#{harId} AND tach_source=#{source}")
//	public void delTachNotesDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);
//
//	@Delete("DELETE FROM sach WHERE sach_harType=#{harType} AND sach_harId=#{harId} AND sach_source=#{source}")
//	public void delSachNotesDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);
//
//	@Delete("DELETE FROM others WHERE others_harType=#{harType} AND others_harId=#{harId} AND others_source=#{source}")
//	public void delOthersNotesDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);
	
	
	
}
