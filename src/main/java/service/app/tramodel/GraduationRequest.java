/**
 * By Upxuan
 * 
 * Created in 2019/04/25
 */
package service.app.tramodel;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class GraduationRequest extends GraduationMsgRequest implements Cloneable {

	private MultipartFile[] files;

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "GraduationRequest [files=" + Arrays.toString(files) + ", getFiles()=" + Arrays.toString(getFiles())
				+ ", getUsername()=" + getUsername() + ", getFilename()=" + getFilename() + ", getDevices()="
				+ getDevices() + ", getDeviceDescribe()=" + getDeviceDescribe() + ", getKeyss()=" + getKeyss()
				+ ", getWorks()=" + getWorks() + ", getWorkDescribe()=" + getWorkDescribe() + ", toString()="
				+ super.toString() + ", getUserType()=" + getUserType() + ", getUserId()=" + getUserId()
				+ ", getHarType()=" + getHarType() + ", getHarId()=" + getHarId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
