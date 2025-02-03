package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Impression;

//Bookinfテーブルを担当するDAO。全レコードの取得とレコード追加のメソッドを持つ
public class BooksDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost/booker?useUnicode=true&characterEncoding=utf8";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	 public boolean insertImpression(Impression impression) {
	        // SQL文を準備
	        String sql = "INSERT INTO impressions (book_id, text, user_name) VALUES (?, ?, ?)";
	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            // プレースホルダに値を設定
	            pstmt.setInt(1, impression.getBookId());
	            pstmt.setString(2, impression.getImpression());
	            pstmt.setString(3, impression.getHn()); // 必要に応じて

	            // SQLを実行
	            int result = pstmt.executeUpdate();
	            return result > 0; // 登録成功なら true を返す
	        } catch (SQLException e) {
	            e.printStackTrace(); // エラーを出力
	            return false; // 登録失敗
	        }
	}
	
	//データベース接続を取得
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }

    // データベース接続を閉じる
    protected void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	
	//書籍情報を取得するメソッド
	public List<Book> findAll(){
		List<Book> bookList = new ArrayList<>();
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
				try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
					
					//SELECT文を準備
					String sql = "SELECT id, title, writer FROM bookinf";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					
					//SELECTを実行し、結果表を取得
					ResultSet rs = pStmt.executeQuery();
					
					//結果表に格納されたレコードの内容を
					//Bookインスタンスに設定し、ArraListインスタンスに追加
					while (rs.next()) {
						int id = rs.getInt("id");
						String title = rs.getString("title");
						String writer = rs.getString("writer");
						
						// デバッグ用ログ
				        //System.out.println("取得したデータ: " + title + ", " + writer);
				        
						Book book = new Book(id,title, writer);
						bookList.add(book);
					}
					System.out.println("データ取得終了。取得件数: " + bookList.size());
				}catch (SQLException e) {
				    e.printStackTrace();
				    return new ArrayList<>();  // 空のリストを返す
				}

				return bookList;			
	}
	
	public Book findBookById(int bookId) {
	    Connection conn = null;
	    Book book = null;
	    try {
	        conn = getConnection();
	        String sql = "SELECT id, title, writer FROM Bookinf WHERE id = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, bookId);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            int id = rs.getInt("id");
	            String title = rs.getString("title");
	            String writer = rs.getString("writer");
	            book = new Book(id, title, writer);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnection(conn);
	    }
	    return book;
	}
	
	
	
	//書籍情報を追加するメソッド
	public boolean creat(Book book) {
		//JDBCドライバを読み込む
				try {
					//Class.forName("com.mysql.jdbc.Driver");
					Class.forName("com.mysql.cj.jdbc.Driver");

				}catch(ClassNotFoundException e) {
					throw new IllegalStateException("JDBCドライバを読み込めませんでした");
				}
				//データベース接続
				try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
				
					//INSERT文の準備(idは自動連番なので指定しなくてもよい）
					String sql = "INSERT INTO bookinf(title,writer) VALUES(?,?)";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					
					//INSERT文中の「？」に使用する値を想定してSQL文を完成
					pStmt.setString(1,book.getTitle());
					
					pStmt.setString(2,book.getWriter());
					
					//INSERT文を実行（resultには追加された行数が代入される
					int result = pStmt.executeUpdate();
					if (result != 1 ) {
						return false;
					}
				}catch (SQLException e ) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
		}
