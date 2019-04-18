/**
 * By Upxuan
 * 
 * Created in 2019/4/2
 * */

package service.app.tramodel;

public class WebMaintainRequest extends BaseRequest implements Cloneable {

	private String project1;
	private String project2;
	private String paper;
	
	public String getProject1() {
		return project1;
	}
	
	public void setProject1(String project1) {
		this.project1 = project1;
	}
	
	public String getProject2() {
		return project2;
	}
	
	public void setProject2(String project2) {
		this.project2 = project2;
	}
	
	public String getPaper() {
		return paper;
	}
	
	public void setPaper(String paper) {
		this.paper = paper;
	}

	@Override
	public String toString() {
		return "WebMaintainRequest [project1=" + project1 + ", project2=" + project2 + ", paper=" + paper + "]";
	}
}
