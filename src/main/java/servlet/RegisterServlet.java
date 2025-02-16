package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.PostRegisterLogic;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOSTメソッドを実行");
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		
		//フォームから送信されたユーザー名とパスワードを取得
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		System.out.println("フォームから送信された  userID: " + userId + " / pass: " + pass);
		
		//空欄がないか確認
		if( userId == null || userId.isEmpty() || pass == null || pass.isEmpty()) {
			request.setAttribute("errorMsg", "ユーザー名またはパスワードを入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");	//空欄があれば登録画面に戻る
            dispatcher.forward(request, response);
            return;
		}
		
		//アカウント情報を作成
		Account account = new Account(userId, pass);
		
		//AccountsDAOを用いて重複がないか確認し、データベースに登録
		PostRegisterLogic postRegisterLogic = new PostRegisterLogic();
		boolean isRegistered = postRegisterLogic.execute(account);
		
		if (isRegistered == true) {	//登録が成功した場合
			System.out.println("ユーザー登録成功");
	        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	    } else {	//ユーザーIDがすでに存在し、登録に失敗した場合
	        request.setAttribute("errorMsg", "登録に失敗しました。そのユーザーIDはすでに登録されています");
	        request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
	     // ユーザーIDが重複していた場合
	        //response.sendRedirect("RegisterServlet");  // リダイレクトしてフォームを再表示
	    }
		
	}

}
