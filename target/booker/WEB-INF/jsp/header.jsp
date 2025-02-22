<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Account" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<link rel = "stylesheet" href="./css/topVew.css">
	</head>
	
	<body>
		<h1>書籍感想投稿サイト<span class="english"> BOOKer</span></h1>
		<!-- ログイン状態に応じて表示を切り替える -->
        <h2><% Boolean loginResult2 = false;
        loginResult2 = (Boolean)session.getAttribute("loginResult");
        System.out.println("loginResult2: " + loginResult2);
        if (Boolean.TRUE.equals(loginResult2)) {
        // ログイン成功の処理 %>
            <p>ログイン中 <a href="LogoutServlet">ログアウト</a></p>
        <% } else {
        	// ログイン失敗の処理%>
            <p>未ログイン</p>
        <% } %></h2>
    </div>
	</body>

</html>