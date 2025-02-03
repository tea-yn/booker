<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List, model.Book" %>
    
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
		<h2>書籍一覧</h2>
		<!-- 書籍情報を繰り返し表示 -->
		<c:forEach var="book" items="${bookList}">
			<p>
				<span class="title">
					<a href="BookInf?bookId=${book.id}">
					<c:out value="${book.title}"/></a></span>
        		<br>
				<a href="BookInf?bookId=${book.id}"><c:out value="${book.writer}"/></a><br>
			</p>
		</c:forEach>
		
		<br>
		<a href="welcomeServlet">トップへ</a>
	</body>

</html>