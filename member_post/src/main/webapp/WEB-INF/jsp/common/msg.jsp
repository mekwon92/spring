<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="jakarta.tags.core" prefix="c" %>
	${header['Referer']}
	<script>
		
		 alert('${msg}' + '${url}');
		console.log('${url}');
		let url = '${url}';
		// if(url) {
		//   const idx = url.indexOf('?') + 5;
		//   console.log(idx);
		//   url =  url.slice(0, idx) + encodeURIComponent(url.slice(idx+1));
		// }
		// console.log(url);
			<c:choose>
					<c:when test="${not empty url}">
						location.href = url;
					</c:when>

				<c:otherwise>
					history.back();
				</c:otherwise>
			</c:choose>
	</script>