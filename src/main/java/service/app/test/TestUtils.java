package service.app.test;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import service.app.domain.StudentModel;

@Component
public class TestUtils {

	@Mapper
	testDao dao;
	
	public void test() {
		StudentModel test = null;
		test = dao.getTestMsg("182050145");
		System.out.println(test.getName());
	}
}
