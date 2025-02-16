package model;

import dao.AccountsDAO;

public class PostRegisterLogic {
	public boolean execute(Account account) {
		AccountsDAO dao = new AccountsDAO();
		// 入力データの検証（ユーザーIDの重複確認）
		if (dao.isUserIdExists(account.getUserId()) == false) {
			//System.out.println("daoに登録されている情報  userID: " + account.getUserId() + " / pass: " + account.getPass());
			return false; // ユーザーIDが既に存在する場合、登録失敗
		}else if(dao.isUserIdExists(account.getUserId()) == true){
			//ユーザーIDに重複がなければ
			// データベースへの登録
			if(dao.createAccount(account) == false) {
				System.out.println("データベースへの登録に失敗しました。");
				return false;
			}
		}
		return true; // 登録成功なら true、失敗なら false dao.createAccount(account)
	}
}
