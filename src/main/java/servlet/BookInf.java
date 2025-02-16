//書籍の詳細画面の表示に関するリクエストを処理するコントローラ

package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.GetBookLogic;
import model.GetTextLogic;
import model.Impression;
import model.PostTextLogic;


@WebServlet("/BookInf")
public class BookInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータから書籍IDを取得
				String bookIdStr = request.getParameter("bookId");
				
				// 書籍IDが空または不正な場合のエラーハンドリング
				//書籍IDを受け取る
			    if (bookIdStr == null || bookIdStr.isEmpty()) {
			        request.setAttribute("errorMsg", "書籍IDが指定されていません(doGet)");
			        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			        return;
			    }
					
			    int bookId = Integer.parseInt(bookIdStr); // 数値変換の例外処理
					
			 // 書籍情報をBooksDAOを使用して取得
			    GetBookLogic getBookLogic = new GetBookLogic();
			    Book book = getBookLogic.execute(bookId); // 書籍IDを基に書籍情報を取得する

			    if (book == null) {
			        response.sendError(HttpServletResponse.SC_NOT_FOUND, "書籍が見つかりません");
			        return;
			    }

			    // 感想リストを取得
			    GetTextLogic getTextLogic = new GetTextLogic();
			    List<Impression> impList = getTextLogic.execute(bookId);

			 // リクエストスコープにデータを設定
			    request.setAttribute("impList", impList);
			    request.setAttribute("book", book);
			    request.setAttribute("bookId", bookId);
			    
			 // セッションスコープに保存
			    //HttpSession session = request.getSession();
			    //session.setAttribute("book", book); 
			    
			//jspにフォワード
			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("WEB-INF/jsp/bookInf.jsp");
			dispatcher.forward(request, response);	
	}

	// POSTメソッド（感想投稿など）
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("imp");
		String bookIdStr = request.getParameter("bookId");
		
		// 2. 書籍IDをintに変換
        int bookId = Integer.parseInt(bookIdStr);
		
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
            // 3. 書籍情報をGetBookLogicを使って取得
            GetBookLogic getBookLogic = new GetBookLogic();
            Book book = getBookLogic.execute(bookId);

            // 4. 書籍情報をリクエストスコープにセット
            request.setAttribute("book", book);
		}
			    
			    // 感想オブジェクトを作成してセッションに保存
			    HttpSession session = request.getSession();
			    Impression imp = new Impression(bookId, text);
			    imp.setBookId(bookId);
			    imp.setImpression(text);
			    session.setAttribute("imp", imp);

			    System.out.println("doPostのログ");
			    System.out.println("UserHN: " + imp.getHn());
			    System.out.println("bookId: " + imp.getBookId());
			    System.out.println("Text: " + imp.getImpression());
			    
			    
			    //感想をデータベースに保存
			    PostTextLogic postTextLogic = new PostTextLogic();
			    postTextLogic.execute(imp);
			    

			 //再取得して一覧を表示
			    GetTextLogic getTextLogic = new GetTextLogic();
			    List<Impression> impList = getTextLogic.execute(bookId);
			    request.setAttribute("impList", impList);
			   
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/bookInf.jsp");
		dispatcher.forward(request, response);
		}

}
