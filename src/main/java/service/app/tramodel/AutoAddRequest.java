package service.app.tramodel;

import java.util.List;

public class AutoAddRequest extends BaseRequest implements Cloneable {

	private List<Selections> selections;

	public List<Selections> getSelections() {
		return selections;
	}

	public void setSelections(List<Selections> selections) {
		this.selections = selections;
	}
}
