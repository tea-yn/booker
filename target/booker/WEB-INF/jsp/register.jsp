<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>


	<html>
		<head>
			<meta charset="UTF-8">
			<title>BOOKer</title>
			<link rel = "stylesheet" href="./css/topVew.css">
		</head>
		
		<body>
			<jsp:include page="header.jsp" ></jsp:include>
			
			<h1>ユーザー登録</h1>
			
			 <!-- エラーメッセージの表示 -->
		    <c:if test="${not empty errorMsg}">
		        <p style="color: red;">${errorMsg}</p>
		    </c:if>
		    
		    <!-- 登録フォーム -->
		    <form action="RegisterServlet" method="post">
		        <label for="username">ユーザーID:</label>
		        <input type="text" id="userId" name="userId" required>
		        <br>
		        
		        <label for="password">パスワード:</label>
		        <input type="password" id="pass" name="pass" required>
		        <br>
		        
		        <input type="submit" value="登録">
		    </form>
		    
		    <br>
		    <a href="LoginServlet">ログイン画面に戻る</a>
		    <br>
			<a href="welcomeServlet">トップへ</a>
			<br>
		</body>
	</html>