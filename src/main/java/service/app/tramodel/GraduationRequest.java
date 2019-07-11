/**
 * By Upxuan
 * 
 * Created in 2019/04/25
 */
package service.app.tramodel;

import org.springframework.web.multipart.MultipartFile;

public class GraduationRequest implements Cloneable {

	private String username;
	private MultipartFile file;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "GraduationRequest [username=" + username + ", file=" + file + "]";
	}
}
