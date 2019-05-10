/**
 * By Upxuan
 * 
 * Created in 2018/12/5
 */
package service.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import service.app.service.ReviseInfoService;
import service.app.tramodel.ReviseInfoRequest;
import service.app.tramodel.response.BaseResponse;
import service.app.tramodel.response.ErrCode;
import service.app.tramodel.response.ResiveInfoResponse;
import service.app.util.ConvertUtil;

@RestController
public class ReviseInfoController {
	
//	private static final String bufferpath = "E:/Codes/vue/harvestweb/static/images/avatar";//本地的测试地址
	private static final String bufferpath = "/var/www/html/lab/download/sculptures";//实际地址
	
	@Autowired
	ReviseInfoService reviseInfoService;

	@RequestMapping("/uploadSculpture")
	public ResiveInfoResponse uploadSculptureController(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		System.out.println("uploadSculpture：");
		ResiveInfoResponse resp = new ResiveInfoResponse();
		boolean flag = true;
//		String bufferpath = "E:/Codes/vue/harvestweb/static/images/avatar";
//		String path = "/var/www/html/lab/download/sculptures";
		try {
			String filename = file.getOriginalFilename();
			InputStream ins = file.getInputStream(); 
			File newfile = new File(bufferpath + File.separator + filename);
			FileUtils.copyInputStreamToFile(ins, newfile);
			
			resp.setImgName(filename);
		} catch (Exception e) {
			flag = false;
		}
		if(flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp;
	}
	
	@RequestMapping("/reviseBaseInfo")
	public BaseResponse reviseBaseInfoController(@RequestBody ReviseInfoRequest data) throws IOException {
		System.out.println(data.toString());
		BaseResponse resp = new BaseResponse();
		Boolean flag = true;
		try {
			if(data.getFilename() == null) {//没有更新头像
				if(data.getUserType() == 1) {
					reviseInfoService.updateInfoInTeacherOneService(data.getUserId(), data.getLink(), data.getTel(), data.getEmail(), data.getDirection());
				} else if (data.getUserType() == 2) {
					reviseInfoService.updateInfoInStudentOneService(data.getUserId(), data.getTel(), data.getEmail(), data.getDirection());
				}
			}else {
				String pastimgname = reviseInfoService.getImgUrlService(data.getUserType(), data.getUserId());
				if(pastimgname != null && pastimgname.length() > 0) {//原来有头像，需要把原来的删掉，然后更新数据库
					FileUtils.forceDelete(new File(bufferpath + File.separator + pastimgname));
				}
				if(data.getUserType() == 1) {
					reviseInfoService.updateInfoInTeacherTwoService(data.getUserId(), data.getFilename(), data.getLink(), data.getTel(), data.getEmail(), data.getDirection());
				} else if (data.getUserType() == 2) {
					reviseInfoService.updateInfoInStudentTwoService(data.getUserId(), data.getFilename(), data.getTel(), data.getEmail(), data.getDirection());
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		if(flag) resp.setErrCode(ErrCode.SETTING_OK);
		else resp.setErrCode(ErrCode.SETTING_ERR);
		return resp; 
	}
	
//	public ResiveInfoResponse reviseBaseInfo(HttpServletResponse response, ReviseInfoRequest data) {
//
//		System.out.println("userType:" + data.getUserType());
//		System.out.println("fileName:" + data.getFileName());
//
//		ResiveInfoResponse resp = new ResiveInfoResponse();
//		boolean flag = true;
//		
//		String path = "E:/Codes/vue/harvestweb/static/images/avatar";
////		String path = "/var/www/html/lab/download/sculptures";
//		String imageUrl = data.getImageUrl();
//		String imagePrefix = "";
//		String image = "";
//		String suffix = "";
//		String[] str = imageUrl.split("base64,");
////		System.out.println(str.length + "," + str[0]);
//		
//		imagePrefix = str[0];
//		image = str[1];
//		//’过来的图片首先应该获取数据库内的图片姓名imgurl，然后得到图片的url后用FileInputStream读取图片信息，和拿过来的图片image('base64,'后的数据)进行字节比较，从而配对图片是否是同一个，避免前端重选同一图片后提交更新而产生的图片生成冗余。
//		//’相同就不更新imgurl，不相同则先删除原有图片，再更新imgurl
//		int thesameflag = 0;//0：原先无上传的图片   1：原先图片和此次上传图片是同一张   2：原先图片和此次上传图片不是同一张
//		String imgName = reviseInfoService.getImgUrlService(data.getUserType(), data.getUserId());
//		String imgUrl = path + File.separator + imgName;
//		if(imgName != null && imgName.length() > 0) {
//			String imageBase64 = ConvertUtil.getImageBase64(imgUrl);
//			if(imageBase64.equals(image)) {
////				System.out.println("the same");
//				thesameflag = 1;
//			}else {
////				System.out.println("the different");
//				thesameflag = 2;
//			}
//		}
//		
//		if(imageUrl == null || imageUrl.length() == 0 || str == null || str.length != 2 || thesameflag == 1) {//’图片没有更新的时候不更新图片 不带newImageName
//			try {
//				if(data.getUserType() == 1) {
//					reviseInfoService.updateInfoInTeacherOneService(data.getUserId(), data.getLink(), data.getTel(), data.getEmail(), data.getDirection());
//				} else if (data.getUserType() == 2) {
//					reviseInfoService.updateInfoInStudentOneService(data.getUserId(), data.getTel(), data.getEmail(), data.getDirection());
//				}
//			} catch (Exception e) {
//				flag = false;
//			}
//		}else { //’图片更新
//			System.out.println("update:");
//		    try {
//				if("data:image/jpeg;".equalsIgnoreCase(imagePrefix)){  //data:image/jpeg;base64,base64编码的jpeg图片数据
//					suffix = ".jpg";
//				}else if("data:image/png;".equalsIgnoreCase(imagePrefix)){   //data:image/png;base64,base64编码的png图片数据
//	                suffix = ".png";
//				}else {
//					flag = false;
//				}
//				if(flag) {
//					String newImageName = System.currentTimeMillis() + suffix;
//					flag = ConvertUtil.generateImage(path, image, newImageName);//生成图片
//					if(thesameflag == 2) { //若有原有提交的照片则删除图片
//						File file = new File(imgUrl);
//						file.delete();
//					}
//					System.out.println(flag);
//					if(flag) {
//						resp.setImgName(newImageName);
//						if(data.getUserType() == 1) {
//							reviseInfoService.updateInfoInTeacherTwoService(data.getUserId(), newImageName, data.getLink(), data.getTel(), data.getEmail(), data.getDirection());
//						} else if (data.getUserType() == 2) {
//							reviseInfoService.updateInfoInStudentTwoService(data.getUserId(), newImageName, data.getTel(), data.getEmail(), data.getDirection());
//						}
//					}
//				}
//		    }catch (Exception e) {
//				System.out.println(e);
//				flag = false;
//			}
//		}
//		if(flag) resp.setErrCode(ErrCode.SETTING_OK);
//		else resp.setErrCode(ErrCode.SETTING_ERR);
//		return resp;
//	}
	
	@RequestMapping("/revisePwd")
	@ResponseBody
	public BaseResponse revisePwd(HttpServletResponse response, ReviseInfoRequest data) {

		BaseResponse resp = new BaseResponse();
		int flag = 2;
		String password = reviseInfoService.findPwdByIdService(data.getUserType(), data.getUserId());
		if(password.equals(data.getPass())) {
			flag = reviseInfoService.updatePwdService(data.getUserType(), data.getUserId(), data.getCheckPass());
		}else {
			flag = 3;
		}
		
		if(flag == 1) 
			resp.setErrCode(ErrCode.SETTING_OK);
		else if(flag == 2)
			resp.setErrCode(ErrCode.SETTING_ERR);
		else if(flag == 3)
			resp.setErrCode(ErrCode.SETTING_PASS_ERR);
		return resp;
	}
}
