<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 송신자에서 꼭 처리가 필요함 -->
<form method="post" enctype="multipart/form-data" action="${cp}upload">
	<input type="file" name="files" multiple>
	<button>파일 전송</button>
</form>
</body>
</html>