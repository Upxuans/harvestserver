/**
 * By Upxuan
 * 
 * Created in 2019/04/25
 */
package service.app.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.util.Assert;

import service.app.domain.FtpFileModel;


public class FtpFileUtil {
	
	//ftp服务器ip地址
	private static final String FTP_HOST = "10.1.18.137";
	//端口号
	private static final int FTP_PORT = 21;
	//用户名
	private static final String FTP_USERNAME = "cloud";
	//密码
	private static final String FTP_PASSWORD = "ftpserver323";
	//文件路径
	private static final String FTP_FILEPATH = "/download/graduation/files";

//	@Value("${ftp.host}")
//	private static String FTP_HOST;
	
//	@Value("${ftp.post}")
//	private static int FTP_PORT;
	
//	@Value("$ftp.username")
//	private static String FTP_USERNAME;
	
//	@Value("${ftp.password}")
//	private static String FTP_PASSWORD;
	
//	@Value("${ftp.filepath}")
//	private static String FTP_FILEPATH;
	
	

	/**
	 **上传文件
	 * @param fileName
	 * @param input
	 * @param username
	 * @return 是否成功
	 */
    public static int uploadFile(String fileName, InputStream input, String username){
    	
        //根据username的个人文件夹
        String perFilePath = FTP_FILEPATH + "/" + username;
        
        int flag = -1;
        FTPClient ftp = new FTPClient();
        try {
        	//连接并登陆数据库 连接成功就获得FTPClient
            ftp = ConnectFTP();
            if(ftp == null) {
            	return flag;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(perFilePath);
            ftp.changeWorkingDirectory(perFilePath);
            if(isExistFile(ftp, fileName)) {//文件重复
            	flag = 0;
            	return flag;
            }
            ftp.storeFile(fileName, input);
            input.close();
            ftp.logout();//登出
            flag = 1;
        } catch (IOException e) {
        	flag = -1;
            e.printStackTrace();
        } finally {
        	//最后需要断开ftp连接
        	closeFTPClient(ftp);
        }
        return flag;
    }
    
    /**
	 **读取文件列表
	 * @param username 从个人文件夹寻找
	 * @return List<String> 文件列表
	 */
    public static FtpFileModel getFileList(String username){
    	FtpFileModel model = new FtpFileModel(); 
    	List<String> fileList = new ArrayList<String>();
    	
        String perFilePath = FTP_FILEPATH + "/" + username;
        FTPClient ftp = new FTPClient();
        try {
        	//连接并登陆数据库 连接成功就获得FTPClient
        	ftp = ConnectFTP();
            if(ftp == null) {
            	return null;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(perFilePath);
            ftp.changeWorkingDirectory(perFilePath);
            //列出fileName下面所有文件方法1
            String[] list = ftp.listNames();
            fileList = Arrays.asList(list);
            //列出fileName下面所有文件方法2
//            FTPFile[] ff = ftp.listFiles();
//            for(int i=0; i<ff.length; i++) {
//            	fileList.add(ff[i].getName());
//            }
            model.setFileList(fileList);;
            model.setFolderSize(getFolderSize(ftp, perFilePath));
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	closeFTPClient(ftp);
        }
        return model;
    }
    
    /**
	 **删除所有文件
	 * @param username 从个人文件夹寻找
	 * @return Boolean
	 */
 	public static boolean deleteAllFiles(String username){
 		Boolean flag = false;
 		String perFilePath = FTP_FILEPATH + "/" + username;
 		FTPClient ftp = new FTPClient();
 		try {
 			ftp = ConnectFTP();
            if(ftp == null) {
            	return flag;
            }
            ftp.changeWorkingDirectory(perFilePath);
            //删除文件
            FTPFile[] ff = ftp.listFiles();
            for(int i=0; i<ff.length; i++) {
            	ftp.deleteFile(ff[i].getName());
            }
            ftp.logout();
            flag = true;
 		} catch (IOException e) {
 			e.printStackTrace();
 			flag = false;
 		} finally {
        	closeFTPClient(ftp);
        }
 		return flag;
 	}
 	
 	/**
	 **删除指定文件
	 * @param username 个人文件夹名
	 * @param filename 要删除的文件名
	 * @return Boolean
	 */
 	public static boolean deleteFile(String username, String filename){
 		Boolean flag = false;
 		String perFilePath = FTP_FILEPATH + "/" + username;
 		FTPClient ftp = new FTPClient();
 		try {
 			ftp = ConnectFTP();
            if(ftp == null) {
            	return flag;
            }
            ftp.changeWorkingDirectory(perFilePath);
            ftp.deleteFile(filename);//删除指定文件
            ftp.logout();
            flag = true;
 		} catch (IOException e) {
 			e.printStackTrace();
 			flag = false;
 		} finally {
        	closeFTPClient(ftp);
        }
 		return flag;
 	}
 	
 	/**
 	 **下载指定文件
 	 * @param username 个人文件夹名
 	 * @param filename 要下载的文件名
 	 * @return Boolean
 	 */
 	public static boolean downloadFile(String username, String filename, String localpath){
 		Boolean flag = false;
 		OutputStream out = null;
 		String perFilePath = FTP_FILEPATH + "/" + username;
 		FTPClient ftp = new FTPClient();
 		try {
 			ftp = ConnectFTP();
 			if(ftp == null) {
 				return flag;
 			}
 			ftp.changeWorkingDirectory(perFilePath);
 			//下载指定文件
 			ftp.enterLocalPassiveMode();
			ftp.setRemoteVerificationEnabled(false);
 
			FTPFile[] ftpFiles = ftp.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localpath + "/" + file.getName());
					out = new FileOutputStream(localFile);
					ftp.retrieveFile(file.getName(), out);
					out.close();
					break;
				}
			}
 			ftp.logout();
 			flag = true;
 		} catch (IOException e) {
 			e.printStackTrace();
 			flag = false;
 		} finally {
 			closeFTPClient(ftp);
 		}
 		return flag;
 	}
 	
 	/**
 	 **获取FTP指定文件的文件流
 	 * @param username 个人文件夹名
 	 * @param filename 要下载的文件名
 	 * @return InputStream
 	 */
 	public static InputStream getFtpFile(String username, String filename){
 		InputStream inputStream = null;
 		String perFilePath = FTP_FILEPATH + "/" + username;
 		FTPClient ftp = new FTPClient();
 		try {
 			ftp = ConnectFTP();
 			if(ftp == null) {
 				return null;
 			}
 			ftp.changeWorkingDirectory(perFilePath);
 			//下载指定文件
 			ftp.enterLocalPassiveMode();
			ftp.setRemoteVerificationEnabled(false);
			FTPFile[] ftpFiles = ftp.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					inputStream = ftp.retrieveFileStream(file.getName());
					break;
				}
			}
 			ftp.logout();
 		} catch (IOException e) {
 			e.printStackTrace();
 		} finally {
 			closeFTPClient(ftp);
 		}
 		return inputStream;
 	}

    
    //打开ftp连接
    public static FTPClient ConnectFTP(){
		FTPClient ftp = new FTPClient();
		int reply;
		try {
			ftp.setControlEncoding("UTF-8");
			ftp.connect(FTP_HOST, FTP_PORT);// 连接FTP服务器
			ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return null;
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ftp;
	}
    
    //判断filePath路径下是否有文件名为fileName的文件
    public static Boolean isExistFile(FTPClient ftp, String fileName) {
    	Boolean flag = false;
    	try {
			String[] list = ftp.listNames();
			for (int i = 0; i < list.length; i++) {
				if(fileName.equals(list[i])){
					flag = true;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return flag;
    }
    
  //判断filePath路径下所有文件所占空间大小
    public static int getFolderSize(FTPClient ftp, String filePath) {
    	int size = 0;
    	try {
			FTPFile[] list = ftp.listFiles();
			for (int i = 0; i < list.length; i++) {
				size += list[i].getSize();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return size;
    }
 	
	//关闭ftp连接
	public static void closeFTPClient(FTPClient ftp) {
		if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException ioe) {
            	
            }
        }
	}
	
	public static boolean downloadFile2(String username, String filename, String localpath){
 		Boolean flag = false;
 		String perFilePath = FTP_FILEPATH + "/" + username;
 		FTPClient ftp =null;
 		try {
 			ftp = ConnectFTP();
 			if(ftp == null) {
 				return flag;
 			}
 			ftp.changeWorkingDirectory(perFilePath);
 			ftp.enterLocalPassiveMode();
			ftp.setRemoteVerificationEnabled(false);
		
 
			Path p = Paths.get(localpath + "/" + filename);
			try(InputStream is =ftp.retrieveFileStream(filename)){
				Assert.notNull(is, "no such file:"+filename);
			
				Files.deleteIfExists(p);
				Files.createFile(p);
				Files.copy(is, p, StandardCopyOption.REPLACE_EXISTING);
				ftp.completePendingCommand();
			}
			
 			ftp.logout();
 			flag = true;
 		} catch (Exception e) {
 			e.printStackTrace();
 			flag = false;
 		} finally {
 			closeFTPClient(ftp);
 		}
 		return flag;
 	}



}