package service.app.tramodel;

public class HarvestRequest extends BaseRequest implements Cloneable {

	private int load;
	private int source;
	
	public int getLoad() {
		return load;
	}
	
	public void setLoad(int load) {
		this.load = load;
	}
	
	public int getSource() {
		return source;
	}
	
	public void setSource(int source) {
		this.source = source;
	}
}
