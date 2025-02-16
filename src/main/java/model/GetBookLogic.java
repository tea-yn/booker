package model;

import dao.BooksDAO;

public class GetBookLogic {
    public Book execute(int bookId) {
        BooksDAO dao = new BooksDAO();
        return dao.findBookById(bookId); // 書籍IDを基に書籍情報を取得
    }
}
