package service.app.domain;

public class AuthorsModel {

	private String name;
	private int degree;
	private Boolean nature1 = false;
	private Boolean nature2 = false;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public Boolean getNature1() {
		return nature1;
	}
	
	public void setNature1(Boolean nature1) {
		this.nature1 = nature1;
	}
	
	public Boolean getNature2() {
		return nature2;
	}
	
	public void setNature2(Boolean nature2) {
		this.nature2 = nature2;
	}
}
