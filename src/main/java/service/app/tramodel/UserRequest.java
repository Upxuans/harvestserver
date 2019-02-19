/**
 * By Upxuan
 * 
 * Created in 2018/12/22
 */
package service.app.tramodel;

import java.util.List;

public class UserRequest implements Cloneable {

	private List<Integer> idList;

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
}
