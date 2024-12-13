<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${10+4}</h3>
	<h3>${'10'+4}</h3>
	<%-- <h3>${'a'+4}</h3> 500 에러--%>
	<h3>${10 - 4}</h3>
	<h3>${10 * 4}</h3>
	<h3>${10 / 4}</h3>
	<h3>${10 % 4}</h3>
	<h3>${3.14 + 1} -> double ${3.14 + 1}</h3>
	<h3>3.14 > 1 ${3.14 > 1}</h3>
	<h3>\${3.14 != 1} ${3.14 != 1}</h3>
	
	<%-- <h3>${10 div 4}</h3> --%>
	<h3>${10 mod 4}</h3>
	<h3>${10 gt 4}</h3>
	<h3>${10 ge 4}</h3>
	<h3>${10 eq 4}</h3>
	<%-- <h3>${10 ne 4}</h3> --%>
	<h3>\${true and false} :: ${true and false}</h3>
	<h3>\${true or false} :: ${true or false}</h3>
	<h3>\${not true} :: ${not true}</h3>
	
	<hr>
	<%
		List<String> list = new ArrayList<>();
		// list.add("abcd");
		String[] strings = {};
		
		pageContext.setAttribute("list", list);
		pageContext.setAttribute("array", strings);
		pageContext.setAttribute("str1", "");
		pageContext.setAttribute("str2", "abcd");
		pageContext.setAttribute("str3", null);
	%>
	<!-- null값 뿐만 아니라 길이값도 반영하기 때문에 사용하기가 편함 -->
	<h3>${empty list}</h3>
	<h3>${empty array}</h3>
	<h3>${empty str1}</h3>
	<h3>${empty str2}</h3>
	<h3>${empty str3}</h3>
	<h3>${not empty str3}</h3>
</body>
</html>