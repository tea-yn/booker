
//感想の投稿に関する処理を行うモデル
package model;

import dao.ImpressionDAO;

public class PostTextLogic {
	public void execute(Impression impression) {
		ImpressionDAO dao = new ImpressionDAO();
		dao.insertImpression(impression);
	}
	
	public boolean executeBool(Impression impression) {
		ImpressionDAO dao = new ImpressionDAO();
		dao.insertImpression(impression);
		return true;
	}
}
