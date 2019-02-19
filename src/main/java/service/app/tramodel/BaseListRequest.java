package service.app.tramodel;

import java.util.List;

public class BaseListRequest implements Cloneable {

	private List<BaseRequest> baseHarvestList;

	public List<BaseRequest> getBaseHarvestList() {
		return baseHarvestList;
	}

	public void setBaseHarvestList(List<BaseRequest> baseHarvestList) {
		this.baseHarvestList = baseHarvestList;
	}
}
