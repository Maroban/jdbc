package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsertApp {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null; select문을 사용하지 않아서 주석 처리한 것

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = ""; // 공백으로 넣은 이유는 알아보기 쉬우라고 넣은 것.
			query += " INSERT INTO author ";
			query += " VALUES (seq_author_id.NEXTVAL, ?, ?) ";

			System.out.println(query);

			// SQL문 바인딩
			pstmt = conn.prepareStatement(query); // query 안에 있는 문자열을 쿼리문으로 만드는 것.
			pstmt.setString(1, "가나다"); // 1번 ? 값에 "김영하"를 넣는 것.
			pstmt.setString(2, "라마바사아자"); // 2번 ? 값에 "알쓸신잡"를 넣는 것.

			// SQL문 실행
			int count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
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

	}

}
