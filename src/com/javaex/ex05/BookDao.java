package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

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

	// 책 리스트 가져오기
	public List<BookVo> getBookList() {

		List<BookVo> bookList = new ArrayList<BookVo>();

		this.getConnection();

		String selectAll = "";
		selectAll += " SELECT ";
		selectAll += "     b.book_id, ";
		selectAll += "     b.title, ";
		selectAll += "     b.pubs, ";
		selectAll += "     b.pub_date, ";
		selectAll += "     b.author_id, ";
		selectAll += "     a.author_name, ";
		selectAll += "     a.author_desc ";
		selectAll += " FROM ";
		selectAll += "     book    b, ";
		selectAll += "     author  a ";
		selectAll += " WHERE ";
		selectAll += "     b.author_id = a.author_id ";
		selectAll += " ORDER BY ";
		selectAll += "     b.book_id ASC ";

		try {
			pstmt = conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);

				bookList.add(bookVo);
			}

			// 4.결과처리

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return bookList;
	}

	// 책 수정
	public int bookUpdate(BookVo bookVo) {

		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " UPDATE book ";
			query += " SET ";
			query += "     title = ?, ";
			query += "     pubs = ?, ";
			query += "     pub_date = ?, ";
			query += "     author_id = ? ";
			query += " WHERE ";
			query += "     book_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			pstmt.setInt(5, bookVo.getBookId());
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + bookVo.getBookId() + "번] 책이 수정되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

	// 책 등록
	public int BookInsert(BookVo bookVo) {

		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " INSERT INTO book VALUES ( ";
			query += "     seq_book_id.NEXTVAL, ";
			query += "     ?, ";
			query += "     ?, ";
			query += "     ?, ";
			query += "     ? ";
			query += " ) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + bookVo.getTitle() + "] 책이 등록되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}

	// 책 테이블 생성
	public void creTableBook() {

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " CREATE TABLE book ( ";
			query += "     book_id    NUMBER(10), ";
			query += "     title      VARCHAR2(100) NOT NULL, ";
			query += "     pubs       VARCHAR2(100), ";
			query += "     pub_date   DATE, ";
			query += "     author_id  NUMBER(10), ";
			query += "     PRIMARY KEY ( book_id ), ";
			query += "     CONSTRAINT book_fk FOREIGN KEY ( author_id ) ";
			query += "         REFERENCES author ( author_id ) ";
			query += " ) ";

			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("책 테이블이 생성되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();
	}

	public int bookDelete(BookVo bookVo) {

		int count = -1;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " DELETE FROM book ";
			query += " WHERE ";
			query += "     book_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookVo.getBookId());
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + bookVo.getBookId() + "번] 책이 삭제되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return count;
	}
	
}
