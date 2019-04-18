/**
 * By Upxuan
 * 
 * Created in 2018/10
 */
package service.app.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.app.domain.AffairsModel;
import service.app.domain.AuthorsModel;
import service.app.domain.JpaperModel;
import service.app.domain.MpaperModel;
import service.app.domain.PatentModel;
import service.app.domain.ProjectModel;
import service.app.domain.SoftwareModel;
import service.app.domain.SubjectModel;
import service.app.service.HarvestService;

@Component
public class HarvestUtil {
	
	@Autowired
	HarvestService harvests;
	 
//	private static String judgeType(int k) {
//		String type = null;
//		
//		if(k == 1) {
//			type = "期刊论文";
//		}else if(k == 2) {
//			type = "会议论文";
//		}else if(k == 3) {
//			type = "专利";
//		}else if(k == 4) {
//			type = "项目";
//		}else if(k == 5) {
//			type = "学术专著";
//		}else if(k == 6) {
//			type = "软件著作权";
//		}else if(k == 7) {
//			type = "公共事务";
//		}
//		return type;
//	}
	
	//文章的审核状态
	public String getReviseStatus(int revise) {
		String reviseStatus = "";
		if(revise == 1) 
			reviseStatus = "审核中";
		else if(revise == 2) 
			reviseStatus = "审核失败";
		else if(revise == 3) 
			reviseStatus = "审核通过";
		return reviseStatus;
	}
	
	//找到一篇文章的第一作者
	public String getHarFirstAuthor(int harType, int harId, int source) {
		String name = "";
		name = harvests.getFirstAuthorService(harType, harId, source);
		return name;
	}
	
	//找到一篇文章的毕业归属者（学生）
	public String getHarBelong(int harType, int harId, int source) {
		String belong = "";
		belong = harvests.getBelongService(harType, harId, source);
		return belong;
	}
	
	//找到一篇文章的提交者(学生)
	public String getHarSubmitName(int id) {
		String submitName = harvests.getHarSubmitNameService(id);
		return submitName;
	}
	
	//找到一篇文章的审核者(教师)
	public String getHarReviseName(Integer id) {
		String name = "";
		if(id != null) {
			name = harvests.getHarReviseNameService(id);
			if(id == -2) {
				name = "Admin";
			}
		}
		return name;
	}
	
	//删除关系
	public void delHarvestRelation(int harType, int harId, int source) {
		harvests.delHarvestRelationService(harType, harId, source);
	}
		
	//获取成果类型为harType，成果ID为harId的作者（老师/学生/非实验室人员）
	public String getAuthorsName(int harType, int harId, int source) {
		
		List<String> teacherStr = harvests.getTeacherAuthorsByIdService(harType, harId, source);
		List<String> studentStr = harvests.getStudentAuthorsByIdService(harType, harId, source);
		List<String> othersStr = harvests.getOthersAuthorsByIdService(harType, harId, source);
//		System.out.println("一轮作者：" + teacherStr + ',' + studentStr + ',' + othersStr);
		
		String authors = "";
		int lent = teacherStr.size();
		int lens = studentStr.size();
		int leno = othersStr.size();
		if(lent != 0) {
			authors += teacherStr.get(0);
			for(int i=1; i<lent; i++) 
				authors += "," + teacherStr.get(i);
		}
		if(lent != 0 && lens != 0) authors += ",";
		if(lens != 0) {
			authors += studentStr.get(0);
			for(int j=1; j<lens; j++) 
				authors += "," + studentStr.get(j);
		}
		if(lens != 0 && leno != 0) authors += ",";
		if(leno != 0) {
			authors += othersStr.get(0);
			for(int j=1; j<leno; j++) 
				authors += "," + othersStr.get(j);
		}
//		System.out.println(authors);
		return authors;
	}

	//创建作者的准备
	public List<JpaperModel> setJpaperDataAuthors(List<JpaperModel> data) {
		for(int i=0; i<data.size(); i++) {
//			System.out.println(data.get(i).getJpaperStatus());
			JpaperModel jModel = data.get(i);
			int source = 0;
			if(jModel.getReview() != null) source = 1;
			String authors = getAuthorsName(1, jModel.getJpaperId(), source);//传值harType,harId
			jModel.setJpaperAuthors(authors);//创建作者
			
			String status = jModel.getJpaperStatus();
			if(status.equals("1")) 
				jModel.setJpaperStatus("已发表");
			else if(status.equals("2")) 
				jModel.setJpaperStatus("已接收");
			else 
				jModel.setJpaperStatus("");
			
			String jpaperType = jModel.getJpaperType1();
            if(!jModel.getJpaperType2().equals(""))
            	jpaperType += '>' + jModel.getJpaperType2();
            if(!jModel.getJpaperType3().equals(""))
            	jpaperType += '>' + jModel.getJpaperType3();
            jModel.setJpaperType(jpaperType);
            
//			System.out.println(data.get(i).getJpaperStatus());
		}
		return data;
	}

