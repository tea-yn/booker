<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>BOOKer</title>
			<link rel = "stylesheet" href="./css/topVew.css">
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
	    <!-- エラーメッセージの表示 -->
	    <p style="color: red;">
	        <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
	    </p>
	</body>
</html>
