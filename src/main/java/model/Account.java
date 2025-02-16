package model;

//ユーザー情報テーブル（accountsテーブル)のレコードを表すEntity
public class Account {
	private String userId;	//ユーザーID
	private String pass;	//パスワード
	private String hn;		//ハンドルネーム
	private String imp;		//感想

	public Account(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}
	public Account(String userId, String pass, String hn) {
		this.userId = userId;
		this.pass = pass;
		this.hn = hn;
	}
	public Account(String userId, String pass, String hn,String imp) {
		this.userId = userId;
		this.pass = pass;
		this.hn = hn;
		this.imp = imp;
	}
		
	
	
	//getter.setter
		public String getUserId() {
			return userId;
		}
		public String getPass() {
			return pass;
		}
		public String getHn() {
			return hn;
		}
		public String getImp() {
			return imp;
		}
				
		public void setHn(String hn) {
			this.hn = hn;
		}
		public void setImp(String imp) {
			this.imp = imp;
		}
		
}
