<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Account" %>
    
    
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>BOOKer</title>
		<link rel = "stylesheet" href="./css/topVew.css">
	</head>
	
	<body>
		<jsp:include page="header.jsp" ></jsp:include>
		
		<ul>
			<li><a href="LoginServlet">ログイン</a></li>
			<li><a href="RegisterServlet">ユーザー登録</a></li>
			<li><% Boolean loginResult2 = false;
			      	loginResult2 = (Boolean)session.getAttribute("loginResult");
			      	System.out.println("loginResult2: " + loginResult2);
			      	if (Boolean.TRUE.equals(loginResult2)) {
			      	// ログイン中の場合は書籍一覧表示あり %>
			     <a href="BookImpressionServlet">書籍一覧</a>
			     <% } else {
			     	// ログインしていない場合は書籍一覧の表示なし %>
			     <% } %>
			</li>
		</ul>
	</body>

</html>