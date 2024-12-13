<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp" />
</head>
<body>
	<div class='wrap'>
		<jsp:include page="../common/header.jsp" />
		<main class="container">
			 <div class="clearfix py-4">
				<h2 class="float-start">Post View</h2>
			 </div>
			 <div class="my-3 col-md-9 mx-auto">
				 <form method="post" action="modify?${cri.qs2}">
	                <label for="title" class="form-label mt-3"><i class="fa-solid fa-t text-primary"></i><b> Title</b></label>
	                <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${post.title}">
	
	                <label for="content" class="form-label mt-3"><i class="fa-solid fa-align-left text-primary"></i><b> Content</b></label>
	                <textarea class="form-control" id="content" name="content" rows="20">${post.content}</textarea>
	
	                <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user text-primary"></i><b> Writer</b></label>
	                <input type="text" class="form-control" id="writer" placeholder="writer" name="writer" value="${post.writer}" readonly>
	                <hr>
	                <div class="text-center my-5">
	             	    <button class="btn btn-primary">작성</button>
	                    <a href="list" class="btn">목록</a>
	                </div>
	                <input type="hidden" name="pno" value="${post.pno}">
	            </form>
            </div>
		</main>
		<jsp:include page="../common/footer.jsp" />
	</div>
</body>
</html>