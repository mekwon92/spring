<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp" />
</head>
<body>
	<div class='wrap'>
		<jsp:include page="../common/header.jsp" />
		<main class="container">
			<h1 class="text-center mt-5">로그인</h1>
			<form name="frm" method="post"
				class="mx-auto col-12 col-sm-8 col-md-6 col-lg-5 col-x1-4 col-xxl-3 card p-2 mt-5">
				<input type="text" class="form-control my-3" id="id" placeholder="아이디" name="id" value="${cookie['remember-id'].value}"> 
				<input type="password" class="form-control my-3" id="pw" placeholder="비밀번호" name="pw">
				<!-- 리멤버 미 -->
				<div class="form-check form-switch my-3">
					<input class="form-check-input" type="checkbox" id="mySwitch" name="remember-id" value="yes" ${empty cookie['remember-id'] ? '' : 'checked' }> 
					<label class="form-check-label" for="mySwitch">아이디 기억</label>
				</div>
				<button class="btn btn-primary">로그인</button>
			</form>
			<jsp:include page="../common/footer.jsp" />
		</main>
	</div>
	<script>
	/* 자바로 전처리를 하냐 js로 후처리를 하냐를 선택 */
	</script>
</body>
</html>