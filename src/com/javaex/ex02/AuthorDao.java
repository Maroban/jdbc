package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 필드

	// 생성자

	// 메소드 - GS

	// 메소드 - 일반
	
	// 작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() {

		// DB 값을 가져와서 ArrayList로 저장
		List<AuthorVo> authorList = new ArrayList<AuthorVo>(); // 리스트 선언

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String select = "";
			select += " SELECT ";
			select += "     author_id, ";
			select += "     author_name, ";
			select += "     author_desc ";
			select += " FROM ";
			select += "		author ";
			select += " ORDER BY ";
			select += "		author_id ASC ";

			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				// DB값을 authorVo에 저장
				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);

				// authorVo 값을 리스트에 add
				authorList.add(authorVo);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

		return authorList;
	}

	// 작가 삭제하기
	public int authorDelete(int authorId) {

		// 쿼리문이 잘못됐을 때 -1
		// 데이터 값이 잘못됐을 때 0
		// 실행됐을 때 1이 뜬다.
		int count = -1;  

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String delete = "";
			delete += " DELETE FROM author ";
			delete += " WHERE ";
			delete += "     author_id = ? ";

			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

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

		return count;
	}

	// 작가 수정하기
	public int authorUpdate(int authorId, String authorName, String authorDecs) {

		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String update = "";
			update += " UPDATE author ";
			update += " SET ";
			update += "     author_name = ?, ";
			update += "     author_desc = ? ";
			update += " WHERE ";
			update += "     author_id = ? ";

			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDecs);
			pstmt.setInt(3, authorId);

			count = pstmt.executeUpdate();

			// 4.결과처리

			System.out.println(count + "건 수정");

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

		return count;
	}

	// 작가 등록하기
	public int authorInsert(String authorName, String authorDecs) {

		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String insert = "";
			insert += " INSERT INTO author VALUES ( ";
			insert += "     seq_author_id.NEXTVAL, ";
			insert += "     ?, ";
			insert += "     ? ";
			insert += " ) ";

			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, authorName); // 받은 값 넣기
			pstmt.setString(2, authorDecs); // 받은 값 넣기

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

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

		return count; // 성공한 데이터 개수를 리턴한다.
	}

}
