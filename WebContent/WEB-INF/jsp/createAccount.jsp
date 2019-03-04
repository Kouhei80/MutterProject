<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント新規登録</title>
</head>
<body>
	<form action="/toMutterProject/CreateAccount" method="post" name="form" >
		ユーザー名:<input type="text" name="name"><br>
		パスワード:<input type="password" name="pass"><br>
		<input type="submit" value="送信" onclick="return check()">
		<a href="/toMutterProject/">ログイン画面へ</a>
	</form>
	<script>
		function check(){
			//必須にする項目
			if(document.form.name.value == "" &&document.form.pass.value == ""){
				alert("名前とパスワードが入力されていません");
				return false;
			}else if(document.form.name.value == ""){
				alert("名前が入力されていません");
				return false;
			}else if(document.form.pass.value == ""){
				alert("パスワードが入力されていません");
				return false;
			}else{
				return true;
			}
		}
	</script>
</body>
</html>