package servlet;

//書籍一覧の表示に関するリクエストを処理するコントローラ
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.GetBookListLogic;

@WebServlet("/BookImpressionServlet")
public class BookImpressionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//書籍リストを取得して、リクエストスコープに保存
		GetBookListLogic getBookListLogic = new GetBookListLogic();
		List<Book> bookList = getBookListLogic.execute();
		request.setAttribute("bookList", bookList);
		
		
		//フォワード(書籍一覧に飛ぶ）
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("WEB-INF/jsp/bookList.jsp");
				dispatcher.forward(request, response);
	}
}
