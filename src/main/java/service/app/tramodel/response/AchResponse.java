package service.app.tramodel.response;

import service.app.domain.PaperInfo;

public class AchResponse extends BaseResponse{
	private PaperInfo  paperInfo;

	public PaperInfo getPaperInfo() {
		return paperInfo;
	}

	public void setPaperInfo(PaperInfo paperInfo) {
		this.paperInfo = paperInfo;
	}
	

}
