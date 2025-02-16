<%-- ログイン成功画面を出力するビュー --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Account" %>
    
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>BOOKer</title>
		<link rel = "stylesheet" href="./css/topVew.css">
	</head>
	
	<body>
		<jsp:include page="header.jsp" ></jsp:include>
		<p>BOOKerへようこそ！</p>
		<br>
		<a href="BookImpressionServlet">感想を投稿</a>
		<br>
		<a href="welcomeServlet">トップへ</a>
		
	</body>

</html>