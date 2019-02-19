/**
 * By Upxuan
 * 
 * Created in 2018/12/21
 */
package service.app.domain;

public class TachModel {

	private int tachUserId;
	private int tachHarType;
	private int tachHarId;
	private int tachFirstAuthor;
	private int tachLoad;
	private int tachSource;
	private int used = 0;
	
	public int getTachUserId() {
		return tachUserId;
	}
	
	public void setTachUserId(int tachUserId) {
		this.tachUserId = tachUserId;
	}
	
	public int getTachHarType() {
		return tachHarType;
	}
	
	public void setTachHarType(int tachHarType) {
		this.tachHarType = tachHarType;
	}
	
	public int getTachHarId() {
		return tachHarId;
	}
	
	public void setTachHarId(int tachHarId) {
		this.tachHarId = tachHarId;
	}
	
	public int getTachFirstAuthor() {
		return tachFirstAuthor;
	}
	
	public void setTachFirstAuthor(int tachFirstAuthor) {
		this.tachFirstAuthor = tachFirstAuthor;
	}
	
	public int getTachLoad() {
		return tachLoad;
	}
	
	public void setTachLoad(int tachLoad) {
		this.tachLoad = tachLoad;
	}
	
	public int getTachSource() {
		return tachSource;
	}
	
	public void setTachSource(int tachSource) {
		this.tachSource = tachSource;
	}
	
	public int getUsed() {
		return used;
	}
	
	public void setUsed(int used) {
		this.used = used;
	}
}
