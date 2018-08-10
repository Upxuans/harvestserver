package service.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "tach")
public class TeachAchInfo {

	@Id //该实体不选ID会有bug，选了还不对。。。
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tachteach;
	private Integer tachtype;
	private Integer tachach;
	public Integer getTachteach() {
		return tachteach;
	}
	public void setTachteach(Integer tachteach) {
		this.tachteach = tachteach;
	}
	public Integer getTachtype() {
		return tachtype;
	}
	public void setTachtype(Integer tachtype) {
		this.tachtype = tachtype;
	}
	public Integer getTachach() {
		return tachach;
	}
	public void setTachach(Integer tachach) {
		this.tachach = tachach;
	}
	
	

}
