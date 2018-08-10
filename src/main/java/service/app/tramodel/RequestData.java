/**
 * 
 */
/**
 * @author Bran
 *
 */
package service.app.tramodel;

public class RequestData implements Cloneable{//从前端拿过来的数据容器
	
	private Integer id;
	private String username;
	private String password;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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