/**
 * By Upxuan
 *
 */
package service.app.tramodel.response;

public class BaseResponse {   //默认传给前端数据的基本容器，后续基本容器扩充
	
	protected int errCode ;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
}
