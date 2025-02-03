package servlet;

//ログインに関するリクエストを処理するコントローラ
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPOSTを実行");
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String hn = request.getParameter("hn");
		
		//ログイン処理の実行
		Login login = new Login(userId, pass);
		LoginLogic bo = new LoginLogic();
		boolean loginResult = false; 
		loginResult = bo.execute(login);
		System.out.println("loginResult(doPOST): " + loginResult);
		
		//ログイン処理の成否によって処理を分岐
		if(loginResult == true) {	//ログイン成功時
			//セッションスコープにユーザーIDを保存
			HttpSession session = request.getSession();
			session.setAttribute("hn", hn);
			session.setAttribute("loginResult", true); // loggedInUserはログインユーザー情報
			//System.out.println("loginResult: " + loginResult);
			
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);
		}else {		//ログイン失敗時
			request.setAttribute("errorMessage", "ユーザーIDまたはパスワードが間違っています。");
		    request.getRequestDispatcher("WEB-INF/jsp/loginNG.jsp").forward(request, response);
			
			//リダイレクト
			//response.sendRedirect("LoginServlet");
		}
		
		
		
		
	}
	
}
