/**
 * By Upxuan
 * 
 * Created in 2019/03/23
 */
package service.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.util.Base64;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class ConvertUtil {

	/**
	 * Base64字节码转图片
	 * @param image Base64码
	 * @param newImageName 文件存储名
	 * @return 返回true或者false
	 */
	//对字节数组字符串进行Base64解码并生成图片
	public static Boolean generateImage(String path, String image, String newImageName){
		try {
			byte[] bs = Base64Utils.decodeFromString(image);
			String imgUrl = path + File.separator + newImageName;
			System.out.println(imgUrl);
			FileOutputStream out = new FileOutputStream(new File(imgUrl));//这里就是文件上传的路径
			out.write(bs);
			out.flush();
			out.close();
//			System.out.println("上传的文件名为："  +  newImageName);
//			System.out.println("上传成功后的文件路径为："  +  imgUrl);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
    }
	
	/**
	 **图片码转Base64字节
	 * @param imgUrl 图片地址
	 * @return 返回图片的Base64编码
	 */
	//根据图片的地址获得Base64编码
	public static String getImageBase64(String imgUrl){
		String imageBase64 = "";
		try {
			InputStream in = new FileInputStream(imgUrl);
            byte[] bs = new byte[in.available()];
            in.read(bs);
            in.close();
	        imageBase64 = Base64Utils.encodeToString(bs);
		} catch (Exception e) {
			return "";
		}
		return imageBase64;
    }
	
}
