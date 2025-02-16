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
		
		<!-- 書籍情報の表示 -->
		<h2>${book.title}</h2>  <!-- 書籍タイトルを表示 -->
		<h4>${book.writer}</h4> <!-- 作者を表示 -->
		
		<h3>感想</h3>
		
		<!-- エラーメッセージの表示 -->
		<c:if test="${not empty errorMsg}">
    		<p style="color:red;">${errorMsg}</p> 
		</c:if>
		
		
		<%-- 感想の一覧表示 --%>
		<!-- 感想リストが空の場合のメッセージ -->
			<c:if test="${empty impList}">
			    <p>まだ感想が投稿されていません。</p>
			</c:if>
			
			<!-- 感想リストが存在する場合 -->
			<c:forEach var="imp" items="${impList}">
			    <div class="impression">
			        <p><strong>名無しの読書好き</strong> <c:out value="${imp.impression}"/></p>
			        <hr>
			    </div>
			</c:forEach>
		
		
		<%-- 感想の投稿フォーム --%>
			<form action="BookInf" method="post">
			    <input type="hidden" name="bookId" value="${book.id}"> <!-- 書籍IDを隠しフィールドで送信 -->
			    <label for="impression">感想:</label>
			    <input type="text" name="imp" placeholder="感想を入力してください"> <!-- 感想を入力 -->
			    
			    <input type="submit" value="投稿">
			</form>
		
		
		
		<br>
		<a href="welcomeServlet">トップへ</a>
		<br>
		<p><a href="BookInf?bookId=${book.id}">更新</a></p> <!-- 感想投稿ページへのリンク -->
		
	</body>

</html>