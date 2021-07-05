package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDao {

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

	// 책 테이블 삭제
	public int dropTableBook() {

		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " DROP TABLE book ";

			pstmt = conn.prepareStatement(query);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("책 테이블이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

	// 작가 테이블 삭제
	public int dropTableAuthor() {
		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " DROP TABLE author ";

			pstmt = conn.prepareStatement(query);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("작가 테이블이 삭제되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

	// 책 시퀀스 삭제
	public int dropSeqBook() {
		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " DROP SEQUENCE seq_book_id ";

			pstmt = conn.prepareStatement(query);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("책 시퀀스가 삭제되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

	// 작가 시퀀스 삭제
	public int dropSeqAuthor() {
		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " DROP SEQUENCE seq_author_id ";

			pstmt = conn.prepareStatement(query);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("작가 시퀀스가 삭제되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

	// 책 시퀀스 생성
	public int creSeqBook() {
		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " CREATE SEQUENCE seq_author_id INCREMENT BY 1 START WITH 1 NOCACHE ";

			pstmt = conn.prepareStatement(query);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("책 시퀀스가 생성되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

	// 작가 시퀀스 생성
	public int creSeqAuthor() {
		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " CREATE SEQUENCE seq_book_id INCREMENT BY 1 START WITH 1 NOCACHE ";

			pstmt = conn.prepareStatement(query);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("작가 시퀀스가 생성되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

}
