<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment-with-locales.min.js" integrity="sha512-4F1cxYdMiAW98oomSLaygEwmCnIP38pb4Kx70yQYqRwLVCs3DbRumfBq82T08g/4LJ/smbFGFpmeFlQgoDccgg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
	<div class='wrap'>
		<jsp:include page="../common/header.jsp" />
		<main class="container">
			 <div class="clearfix py-4">
				<h2 class="float-start">Post View</h2>
			 </div>
			 <div class="my-3 col-md-9 mx-auto">
                <label for="title" class="form-label mt-3"><i class="fa-solid fa-t text-secondary"></i><b> Title</b></label>
                <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${post.title}" disabled>

                <label for="content" class="form-label mt-3"><i class="fa-solid fa-align-left text-secondary"></i><b> Content</b></label>
                <textarea class="form-control" id="content" name="content" disabled rows="20">${post.content}</textarea>

                <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user text-secondary"></i><b> Writer</b></label>
                <input type="text" class="form-control" id="writer" placeholder="writer" name="writer" value="${post.writer}" disabled>

                <label for="regdate" class="form-label mt-3"><i class="fa-solid fa-calendar-days text-secondary"></i><b> Register Date</b></label>
                <input type="text" class="form-control" id="regdate" placeholder="regdate" name="regdate" value="${post.regdate}" disabled>

                <label for="updatedate" class="form-label mt-3"><i class="fa-solid fa-wrench text-secondary"></i><b> Updated Date</b></label>
                <input type="text" class="form-control" id="updatedate" placeholder="updatedate" name="updatedate" value="${post.updatedate}" disabled>
                <label class="form-label mt-3"><i class="fa-solid fa-paperclip text-secondary"></i><b> Attach</b><br></label><br>
                <!-- readonly는 서버로 보내짐 disabled와 차이가 있음 -->
				<%-- ${post} --%>
				<ul class="list-group attach-result">
				<c:if test="${empty post.attachs}">
					<li class="list-group-item">첨부파일이 없습니다.</li>
				</c:if>
				<c:forEach items="${post.attachs}" var="a">
					<li class="list-group-item"><a href="${cp}download?uuid=${a.uuid}&origin=${a.origin}&path=${a.path}">${a.origin}</a></li>			
				</c:forEach>
				</ul>
				
				<!-- 내 댓글 -->
				<div class="clearfix mt-3 mb-2">
                	<label class="form-label float-start"><i class="fa-brands fa-replyd text-warning"></i><b> My Reply</b></label>
				</div>
				<ul class="list-group small my-replies my-2" >
                 	<li class="list-group-item" data-rno="8">
	                    <p class="text-black fw-bold mt-3 text-truncate">댓글</p>
	                    <div class="clearfix text-secondary">
	                        <span class="float-start">abcd</span>
	                        <span class="float-end small">5일 전</span>
	                        <a class="float-end small text-danger mx-2 btn-reply-remove" href="#">삭제</a>
	                    </div>
                	</li>
				</ul>
				
				<!-- 전체댓글 구간 -->
				<div class="clearfix mt-3 mb-2">
                	<label class="form-label float-start"><i class="fa-brands fa-replyd text-secondary"></i><b> Reply</b></label>
					<button type="button" class="btn btn-primary float-end btn-sm" id="btnWriteReply">write reply</button>
				</div>
				<ul class="list-group small replies">
                 
				</ul>
				
				<div class="d-grid my-3">
					<button class="btn btn-primary btn-block btn-more-reply">댓글 더보기</button>
				</div>
                
                <div class="text-center my-5">
                    <c:if test="${post.writer == member.id}">
	                    <a href="modify?pno=${post.pno}&${cri.qs2}" class="btn btn-warning">수정</a>
	                    <a href="remove?pno=${post.pno}&${cri.qs2}" class="btn btn-danger" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
                    </c:if>
                    <a href="list?${cri.qs2}" class="btn btn-primary">목록</a>
                </div>
            </div>
		</main>
		<script src="${cp}js/reply.js"></script>
		<script>
			moment.locale('ko');
			const pno = '${post.pno}'
			// replyService.write({content:"abcd"});
			
			
			//목록조회
			function list(cri, myOnly) {
				replyService.list(pno, cri, function(data) {
					if(!data.list.length) {
						$(".btn-more-reply")
						.prop("disabled",true)
						.text("댓글이 없습니다")
						.removeClass("btn-primary")
						.addClass("btn-secondary");
						return;
					}
					let myStr = "";					
					for(let i in data.myList) {
						myStr += makeLi(data.myList[i]);
					}
					$(".my-replies").html(myStr);
					//추가 css 작업(다크모드 적용시)
					//$(".my-replies .text-secondary, my-replies .text-black").removeClass("text-secondary text-black");
					
					if(myOnly) return;
					
					let str = "";
					for(let i in data.list) {
						str += makeLi(data.list[i]);
					}
					
	
					$(".replies").append(str);
					
				});				
			}
			list();

			// 단일 리스트 문자열 생성
			function makeLi(reply) {
                return `<li class="list-group-item" data-rno="\${reply.rno}">
                    <p class="text-black fw-bold mt-3 text-truncate">\${reply.content}</p>
                    <div class="clearfix text-secondary">
                        <span class="float-start">\${reply.writer}</span>
                        <span class="float-end small">\${moment(reply.regdate,'yyyy/MM/DD-HH:mm:ss').fromNow()}</span>
                        <a class="float-end small text-danger mx-2 btn-reply-remove" href="#">삭제</a>
                    </div>
                </li>`;
            }
			
			
			// li 클릭시 이벤트 -> 동적.. 이벤트 위임이 필요함
			$(".replies, .my-replies").on("click", "li", function() {
				const rno = $(this).data("rno");
				//console.log($(this).data("rno"))
				replyService.view(rno, function(data) {
					$("#replyModal").find(".modal-footer div button").hide()
					.filter(":gt(0)").show();

					$("#replyModal").data("rno", rno).modal("show");
					$("#replyContent").val(data.content);
					$("#replyWriter").val(data.writer);
					
					console.log(data);	
				})
			});
			
			//li .btn-reply-remove 클릭시 이벤트 - 삭제
			$(".replies, .my-replies").on("click", "li .btn-reply-remove", function() {
				// event.preventDefault();
				// event.stopPropagation(); //버블링방지..인데 잘 안먹넴...
				if(!confirm("삭제 하시겠습니까?")) {
					return false;
				}
				const $li = $(this).closest("li");
				const rno = $li.data("rno");
				replyService.remove(rno, function(data) {
					alert("삭제 되었습니다");
					$li.remove();
					list(undefined, true);
				});
				return false;
			});
			
			// 댓글 쓰기 버튼 클릭시
			$("#btnWriteReply").click(function() {
				$("#replyModal").find(".modal-footer div button").hide()
				.filter(":eq(0)").show();
				
				$("#replyModal").modal("show");
				$("#replyContent").val(""); // 글쓰기 끝나고 사라짐
				$("#replyWriter").val("${member.id}"); //세션정보 가져오기
			})

			// 댓글 더보기 버튼 클릭시
			$(".btn-more-reply").click(function() {
				const lastRno = $(".replies li:last").data("rno");
				//console.log(lastRno);
				list({lastRno}); // lastRno:lastRno 
			});
			
			
			$(function() {		
				// 댓글 작성(반영) 버튼 클릭시
				/* $("#replyModal").modal("show") */
				$("#btnReplyWriteSubmit").click(function() {
					const writer = $("#replyWriter").val();
					const content = $("#replyContent").val();
					const reply = {pno, writer, content};
					
					replyService.write(reply, function(data){						
						$("#replyModal").modal("hide");
						list(undefined, true);

						
						// location.reload(); //새로고침
					});
				});
				// 댓글 수정(반영) 버튼 클릭시
				$("#btnReplyModifySubmit").click(function() {
					const content = $("#replyContent").val();
					const rno = $("#replyModal").data("rno");
					const reply = {rno, content};
					
					replyService.modify(reply, function(data){						
						$("#replyModal").modal("hide");
						$(`.replies li[data-rno='\${rno}'] p`).text(content);
						list(undefined, true);
						//수정시간까지 변경하고싶으면 db에서 갖고와야됨. 여기선 content는 알고있으니까,,,
						
					});
				});
				// 댓글 삭제(반영) 버튼 클릭시
				$("#btnReplyRemoveSubmit").click(function() {
					const rno = $("#replyModal").data("rno");
					const $li = $(`.replies li[data-rno='\${rno}']`);
					replyService.remove(rno, function(data){						
						$("#replyModal").modal("hide");
						$li.remove();
						list(undefined, true);
					});
				});
			})

		</script>
		<jsp:include page="../common/footer.jsp" />
	</div>
		
	<!-- The Modal -->
	<div class="modal fade" id="replyModal">
		<div class="modal-dialog">
			<div class="modal-content">
	
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Reply</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
		
				<!-- Modal body -->
				<div class="modal-body">
					<label for="replyContent" class="mb-2">Content</label>
					<input type="text" class="form-control mb-3" id="replyContent">
					
					<label for="replyWriter" class="mb-2">Writer</label>
					<input type="text" class="form-control mb-3" id="replyWriter" value="${member.id}">			
				</div>
	
				<!-- Modal footer -->
				<div class="modal-footer">
					<div>
						<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="btnReplyWriteSubmit">Write</button>
						<button type="button" class="btn btn-warning" data-bs-dismiss="modal" id="btnReplyModifySubmit">Modify</button>
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="btnReplyRemoveSubmit">Remove</button>
					</div>
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>					
				</div>
			</div>
		</div>
	</div>
</body>
</html>