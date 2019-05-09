/**
 * By Upxuan
 * 
 * Created in 2019/04
 */
package service.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import service.app.domain.FtpFileModel;
import service.app.service.GraduationService;
import service.app.tramodel.GraduationMsgRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;
import service.app.tramodel.response.GraduationResponse;
import service.app.util.FtpFileUtil;

@RestController
public class GraduationController {

	@Autowired
	GraduationService graduationService;
	
	/**
	 * *获得student的相关信息
	 * @param BaseRequest
	 * @return LogInUserResponse
	 */
	@RequestMapping("/getGraduationMsg")
	public GraduationResponse getGraduationMsgController(@RequestBody GraduationMsgRequest data) {
		
//		System.out.println(data.toString());
		GraduationResponse resp = new GraduationResponse();
		Boolean flag = true;
		try {
			resp = graduationService.getGraduationMsgService(data.getUserId());
			FtpFileModel model = FtpFileUtil.getFileList(data.getUsername());
			resp.setFileList(model.getFileList());
			resp.setFolderSize(model.getFolderSize());
		} catch (Exception e) {
			flag = false;
		}
		
		if(flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	/**
	 * graduation信息更新（ftp处理文件上传）
	 * @param GraduationRequest
	 * @return flag=1成功；flag=0文件重复；flag=-1文件上传失败；
	 * @throws IOException
	 */
	
    @RequestMapping(value = "/uploadGraduation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody BaseResponse uploadGraduationController(@RequestParam("files") MultipartFile[] files, @RequestParam("username") String username, HttpServletRequest request) throws IOException {
//    	System.out.println(files.length);
    	BaseResponse resp = new BaseResponse();
    	int flag = 1;
    	for(int i=0; i<files.length; i++) {
    		String fileName = files[i].getOriginalFilename();
    		InputStream inputStream = files[i].getInputStream();
    		flag = FtpFileUtil.uploadFile(fileName, inputStream, username);
    		if(flag != 1) break;
    	}
    	
		if(flag == 1) resp.setErrCode(ErrCode.SETTING_OK);
		else if(flag == 0) resp.setErrCode(ErrCode.SETTING_FILE_REPEAT_ERR);
		else if(flag == -1) resp.setErrCode(ErrCode.SETTING_FILE_UPLOAD_ERR);
    	return  resp;
    	
    }
    
    /** 单独上传文件，上传n个文件要访问n次次方法，会触发前端el-upload的on-success/on-error事件 */
////    直接上传文件不带任何其他data的配置：  @RequestParam("file") MultipartFile file
//    public @ResponseBody BaseResponse uploadGraduationController(GraduationRequest data, HttpServletRequest request) throws IOException {
////    	System.out.println("uploadGraduation:");
////    	System.out.println(data.toString());
//
//    	BaseResponse resp = new BaseResponse();
//    	MultipartFile file = data.getFile();
//		int flag = 1;
//		Boolean isUpload = true;
//		InputStream inputStream = file.getInputStream();
//		String fileName = file.getOriginalFilename();//获取文件名 若存在个人文件夹则不考虑重名现象。
////        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀名
////        String newfileName = UUID.randomUUID() + suffixName; //重新生成文件名
////        System.out.println(fileName);
//		isUpload = FtpFileUtil.uploadFile(fileName, inputStream, data.getUsername());
//		if(isUpload){
//			try {
////				System.out.println("ftp上传成功 /无上传文件");
//				graduationService.updateStudentGraduationService(data.getUserId(), data.getDevices(), data.getDeviceDescribe(), data.getKeyss(), data.getWorks(), data.getWorkDescribe());
//			} catch (Exception e) {
//				flag = -2;
//			}
//		}else {
//			flag = -1;
//		}
//		if(flag == 1) resp.setErrCode(ErrCode.SETTING_OK);
//		else if(flag == 0) resp.setErrCode(ErrCode.SETTING_FILE_REPEAT_ERR);
//		else if(flag == -1) resp.setErrCode(ErrCode.SETTING_FILE_UPLOAD_ERR);
//		else if(flag == -2) resp.setErrCode(ErrCode.SETTING_ERR);
//    	return  resp;
//    }
    
    /**
	 * graduation信息更新（没有文件上传的时候）
	 * @param GraduationMsgRequest
	 * @return BaseResponse
	 */
    @RequestMapping("/uploadGraduationMsg")
    public @ResponseBody BaseResponse uploadGraduationMsgController(@RequestBody GraduationMsgRequest data) {
    	System.out.println("uploadGraduation:");
    	System.out.println(data.toString());

    	BaseResponse resp = new BaseResponse();
    	Boolean flag = true;
    	try {
    		graduationService.updateStudentGraduationService(data.getUserId(), data.getDevices(), data.getDeviceDescribe(), data.getKeyss(), data.getWorks(), data.getWorkDescribe());
		} catch (Exception e) {
			flag = false;
		}
		if(flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
    	return  resp;
    }
    
    /**
	 * 清空个人文件夹-删除所有文件
	 * @param GraduationMsgRequest
	 * @return BaseResponse
	 */
    @RequestMapping("/deleteAllFile")
    public BaseResponse deleteAllFileController(HttpServletResponse response, GraduationMsgRequest data) {
    	
    	BaseResponse resp = new BaseResponse();
    	Boolean flag = FtpFileUtil.deleteAllFiles(data.getUsername());
    	
    	if(flag) resp.setErrCode(ErrCode.SETTING_OK);
    	else resp.setErrCode(ErrCode.SETTING_ERR);
    	return resp;
    }
    
    /**
     * 删除指定文件
     * @param GraduationMsgRequest
     * @return BaseResponse
     */
    @RequestMapping("/deleteFile")
    public BaseResponse deleteFileController(HttpServletResponse response, GraduationMsgRequest data) {
    	
    	BaseResponse resp = new BaseResponse();
    	Boolean flag = FtpFileUtil.deleteFile(data.getUsername(), data.getFilename());
    	
    	if(flag) resp.setErrCode(ErrCode.SETTING_OK);
    	else resp.setErrCode(ErrCode.SETTING_ERR);
    	return resp;
    }
    
    /**
     * 下载指定文件
     * @param GraduationMsgRequest
     * @return  ResponseEntity<byte[]> 文件流
     */
    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFileController(@RequestBody GraduationMsgRequest data) throws IOException {
    	System.out.println(data.toString());
//    	File file = new File("/var/www/html/lab/download/suffer" + File.separator + data.getFilename());// 你放的文件路径 
    	File file = new File("F:" + File.separator + data.getFilename());// 你放的文件路径 
    	
    	//从ftp中获得的文件流写到file文件中去
    	InputStream inputStream = FtpFileUtil.getFtpFile(data.getUsername(), data.getFilename());
    	OutputStream outputStream = new FileOutputStream(file);
    	byte[] arr = new byte[1000]; //该数组用来存入从输入文件中读取到的数据
    	int len; //变量len用来存储每次读取数据后的返回值
    	while( ( len=inputStream.read(arr) ) != -1 ) {
    		outputStream.write(arr, 0, len);
    	}//while循环：每次从输入文件读取数据后，都写入到输出文件中
    	inputStream.close();
    	outputStream.close();
		
    	HttpHeaders headers = new HttpHeaders();// 设置一个head
    	String downloadFielName = new String(data.getFilename().getBytes("UTF-8"),"iso-8859-1");
    	headers.setContentDispositionFormData("attachment", downloadFielName);// 文件的属性，也就是文件叫什么吧
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
    	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);//开始下载
    }
    
//    @RequestMapping("/downloadFile")
//    public BaseResponse downloadFileController(HttpServletResponse response, GraduationMsgRequest data) {
//    	
//    	BaseResponse resp = new BaseResponse();
//    	//前端获取浏览器默认下载路径（localpath），此处为windows下的一个测试用例。
//    	String localpath = "C:\\Users\\Administrator\\Desktop";
//    	Boolean flag = FtpFileUtil.downloadFile(data.getUsername(), data.getFilename(), localpath);
//    	
//    	if(flag) resp.setErrCode(ErrCode.SETTING_OK);
//    	else resp.setErrCode(ErrCode.SETTING_ERR);
//    	return resp;
//    }
    
    
}
