package test;

import model.Login;
import model.LoginLogic;

public class LoginLogicTest {
	public static void main(String[] args) {
		testExecureOK();	//ログイン成功のテスト
		testExecureNG();	//ログイン失敗のテスト
	}
		
		public static void testExecureOK() {
			Login login = new Login("bookman","1234");
			LoginLogic bo = new LoginLogic();
			boolean result = bo.execute(login);
			
			if(result) {
				System.out.println("testExecureOK：成功しました");
			}else {
				System.out.println("testExecureOK：失敗しました");
			}
		}
		public static void testExecureNG() {
			Login login = new Login("bookman","12345");
			LoginLogic bo = new LoginLogic();
			boolean result = bo.execute(login);
			
			if(!result) {
				System.out.println("testExecureNG:成功しました");
			}else {
				System.out.println("testExecureNG:失敗しました");
			}
		}
}
