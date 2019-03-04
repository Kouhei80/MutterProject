<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User,model.Tweet,java.util.List" %>
    <%
    //セッションスコープに保存されたユーザー情報を取得
    User  loginUser = (User) session.getAttribute("loginUser");
    //リクエストスコープに保存されたTweetリストを取得
    List<Tweet> tweetList  = (List<Tweet>) request.getAttribute("tweetList");
    //リクエストスコープに保存されたerrorMsgを取得
    String errorMsg = (String) request.getAttribute("errorMsg");
    //リクエストスコープに保存されたmsgを取得
    String msg = (String) request.getAttribute("msg");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやき掲示板</title>
</head>
<body>
	<h1>メインページ</h1>
	<p>
	<%= loginUser.getName() %>さん、ログイン中
	<a href="/toMutterProject/Logout">ログアウト</a>
	</p>
	<p><a href="/toMutterProject/Main">更新</a></p>
	<form action="/toMutterProject/Main" method="post">
		<input type="text" name="text">
		<input type="submit" value="送信">
	</form>
	<% if(errorMsg != null){ %>
		<p><%=errorMsg %></p>
	<% } %>

	<% if(msg != null){ %>
		<div class="alert">
			<%=msg %>
		</div>
	<%} %>
	<% for(Tweet tweet : tweetList){ %>
		<p><%=  tweet.getUserName() %>:<%= tweet.getText() %><br><%= tweet.getUpdated() %></p>
		<a href="/toMutterProject/Main?action=delete&id=<%=String.valueOf(tweet.getId()) %>" class="btn" onclick="return confirm('削除してよろしいですか？');">削除</a>
	<% } %>
</body>
</html>