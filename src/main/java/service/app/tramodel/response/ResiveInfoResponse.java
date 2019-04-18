/**
 * By Upxuan
 *
 */
package service.app.tramodel.response;

public class ResiveInfoResponse extends BaseResponse {   //默认传给前端数据的基本容器，后续基本容器扩充
	
	protected String imgName ;

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
}
