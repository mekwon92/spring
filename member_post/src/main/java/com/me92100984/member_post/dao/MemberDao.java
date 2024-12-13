package com.me92100984.member_post.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.me92100984.member_post.vo.Member;

import com.me92100984.member_post.utils.DBConn;
//V0 : Value Object
//DAO : DB Access Object

//mariadb maven 필요함 3.3.3(완전 최신보다는 사용율 높은거 사용) jar를 WEB-INF/lib 밑에 넣기
public class MemberDao {
	
	//CRUD
	public int insert(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
//		org.mariadb.jdbc.Driver
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
//			String sql = "insert into tbl_member (id, pw, name) values ('" + member.getId()
//			+ "', '"+ member.getPw()+"', '" + member.getName() + "')";//세미콜론 빼야함. 이 방식은 sql 인젝션에 취약			

// PreparedStatement - 깔끔하고 보안상 안전함. 미리 파라미터화하여 공격 가능성을 차단. 유지보수하기도 좋음
			String sql = "insert into tbl_member (id, pw, name, email, road_addr, detail_addr) values (?,?,?,?,?,?)";
			
			//1. connection 객체 취득
	//utils conn = DriverManager.getConnection("jdbc:mariadb://54.180.26.240:3306/post", "sample", "1234");
			conn = DBConn.getConnection();
			
			//2. 문장생성, 파라미터 지정
//			Statement stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, member.getId());
			pstmt.setString(idx++, member.getPw());
			pstmt.setString(idx++, member.getName());
			pstmt.setString(idx++, member.getEmail());
			pstmt.setString(idx++, member.getRoadAddr());
			pstmt.setString(idx++, member.getDetailAddr());
		
			//3. 문장 실행(int는 반영된 행의 개수를 반환)
//			return stmt.executeUpdate(sql);
			return pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) { //예외처리가 요구됨
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {}
		}
		
		
		return 0;
	}
	public static void main(String[] args) {
		MemberDao dao = new MemberDao();
//		int result = dao.insert(Member.builder().id("cccc").pw("123333").name("권밍").build());
//		System.out.println(result); //원래는 전용 테스트 도구를 사용해야함(편의상)
		//웹이 아니라 자바에서 실행해야함
		
		Member m = dao.selectOne("hello");
		System.out.println(m);
	}
	
	// 싱글턴. 인스턴스를 한번만 만든다. 여기서 싱글턴 만들기만하고 사용은 안함
	// static:로드 시에 생성 . 프로그램 종료시 사라짐
	// 계속 생성의 목적이 아니라 재활용하는 것
	private static final MemberDao dao = new MemberDao(); //final을 붙이면 setter도 불가능
	
	public static MemberDao getInstance() {	
		return dao;
	}
	
	//생성자를 private해서 객체 생성을 하지 못하게 막음
	private MemberDao() {}
	
	public Member selectOne(String id) {
		Member member = null;
		String sql = "select * from tbl_member where id = ?";
		try(Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { // 다음 커서가 존재하는가..?
				member = Member.builder()
						.id(rs.getString("id"))
						.pw(rs.getString("pw"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.roadAddr(rs.getString("road_addr"))
						.detailAddr(rs.getString("detail_addr"))
						.regdate(rs.getDate("regdate"))
						.build();
			}
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	
}