	public List<MpaperModel> setMpaperDataAuthors(List<MpaperModel> data) {
		for(int i=0; i<data.size(); i++) {
			MpaperModel mModel = data.get(i);
			int source = 0;
			if(mModel.getReview() != null) source = 1;
			String authors = getAuthorsName(2, mModel.getMpaperId(), source);
			mModel.setMpaperAuthors(authors);//创建作者
			
			String jpaperType = mModel.getMpaperType1();
            if(!mModel.getMpaperType2().equals(""))
            	jpaperType += '>' + mModel.getMpaperType2();
            if(!mModel.getMpaperType3().equals(""))
            	jpaperType += '>' + mModel.getMpaperType3();
//            System.out.println("jpaperType:" + jpaperType);
            mModel.setMpaperType(jpaperType);
		}
		return data;
	}
	
	public List<PatentModel> setPatentDataAuthors(List<PatentModel> data) {
		for(int i=0; i<data.size(); i++) {
			PatentModel pModel = data.get(i);
			int source = 0;
			if(pModel.getReview() != null) source = 1;
			String authors = getAuthorsName(3, pModel.getPatentId(), source);
			pModel.setPatentAuthors(authors);
			
			String type = pModel.getPatentType();
			if(type.equals("1")) 
				pModel.setPatentType("发明专利");
			else if(type.equals("2")) 
				pModel.setPatentType("实用新型");
			else if(type.equals("3")) 
				pModel.setPatentType("外观设计");
			else
				pModel.setPatentType("");
			
			String status = pModel.getPatentStatus();
			if(status.equals("1")) 
				pModel.setPatentStatus("申请");
			else if(status.equals("2")) 
				pModel.setPatentStatus("授权");
			else 
				pModel.setPatentStatus("");
		}
		return data;
	}

	public List<ProjectModel> setProjectDataAuthors(List<ProjectModel> data) {
		for(int i=0; i<data.size(); i++) {
			ProjectModel pModel = data.get(i);
			int source = 0;
			if(pModel.getReview() != null) source = 1;
			String authors = getAuthorsName(4, pModel.getProjectId(), source);
			pModel.setProjectAuthors(authors);//创建作者
		}
		return data;
	}

	public List<SubjectModel> setSubjectDataAuthors(List<SubjectModel> data) {
		for(int i=0; i<data.size(); i++) {
			SubjectModel sModel = data.get(i);
			int source = 0;
			if(sModel.getReview() != null) source = 1;
			String authors = getAuthorsName(5, sModel.getSubjectId(), source);
			sModel.setSubjectAuthors(authors);//创建作者
			
			String status = sModel.getSubjectStatus();
			if(status.equals("1")) 
				sModel.setSubjectStatus("已出版");
			else if(status.equals("2")) 
				sModel.setSubjectStatus("待出版");
			else 
				sModel.setSubjectStatus("");
		}
		return data;
	}
	
	public List<SoftwareModel> setSoftwareDataAuthors(List<SoftwareModel> data) {
		for(int i=0; i<data.size(); i++) {
			SoftwareModel sModel = data.get(i);
			int source = 0;
			if(sModel.getReview() != null) source = 1;
			String authors = getAuthorsName(6, sModel.getSoftwareId(), source);
			sModel.setSoftwareAuthors(authors);//创建作者
			
			String status = sModel.getSoftwareAcquireType();
			if(status.equals("1")) 
				sModel.setSoftwareAcquireType("原始取得");
			else if(status.equals("2")) 
				sModel.setSoftwareAcquireType("继受取得");
			else 
				sModel.setSoftwareAcquireType("");
		}
		return data;
	}
	
	public List<AffairsModel> setAffairsDataAuthors(List<AffairsModel> data) {
		for(int i=0; i<data.size(); i++) {
			AffairsModel aModel = data.get(i);
			int source = 0;
			if(aModel.getReview() != null) source = 1;
			String authors = getAuthorsName(7, aModel.getAffairsId(), source);
			aModel.setAffairsAuthors(authors);//创建作者
		}
		return data;
	}

	public List<AuthorsModel> getAuthorsList(int harType, int harId, int source) {
		List<AuthorsModel> models = harvests.getAuthorsListService(harType, harId, source);
		return models;
	}
}
