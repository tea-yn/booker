package dao;

//Impression(感想）テーブルを担当するDAO。全レコードの取得とレコード追加のメソッドを持つ

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Impression;

public class ImpressionDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL =
		"jdbc:mysql://localhost/booker"
			+ "?useUnicode=true&characterEncoding=utf8";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";


	//感想リストを取得するメソッド
	public List<Impression> findByBookId(int bookId) {
        List<Impression> impList = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        //データベースに接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            //SELECT文を準備
        	String sql = "SELECT * FROM impressions WHERE book_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, bookId);
            //SELECTを実行し、結果表を取得
            ResultSet rs = pStmt.executeQuery();
            
            //結果表に格納されたrecordの内容を
            //Impressionインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
            	 int id = rs.getInt("id");					//Impressionテーブルの識別用ID
                 String text = rs.getString("text");		//感想
                 String userId = rs.getString("user_id");	//ユーザーのID（char型）
                 int bId = rs.getInt("book_id");
             
                 System.out.println("取得したデータ: " + id + ", " + text);
                 
                 Impression imp = new Impression(id, text, userId, bId);
                 impList.add(imp);
            }
            //ログの出力
            System.out.println("impListデータ取得終了。取得件数: " + impList.size());
            if (impList == null || impList.isEmpty()) {
                System.out.println("impListは空です。");
            } else {
                for (Impression imp : impList) {
                    //System.out.println("感想: " + imp.getImpression());
                    //System.out.println("ユーザー名: " + imp.getHn());
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return impList;
    }
		
	
	//感想を追加するメソッド（creatText)
	public boolean creatText(Impression impression) {
	//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
						}
			//データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
						
				//INSERT文の準備
				String sql = "INSERT INTO impression(imp,id) VALUES(?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//INSERT文中の「？」に使用する値を想定してSQL文を完成
				pStmt.setString(1,impression.getImpression());
				
				//INSERT文を実行（resultには追加された行数が代入される
				int result = pStmt.executeUpdate();
				if (result != 1 ) {
					return false;
				}
				System.out.println("追加された行数： " + result);
							
			}catch (SQLException e ) {
				e.printStackTrace();
				return false;
		}
		return true;
	}
	
	
	public boolean insertImpression(Impression impression) {
		// SQL文の準備
		String sql = "INSERT INTO impressions (book_id, user_id, text) VALUES (?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
		PreparedStatement pstmt = conn.prepareStatement(sql)) {         
			// パラメータを設定
			pstmt.setInt(1, impression.getBookId());
			pstmt.setString(2, impression.getUserId());
			pstmt.setString(3, impression.getImpression());          
			// SQL実行
			int result = pstmt.executeUpdate();
			return result > 0; // 登録成功ならtrueを返す
		} catch (SQLException e) {
			e.printStackTrace(); // エラーログを出力
			return false; // 登録失敗ならfalseを返す
		}
	}
}