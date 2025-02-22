<%-- ログイン画面を出力するビュー --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>BOOKer</title>
		<link rel = "stylesheet" href="./css/login.css">
	</head>
	
	<body>
		<jsp:include page="header.jsp" ></jsp:include>
		<form action="LoginServlet" method="post">
			ユーザーID：<input type="text" name="userId"><br>
			<br>
			パスワード：<input type="password" name="pass"><br>
			<br>
			<input type="submit" value="ログイン">
		</form>
		
		<br>
		<a href="welcomeServlet">トップへ</a>	
		
	</body>

</html>