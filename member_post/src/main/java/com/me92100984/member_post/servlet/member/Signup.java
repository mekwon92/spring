package com.me92100984.member_post.servlet.member;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.me92100984.member_post.service.MemberService;
import com.me92100984.member_post.service.MemberServiceImpl;
import com.me92100984.member_post.vo.Member;

//서블릿은 흐름처리, 유효성 제어만 한다.

@WebServlet("/signup")
public class Signup extends HttpServlet {
	// dao를 쓰다가 나중에는 마이마티스를 사용?
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// jsp가 외부에 노출되지않도록 파일을 옮김(WEB-INF 폴더) - forward 개념
		req.getRequestDispatcher("/WEB-INF/jsp/member/signup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 외부는 바이트기반의 세계라 1byte씩 끊어와서 한글이 깨짐. iso-8859-1(약칭:latin-1)
		// utf-8 : 한글 3byte 영문 1byte
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String roadAddr = req.getParameter("roadAddr");
		String detailAddr = req.getParameter("detailAddr");

//		Member member = new Member();
//		member.setId(id);

		// 디자인패턴 - 빌더 일부초기화를 할때 유용하게 사용됨
		Member member = Member.builder().id(id).pw(pw).name(name).email(email).roadAddr(roadAddr).detailAddr(detailAddr)
				.build();

		System.out.println(member);

		service.register(member);

		// doget으로 보냄
		resp.sendRedirect("/signin");
	}
}
