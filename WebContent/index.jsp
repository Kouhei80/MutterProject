<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやき掲示板</title>
</head>
<body>
	<h1>Hello!!</h1>
		<form action="/toMutterProject/Login" method="post">
		ユーザー名:<input type="text" name="name"><br>
		パスワード:<input type="password" name="pass"><br>
		<input type="submit" value="ログイン">
		</form>
		<a href="/toMutterProject/CreateAccount">アカウント作成</a>
</body>
</html>