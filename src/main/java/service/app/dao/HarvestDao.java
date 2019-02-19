package service.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import service.app.domain.AuthorsModel;
import service.app.domain.OthersModel;
import service.app.domain.SachModel;
import service.app.domain.TachModel;

@Mapper
public interface HarvestDao {
	
	//获取成果类型为harType，成果ID为harId的作者（老师/学生/非实验室人员）
	@Select("SELECT t1.name FROM teacher AS t1 WHERE t1.id IN"
			+ "(SELECT t2.tach_userId FROM tach AS t2 WHERE t2.tach_harType=#{harType} AND t2.tach_harId=#{harId} AND t2.tach_source=#{source})")
	public List<String> getAuthorsByTeacherIdDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);
	
	@Select("SELECT t1.name FROM student AS t1 WHERE t1.id IN"
			+ "(SELECT t2.sach_userId FROM sach AS t2 WHERE t2.sach_harType=#{harType} AND t2.sach_harId=#{harId} AND t2.sach_source=#{source})")
	public List<String> getAuthorsByStudentIdDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Select("SELECT t.others_name FROM others AS t WHERE t.others_harType=#{harType} AND t.others_harId=#{harId} AND t.others_source=#{source}")
	public List<String> getAuthorsByOthersIdDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	//找第一作者
	@Select("SELECT t1.name FROM teacher AS t1 WHERE t1.id IN"
			+ "(SELECT t2.tach_userId FROM tach AS t2 WHERE t2.tach_harType=#{harType} AND t2.tach_harId=#{harId} AND t2.tach_source=#{source} AND t2.tach_firstAuthor=1)")
	public String getFirstAuthorInTachDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Select("SELECT t1.name FROM student AS t1 WHERE t1.id IN"
			+ "(SELECT t2.sach_userId FROM sach AS t2 WHERE t2.sach_harType=#{harType} AND t2.sach_harId=#{harId} AND t2.sach_source=#{source} AND t2.sach_firstAuthor=1)")
	public String getFirstAuthorInSachDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Select("SELECT t.others_name FROM others AS t WHERE t.others_harType=#{harType} AND t.others_harId=#{harId} AND t.others_source=#{source} AND t.others_firstAuthor=1")
	public String getFirstAuthorInOthersDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);
	
	//找毕业条件归属者
	@Select("SELECT t1.name FROM student AS t1 WHERE t1.id IN"
			+ "(SELECT t2.sach_userId FROM sach AS t2 WHERE t2.sach_harType=#{harType} AND t2.sach_harId=#{harId} AND t2.sach_source=#{source} AND t2.sach_belong=1)")
	public String getBelongInSachDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	//通过id找到学生name
	@Select("SELECT name FROM student WHERE id=#{id}")
	public String getHarSubmitNameDao(@Param(value = "id") int id);

	//通过id找到老师name
	@Select("SELECT name FROM teacher WHERE id=#{id}")
	public String getHarReviseNameDao(int id);
	
	//删除关系source=0/1（harvest/harvest_copy）的关系
	@Delete("DELETE FROM tach WHERE tach_harType=#{harType} AND tach_harId=#{harId} AND tach_source=#{source}")
	public void delTachDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Delete("DELETE FROM sach WHERE sach_harType=#{harType} AND sach_harId=#{harId} AND sach_source=#{source}")
	public void delSachDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Delete("DELETE FROM others WHERE others_harType=#{harType} AND others_harId=#{harId} AND others_source=#{source}")
	public void delOthersDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	//得到一个成果记录的作者list
	@Select("SELECT * FROM tach WHERE tach_harType=#{harType} AND tach_harId=#{harId} AND tach_source=#{source}")
	public List<TachModel> getTachAuthorsListDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Select("SELECT * FROM sach WHERE sach_harType=#{harType} AND sach_harId=#{harId} AND sach_source=#{source}")
	public List<SachModel> getSachAuthorsListDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);

	@Select("SELECT * FROM others WHERE others_harType=#{harType} AND others_harId=#{harId} AND others_source=#{source}")
	public List<OthersModel> getOthersAuthorsListDao(@Param(value = "harType") int harType, @Param(value = "harId") int harId, @Param(value = "source") int source);
	
	
	
	
	
	
	
	
}
