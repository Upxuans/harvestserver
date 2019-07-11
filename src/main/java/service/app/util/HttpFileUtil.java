package service.app.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import service.app.domain.FileModel;

@Service
public class HttpFileUtil {

	private final static Logger logger = LoggerFactory.getLogger(HttpFileUtil.class);
	
	// 到配置文件中添加路径
	@Value("${files.path}")
	private String localBasePath; // 用户上传和下载保存的基路径
	
	
	/**
	 * 根据用户id，获取用户文件列表数据
	 * @param username 用户名
	 * @return 若用户名文件夹不存在则设置文件列表为null，
	 *  若文件夹存在但无文件，则返回空列表
	 */
	public FileModel getFileList(String username) {
		Assert.notNull(username, "username is null");
		if(logger.isDebugEnabled())
			logger.debug("user:" + username + " getFileList");
		FileModel res = new FileModel();
		boolean flag = true;
		Path p = checkOrGetUserPath(username);
		//用户路径不存在，创建目录
		try {
		    p = Files.createDirectory(Paths.get(localBasePath + File.separator + username));
		} catch(FileAlreadyExistsException e){
		    // the directory already exists.
		} catch (IOException e) {
		    //something else went wrong
			flag = false;
		    e.printStackTrace();
		}
		if(flag){
			File cur = p.toFile();
			// 用户路径存在
			res.setFileList(Arrays.asList(cur.list()));
			long size = getFilesSize(cur.listFiles());
			// 注意，这里可能有问题，
			res.setFolderSize((int)size);
		}else res = null;
		return res;
	}
	
	
	/**
	 * 保存用户文件
	 * @param filename 文件名
	 * @param is 输入流，注意该方法使用流后会关闭流
	 * @param username 用户名
	 * @return 1:success 0:file repeat -1:save error
	 */
	public int uploadFile(String filename, InputStream is, String username) {
		int flag = -1;
		try {
			Assert.notNull(filename, "filename is null");
			Assert.notNull(username, "username is null");
			Assert.notNull(is, "FileInputStream is null");
			
			Path p = checkOrCreateUserPath(username);
			Path fp = p.resolve(filename);//获得上传文件所在路径
			//检测文件是否存在
			if(Files.exists(fp)&& !Files.isDirectory(fp)) {
				return flag=0;
			}
			
			//保存文件
			try(InputStream ins = is){
				Files.createFile(fp);
				Files.copy(ins, fp, StandardCopyOption.REPLACE_EXISTING);
			}
			flag=1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("file save error!",e);
		}
		return flag;
		
	}
	
	/**
	 * 删除用户所有文件
	 * @param username
	 * @return true:delete success false:delete fall;
	 */
	public boolean deleteAllFiles(String username) {
		boolean flag=false;
		
		try {
			Assert.notNull(username, "username is null");
			Path p = checkOrGetUserPath(username);
			if(p!=null&&Files.isDirectory(p)) {
				Arrays.stream(p.toFile().listFiles()).
					forEach(File::delete);// 注意，这里只能删除文件，否则会出现问题,根据实际情况做修改
			}
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 删除制定所有文件
	 * @param username
	 * @return true:delete success false:delete fall;
	 */
	public boolean deleteFile(String username,String filename) {
		boolean flag=false;
		
		try {
			Assert.notNull(username, "username is null");
			Assert.notNull(filename, "filename is null");
			Path p = checkOrGetUserPath(username);
			if(p!=null&&Files.isDirectory(p)) {
				Path fp = p.resolve(filename);
				if(Files.exists(fp)&&!Files.isDirectory(fp))
					Files.delete(fp);
			}
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 根据用户名和文件获得文件字节数组
	 * @param username
	 * @param filename
	 * @return 文件不存在或者读取数据错误都会返回null
	 */
	public byte[] downloadFile(String username, String filename) {
		Assert.notNull(username, "username is null");
		Assert.notNull(filename, "filename is null");
		byte[] data = null;
		try {
			Assert.notNull(filename, "filename is null");
			Assert.notNull(username, "username is null");

			Path p = checkOrCreateUserPath(username);
			Path fp = p.resolve(filename);//获得上传文件所在路径
			//检测文件是否存在
			if(!Files.exists(fp) || Files.isDirectory(fp)) {
				return data;
			}
			
			//保存文件
			try(InputStream ins = Files.newInputStream(fp, StandardOpenOption.READ)){
				data = StreamUtils.copyToByteArray(ins);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("file read error!",e);
		}
		return data;
	}
	
	/**
	 * 检测用户文件路径是否存在，如果不存在则创建
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	private Path checkOrCreateUserPath(String username) throws IOException {
		Path p = Paths.get(localBasePath + File.separator + username);
		if(!Files.exists(p)) Files.createDirectories(p);
		return p;
	}
	
	/**
	 * 检测并返回用户路径，若不存在路径则返回null
	 * @param username
	 * @return
	 * @throws IOException
	 */
	private Path checkOrGetUserPath(String username)  {
		Path p = Paths.get(localBasePath + File.separator + username);
		if(!Files.exists(p)) return null;;
		return p;
	}
	
	
	/**
	 * 返回所有文件的内存数量总和，单位字节
	 * @param all
	 * @return
	 */
	private static long getFilesSize(File[] all) {
		return Arrays.stream(all).
			filter(f->!f.isDirectory()).
			mapToLong(File::length).sum();
	}

}
