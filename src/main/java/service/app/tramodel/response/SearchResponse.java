/*
 * By Upxuan
 * 
 * Created in 2019/3/26
 * */
package service.app.tramodel.response;

import java.util.List;

import service.app.domain.Param;

public class SearchResponse {

	protected int id;
	protected String title;
	protected String authors;
	protected String type;
	protected List<Param> params;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthors() {
		return authors;
	}
	
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<Param> getParams() {
		return params;
	}
	
	public void setParams(List<Param> params) {
		this.params = params;
	}
}
