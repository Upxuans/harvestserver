package service.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "jpaper")
public class PaperInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jpaperid;
	private String jpapertitle;
	private String jpapername;
	public Integer getJpaperid() {
		return jpaperid;
	}
	public void setJpaperid(Integer jpaperid) {
		this.jpaperid = jpaperid;
	}
	public String getJpapertitle() {
		return jpapertitle;
	}
	public void setJpapertitle(String jpapertitle) {
		this.jpapertitle = jpapertitle;
	}
	public String getJpapername() {
		return jpapername;
	}
	public void setJpapername(String jpapername) {
		this.jpapername = jpapername;
	}
	
}
