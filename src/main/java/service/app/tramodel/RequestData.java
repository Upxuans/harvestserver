/**
 * 
 */
/**
 * @author Bran
 *
 */
package service.app.tramodel;

public class RequestData implements Cloneable{//从前端拿过来的数据容器
	
	private int type;
	private String username;
	private String password;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
//	private String token;     //搁置起来
//	
//	public String getToken() {
//		return token;
//	}
//	public void setToken(String token) {
//		this.token = token;
//	}
}