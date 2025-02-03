package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得
		HttpSession session = request.getSession(false); // セッションが存在しない場合はnullを返す
		
		// セッションが存在する場合、無効化
		if (session != null) {
			// セッションスコープからログイン情報を削除
            session.removeAttribute("loginResult");
            session.invalidate(); // セッションを無効化
            System.out.println("セッション無効化完了");

		}
		
		// TOPページにリダイレクト
		response.sendRedirect("welcomeServlet"); // 必要に応じて他のページに変更
		
		/*
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);*/
	}
}
