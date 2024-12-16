package com.me92100984.member_post.servlet.post;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.me92100984.member_post.dto.Criteria;
import com.me92100984.member_post.service.PostService;
import com.me92100984.member_post.service.PostServiceImpl;
import com.me92100984.member_post.utils.Commons;

@WebServlet("/post/view")
public class View extends HttpServlet{
// 	private PostService service = new PostServiceImpl();

// 	@Override
// 	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		Criteria cri = new Criteria(req); //수집
		
// 		String pnoStr = req.getParameter("pno");
// //		Long pno = pnoStr == null ? 1L : Long.valueOf(pnoStr);
		
// 		if(pnoStr == null) {
// 			Commons.printMsg("비정상적인 접근입니다", "list", resp);
// 			return;
// 		}
// 		Long pno = Long.valueOf(pnoStr);
		
// 		req.setAttribute("post", service.view(pno));
// 		req.setAttribute("cri", cri); //사용하기위한..
// 		req.getRequestDispatcher("/WEB-INF/jsp/post/view.jsp").forward(req, resp);
		
// 		//리디렉션하는 방법도 있음
// 	}
	
}
