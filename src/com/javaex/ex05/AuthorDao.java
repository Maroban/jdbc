package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver"; // 드라이버
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // IP주소와 포트번호
	private String id = "webdb"; // SQL 계정 이름
	private String pw = "webdb"; // SQL 계정 비밀번호

	// DB 연결
	private void getConnection() {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원 정리
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 작가 등록
	public int authorInsert(BookVo bookVo) {

		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " INSERT INTO author VALUES ( ";
			query += "     seq_author_id.NEXTVAL, ";
			query += "     ?, ";
			query += "     ? ";
			query += " ) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getAuthorName());
			pstmt.setString(2, bookVo.getAuthorDesc());

			count = pstmt.executeUpdate();

			// 4.결과처리

			System.out.println("[" + bookVo.getAuthorName() + "] 작가가 등록되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

	// 작가 테이블 생성
	public void creTableAuthor() {

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " CREATE TABLE author ( ";
			query += "     author_id    NUMBER(10), ";
			query += "     author_name  VARCHAR2(100) NOT NULL, ";
			query += "     author_desc  VARCHAR2(100), ";
			query += "     PRIMARY KEY ( author_id ) ";
			query += " ) ";

			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();

			// 4.결과처리

			System.out.println("작가 테이블이 생성되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();
	}

}
