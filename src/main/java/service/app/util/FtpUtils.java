package service.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtils {

	
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
	
	
    //打开ftp连接
    private static FTPClient ConnectFTP(){
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
	
    public final static void main(String[] args) {
    	FTPClient client = ConnectFTP();
    	String userPath = FTP_FILEPATH+"/182050146";
    	try {
			FTPFile[] files = client.listFiles(FTP_FILEPATH+"/182050146");
			for(FTPFile f:files) {
				String filePath = "F:/"+f.getName();
				Path p = Paths.get(filePath);
				System.err.println(p);
				
				try(InputStream is =client.retrieveFileStream(userPath+"/"+f.getName())){
//					Files.delete(p);
					Files.createFile(p);
					Files.copy(is, p, StandardCopyOption.REPLACE_EXISTING);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
	
}
