package dao;

//Accountテーブルを担当するDAO。全レコードの取得とレコード追加のメソッドを持つ
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountsDAO {
		//データベース接続に使用する情報
		private final String JDBC_URL =
				"jdbc:mysql://localhost/booker"
				+ "?useUnicode=true&characterEncoding=utf8";
		private final String DB_USER = "root";
		private final String DB_PASS = "root";
		
		//ログイン情報を取得するメソッド
		public Account FindByLogin(Login login){
			Account account = null;
			//JDBCドライバを読み込む
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			}
			//データベースに接続
			try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
				
				//SELECT文を準備
				String sql = "SELECT user_id, pass, hn FROM accounts WHERE user_id = ? AND pass = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, login.getUserId());
				pStmt.setString(2, login.getPass());
				
				//SELECT文を実行し、結果表を取得
				ResultSet rs = pStmt.executeQuery();
				
				if(rs.next()) {
					//ユーザーが存在したらデータを取得
					//そのユーザーを表すAccountインスタンスを生成
					String userId = rs.getString("user_id");
					String pass = rs.getString("pass");
					String hn = rs.getString("hn");
					if( hn == null) {
						account = new Account(userId, pass);
						
						System.out.println("account OK");
					}else if( hn != null){
						account = new Account(userId, pass, hn);
					}
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return account;
		}
		
		
		//ユーザーアカウントを追加するメソッド
		public boolean createAccount(Account account) {
			//JDBCドライバを読み込む
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			}
			
			boolean result = false;
			String sql = "INSERT INTO accounts (user_id, pass, hn) VALUES (?, ?, ?)";
			
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
				try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				// データベース接続とSQL実行の準備
					pstmt.setString(1, account.getUserId());
					pstmt.setString(2, account.getPass());
					pstmt.setString(3, "名無しの読書好き");
					
					int rows = pstmt.executeUpdate();
					if (rows > 0) {
						result = true; // 登録成功
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL Error: " + e.getMessage());
			}
			return result;
		}
		
		
		//ユーザーの重複を確認するメソッド
		public boolean isUserIdExists(String userId) {
			//JDBCドライバを読み込む
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			}
			
			boolean exists = false;
			String sql = "SELECT COUNT(*) FROM accounts WHERE user_id = ?";
			
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, userId);
				//System.out.println("Checking userId: " + userId); // ログ出力
				
				
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					exists = rs.getInt(1) > 0;
					//System.out.println("User ID exists: " + exists); // ログ出力
				}
			} catch (SQLException e) {
				e.printStackTrace();
				 System.out.println("SQL Exception: " + e.getMessage());
				return exists;
			}
			exists = true;
			return exists;
			
		}
}