<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Account" %>
    
    
<!DOCTYPE html>

<html>

	<head>
		<%-- jQueryのCDN --%>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<%-- GoogleのCDN --%>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<%-- MicrosoftのCDN --%>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
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