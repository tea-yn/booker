//ログイン処理を実行するBO
package model;

import dao.AccountsDAO;

public class LoginLogic {
	public boolean execute(Login login) {
		AccountsDAO dao = new AccountsDAO();
		Account account = dao.FindByLogin(login);
		System.out.println("account: " + account);
		return account != null;
	}

}
