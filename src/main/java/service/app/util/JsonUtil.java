package service.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class JsonUtil {
	
	public static void jsonHandle(String project1) {
		
//		String path = "E:\\Codes\\vue\\harvestweb\\static\\mock\\achievement.json";
//		String jsonMsg = JsonUtil.ReadFile(path);
//		JSONObject json = JSONObject.fromObject(jsonMsg);
//		JSONObject testDataObject = json.getJSONObject("testData");
//		String msgString = (String)testDataObject.get("msg");//读出信息
//		System.out.println(msgString);
//		
//		System.out.println(json.remove("testData").toString());//删除
//		System.out.println(json.toString());//删除后的数据
//		
//		JSONObject jsonObject = JSONObject.fromObject(jsonMsg);
//		//将testData的值更新
//		String testData = "test";
//		jsonObject.put("testData",testData);
//		//指定文件路径
//		File file = new File("E:\\Codes\\vue\\harvestweb\\static\\mock\\achievement.json");
//		if(!file.getParentFile().exists()){
//		    file.getParentFile().mkdirs();
//		}
//		//准备输出流
//		Writer out = new FileWriter(file);
//		//写文件
//		out.write(jsonObject.toString());
//		out.close();
	}
	
	public static String ReadFile(String path) {
		File file = new File(path);
	    BufferedReader reader = null;
	    String laststr = "";
	    try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
	    	while ((tempString = reader.readLine()) != null) { //一次读入一行，直到读入null为文件结束
//	    		System.out.println(tempString);
	    		laststr = laststr + tempString;
	    	}
	    	reader.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } finally {
	    	if (reader != null) {
		    	try {
		    		reader.close();
		     	} catch (IOException e1) {}
	    	}
	    }
	    return laststr;
	}
}
