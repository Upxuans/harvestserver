/**
 * By Upxuan
 * 
 * Created in 2018/12/21
 */
package service.app.domain;

public class OthersModel {

	private int othersId;
	private String othersName;
	private int othersHarType;
	private int othersHarId;
	private int othersFirstAuthor;
	private int othersSource;
	private int used = 0;
	
	public int getOthersId() {
		return othersId;
	}
	
	public void setOthersId(int othersId) {
		this.othersId = othersId;
	}
	
	public String getOthersName() {
		return othersName;
	}
	
	public void setOthersName(String othersName) {
		this.othersName = othersName;
	}
	
	public int getOthersHarType() {
		return othersHarType;
	}
	
	public void setOthersHarType(int othersHarType) {
		this.othersHarType = othersHarType;
	}
	
	public int getOthersHarId() {
		return othersHarId;
	}
	
	public void setOthersHarId(int othersHarId) {
		this.othersHarId = othersHarId;
	}
	
	public int getOthersFirstAuthor() {
		return othersFirstAuthor;
	}
	
	public void setOthersFirstAuthor(int othersFirstAuthor) {
		this.othersFirstAuthor = othersFirstAuthor;
	}
	
	public int getOthersSource() {
		return othersSource;
	}
	
	public void setOthersSource(int othersSource) {
		this.othersSource = othersSource;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}
}
