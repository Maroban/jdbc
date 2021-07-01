package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelectApp {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String select = "";
			select += " SELECT ";
			select += "     author_id, ";
			select += "     author_name, ";
			select += "     author_desc ";
			select += " FROM ";
			select += "		author ";

			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) { // 한칸 아래로 이동
				int authorId = rs.getInt("author_id"); // 컬럼명인 "author_id" 값 출력
				String authorName = rs.getString("author_name"); // 컬럼명인 "author_name" 값 출력
				String authorDesc = rs.getString("author_desc"); // 컬럼명인 "author_desc" 값 출력

				System.out.println(authorId + ", " + authorName + ", " + authorDesc);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
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

	}

}
