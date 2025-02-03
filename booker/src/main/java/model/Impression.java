package model;

//感想情報テーブル（impressionテーブル)のレコードを表すEntity
public class Impression {
	private int id;				//impressionテーブルの識別用ID
	private String imp_text;			//感想
	private int book_id;		//紐づけ用書籍ID
	private String user_id;		//ユーザーID
	private String hn;

	//コンストラクタ
	/*public Impression(String imp, int book_id) {
		this.imp = imp;
		this.book_id = book_id;
	}*/
	public Impression(int id,String imp_text,String userId, int bookId) {
		this.id = id;
		this.imp_text = imp_text;
		this.book_id = bookId;
		this.user_id = userId;
	}
	/*
	public Impression(String imp_text,String userId, int bookId) {
		this.imp_text = imp_text;
		this.book_id = bookId;
		this.user_id = userId;
	}
	
	public Impression() {
		
	}
	
	public Impression(String imp_text,String userId, int bookId,String hn) {
		this.imp_text = imp_text;
		this.book_id = bookId;
		this.user_id = userId;
		this.hn = hn;
		}*/
	
	public Impression(int bookId, String imp_text) {
		this.book_id = bookId;
		this.imp_text = imp_text;
	}
	

	//getter*setter
	public int getId() {
		return id;
	}
	
	public String getImpression() {
		return imp_text;
	}
	public void setImpression(String imp_text) {
		this.imp_text = imp_text;
	}
	

	public int getBookId() {
		return book_id;
	}
	
	public String getUserId() {
		return user_id;
	}
	
	public void setBookId(int book_id) {
		this.book_id = book_id;
	}
		
	public String getHn() {
		return hn;
	}
	
	
}
