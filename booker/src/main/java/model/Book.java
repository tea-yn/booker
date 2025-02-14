package model;

public class Book {
	private int id;		//書籍管理用ID番号
	private String title;	//本のタイトル
	private String writer;	//本の作者
	private String summary;	//あらすじ
	private String publish_date;	//出版日
	private String category;	//カテゴリー
	private String isbn;         // ISBN
	
	public Book(int id,String title, String writer, String isbn) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.isbn = isbn;
	}
	
	public Book(int id,String title, String writer, String summary, String publish_date, String category, String isbn) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.summary = summary;
		this.publish_date = publish_date;
		this.category = category;
		this.isbn = isbn;
	}
	
	
	//Gertter/Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
        this.title = title;
    }

	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
        this.writer = writer;
    }
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getPublish_date() {
		return publish_date;
	}
	
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getIsbn() { 
		return isbn; 
	}
	
    public void setIsbn(String isbn) { 
    	this.isbn = isbn; 
    }
}
