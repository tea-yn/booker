package model;

import dao.BooksDAO;

public class PostBookLogic {
	public void execute(Book book) {
		BooksDAO dao = new BooksDAO();
		dao.creat(book);
		
	}

}
