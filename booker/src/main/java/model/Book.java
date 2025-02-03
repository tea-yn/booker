package model;

public class Book {
	private int id;		//書籍管理用ID番号
	private String title;	//本のタイトル
	private String writer;	//本の作者
	
	public Book(int id,String title, String writer) {
		this.id = id;
		this.title = title;
		this.writer = writer;
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
	

}
