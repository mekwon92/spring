<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		Cookie cookie = new Cookie("newjeans","hypeboy");
		cookie.setMaxAge(60 * 60 * 24);	//쿠키 만료시간 지정
		response.addCookie(cookie);
		/* F12-Network에서 확인가능 */
	%>

</body>
</html>