/**
 * By Upxuan
 * 
 * Created in 2018/11/5
 */
package service.app.tramodel;

public class ReviseInfoRequest extends BaseRequest implements Cloneable{

	protected String imageUrl;
	protected String filename;
	protected String link;
	protected String tel;
	protected String email;
	protected String direction;
	protected String pass;
	protected String checkPass;
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCheckPass() {
		return checkPass;
	}

	public void setCheckPass(String checkPass) {
		this.checkPass = checkPass;
	}

	@Override
	public String toString() {
		return "ReviseInfoRequest [imageUrl=" + imageUrl + ", filename=" + filename + ", link=" + link + ", tel=" + tel
				+ ", email=" + email + ", direction=" + direction + ", pass=" + pass + ", checkPass=" + checkPass + "]";
	}
}
