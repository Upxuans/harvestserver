/**
 * By Upxuan
 * 
 * Created in 2018/12/21
 */
package service.app.domain;

public class SachModel {

	private int sachUserId;
	private int sachHarType;
	private int sachHarId;
	private int sachFirstAuthor;
	private int sachBelong;
	private int sachLoad;
	private int sachSource;
	private int used = 0;
	
	public int getSachUserId() {
		return sachUserId;
	}
	
	public void setSachUserId(int sachUserId) {
		this.sachUserId = sachUserId;
	}
	
	public int getSachHarType() {
		return sachHarType;
	}
	
	public void setSachHarType(int sachHarType) {
		this.sachHarType = sachHarType;
	}
	
	public int getSachHarId() {
		return sachHarId;
	}
	
	public void setSachHarId(int sachHarId) {
		this.sachHarId = sachHarId;
	}
	
	public int getSachFirstAuthor() {
		return sachFirstAuthor;
	}
	
	public void setSachFirstAuthor(int sachFirstAuthor) {
		this.sachFirstAuthor = sachFirstAuthor;
	}
	
	public int getSachBelong() {
		return sachBelong;
	}
	
	public void setSachBelong(int sachBelong) {
		this.sachBelong = sachBelong;
	}
	
	public int getSachLoad() {
		return sachLoad;
	}
	
	public void setSachLoad(int sachLoad) {
		this.sachLoad = sachLoad;
	}
	
	public int getSachSource() {
		return sachSource;
	}
	
	public void setSachSource(int sachSource) {
		this.sachSource = sachSource;
	}
	
	public int getUsed() {
		return used;
	}
	
	public void setUsed(int used) {
		this.used = used;
	}
}
