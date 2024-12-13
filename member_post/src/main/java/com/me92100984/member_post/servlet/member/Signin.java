package com.me92100984.member_post.servlet.member;

import java.io.IOException;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.me92100984.member_post.service.MemberService;
import com.me92100984.member_post.service.MemberServiceImpl;


@WebServlet("/signin")
public class Signin extends HttpServlet{
	private MemberService service = new MemberServiceImpl();	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/signin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String remember = req.getParameter("remember-id");
		
		// if 부분 먼저 지우고 체크 먼저 해보기
//		System.out.println(id);
//		System.out.println(pw);
//		System.out.println(remember);
	
//		service.register(member);
		
		//로그인 성공
		if(service.login(id, pw)) {//null 을 캐스팅하면 nullpointexception 되어서 체크해야됨!
			//세션이 로그인 동안 유지되도록(무조건 진행)
			HttpSession session = req.getSession();
			session.setAttribute("member", service.findBy(id));
			
			//쿠키에 아이디 기억 여부 처리
			if(remember != null) {
				Cookie cookie = new Cookie("remember-id",id);
				cookie.setMaxAge(60 * 60 * 24 * 7);
				resp.addCookie(cookie);
			}
			else {
				// 아이디 기억 안할 때 처리할 일
				Cookie[] cookies = req.getCookies();
				for(Cookie c : cookies) {
					if(c.getName().equals("remember-id")) {
						c.setMaxAge(0);
						resp.addCookie(c);
						break;
					}
				}
			}	
			//url 파라미터 여부에 따른 리디렉션 페이지 지정
			String redirectURL = req.getContextPath()+"/index"; 
			//req.getContextPath()+"/" 이것은 프로젝트이름 /member 을 기대하는 것
			
			String url = req.getParameter("url");
			if(url != null && !url.equals("")) {
				redirectURL = URLDecoder.decode(url, "utf-8");
			}
			
			resp.sendRedirect(redirectURL); 
		}
		//로그인 실패
		else {
			resp.sendRedirect("login?msg=fail");
		}
		
	}
}
