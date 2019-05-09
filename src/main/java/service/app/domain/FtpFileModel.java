package service.app.domain;

import java.util.List;

public class FtpFileModel {

	private List<String> fileList;
	private int folderSize;
	
	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public int getFolderSize() {
		return folderSize;
	}
	
	public void setFolderSize(int folderSize) {
		this.folderSize = folderSize;
	}
}
