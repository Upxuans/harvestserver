/**
 * By Upxuan
 * 
 * Created in 2019/04
 */
package service.app.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import service.app.domain.FileModel;
import service.app.service.GraduationService;
import service.app.tramodel.GraduationMsgRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;
import service.app.tramodel.response.GraduationResponse;
import service.app.util.HttpFileUtil;

@RestController
public class GraduationController {

	@Autowired
	GraduationService graduationService;
	
	@Autowired
	HttpFileUtil httpFileUtil;
	
	/**
	 * *获得student的相关信息
	 * @param BaseRequest
	 * @return LogInUserResponse
	 */
	@RequestMapping("/getGraduationMsg")
	public GraduationResponse getGraduationMsgController(@RequestBody GraduationMsgRequest data) {
		
		GraduationResponse resp = new GraduationResponse();
		Boolean flag = true;
		try {
			resp = graduationService.getGraduationMsgService(data.getUserId());
//			FtpFileModel model = FtpFileUtil.getFileList(data.getUsername());  // FTP服务器
			FileModel model = httpFileUtil.getFileList(data.getUsername());
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
	 * graduation信息更新（处理文件上传）
	 * @param GraduationRequest
	 * @return flag=1成功；flag=0文件重复；flag=-1文件上传失败；
	 * @throws IOException
	 */
//    @RequestMapping(value = "/uploadGraduation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public @ResponseBody BaseResponse uploadGraduationController(@RequestParam("files") MultipartFile[] files, @RequestParam("username") String username, HttpServletRequest request) throws IOException {
//    	
//    	BaseResponse resp = new BaseResponse();
//    	int flag = 1;
//    	for(int i=0; i<files.length; i++) {
//    		String fileName = files[i].getOriginalFilename();
//    		InputStream inputStream = files[i].getInputStream();
////    		flag = FtpFileUtil.uploadFile(fileName, inputStream, username);  // FTP服务器
//    		flag = httpFileUtil.uploadFile(fileName, inputStream, username);  
//    		if(flag != 1) break;
//    	}
//    	
//		if(flag == 1) resp.setErrCode(ErrCode.SETTING_OK);
//		else if(flag == 0) resp.setErrCode(ErrCode.SETTING_FILE_REPEAT_ERR);
//		else if(flag == -1) resp.setErrCode(ErrCode.SETTING_FILE_UPLOAD_ERR);
//    	return  resp;
//    	
//    }
    
    /** 单独上传文件，上传n个文件要访问n次次方法，会触发前端el-upload的on-success/on-error事件 */
//    直接上传文件不带任何其他data的配置：  @RequestParam("file") MultipartFile file
    @RequestMapping(value = "/uploadGraduation")
    public @ResponseBody BaseResponse uploadGraduationController1(@RequestParam("file") MultipartFile file, @RequestParam("username") String username, HttpServletRequest request) throws IOException {
    	
    	BaseResponse resp = new BaseResponse();
    	int flag = 1;

		String fileName = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();
		flag = httpFileUtil.uploadFile(fileName, inputStream, username);  
    	
		if(flag == 1) resp.setErrCode(ErrCode.SETTING_OK);
		else if(flag == 0) resp.setErrCode(ErrCode.SETTING_FILE_REPEAT_ERR);
		else if(flag == -1) resp.setErrCode(ErrCode.SETTING_FILE_UPLOAD_ERR);
    	return  resp;
//		int flag = 1;
//		Boolean isUpload = true;
//		InputStream inputStream = file.getInputStream();
//		String fileName = file.getOriginalFilename();//获取文件名 若存在个人文件夹则不考虑重名现象。
////        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀名
////        String newfileName = UUID.randomUUID() + suffixName; //重新生成文件名
////        System.out.println(fileName);
//		isUpload = HttpFileUtil.uploadFile(fileName, inputStream, data.getUsername());
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
//		return resp;
    }
    
    /**
	 * graduation信息更新（没有文件上传的时候）
	 * @param GraduationMsgRequest
	 * @return BaseResponse
	 */
    @RequestMapping("/uploadGraduationMsg")
    public @ResponseBody BaseResponse uploadGraduationMsgController(@RequestBody GraduationMsgRequest data) {

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
//    	Boolean flag = FtpFileUtil.deleteAllFiles(data.getUsername());  // FTP服务器
    	Boolean flag = httpFileUtil.deleteAllFiles(data.getUsername());
    	
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
//    	Boolean flag = FtpFileUtil.deleteFile(data.getUsername(), data.getFilename());  // FTP服务器
    	Boolean flag = httpFileUtil.deleteFile(data.getUsername(), data.getFilename());
    	
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
    	
    	//从ftp中获得的文件流写到file文件中去
//    	InputStream inputStream = FtpFileUtil.getFtpFile(data.getUsername(), data.getFilename());  // FTP服务器
//    	byte[] res = StreamUtils.copyToByteArray(inputStream);
    	
    	byte[] bt = httpFileUtil.downloadFile(data.getUsername(), data.getFilename());
    	
    	HttpHeaders headers = new HttpHeaders();// 设置一个head
    	String downloadFielName = new String(data.getFilename().getBytes("UTF-8"),"iso-8859-1");
    	headers.setContentDispositionFormData("attachment", downloadFielName);// 文件的属性：文件名
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
    	return new ResponseEntity<byte[]>(bt, headers, HttpStatus.CREATED);
    }
}
