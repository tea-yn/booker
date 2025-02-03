package model;

import java.util.List;

import dao.BooksDAO;

//すべての書籍情報をデータベースから取得するクラス
public class GetBookListLogic {
	public List<Book> execute(){
		BooksDAO dao = new BooksDAO();
		List<Book> bookList = dao.findAll();
		return bookList;
	}

}
