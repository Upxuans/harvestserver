/*
 * By Upxuan
 * 
 * Created in 2019/3/26
 * */
package service.app.tramodel;

public class SearchRequest extends BaseRequest implements Cloneable {

	private String keyWords;

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
}
