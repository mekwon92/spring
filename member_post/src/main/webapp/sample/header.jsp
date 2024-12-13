<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<h1>header.jsp</h1>
	<!-- include시 원하는부분만 -->
	<%
		String num = request.getParameter("num");
	%>
	<h3>header : <%=num%></h3>
	<!-- header도 out의 값을 받아온다...?  -->
	<!-- include시킨 자의 파라미터를 받아온다... -->
	
	<!-- page-request-session-application -->
	<!-- 스프링 tiles -->
