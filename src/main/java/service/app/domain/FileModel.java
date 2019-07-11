package service.app.domain;

import java.util.List;

public class FileModel {

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

	@Override
	public String toString() {
		return "FileModel [fileList=" + fileList + ", folderSize=" + folderSize + "]";
	}
	
}
