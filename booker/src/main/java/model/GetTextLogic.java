
//すべての感想をデータベースから取得する
package model;

import java.util.List;

import dao.ImpressionDAO;

public class GetTextLogic {
	/*public List<Impression> execute(int bookId) {
        // DAOを使って、指定した書籍IDに紐づく感想を取得
        ImpressionDAO dao = new ImpressionDAO();
        List<Impression> impList = dao.findByBookId(bookId);
        return impList;
    }*/

	public List<Impression> execute(int bookId) {
        // DAOを使って、指定した書籍IDに紐づく感想を取得
        ImpressionDAO dao = new ImpressionDAO();
        return dao.findByBookId(bookId); // 書籍IDに基づいて感想を取得
    }

}
