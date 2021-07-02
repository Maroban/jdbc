package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자

	// 메소드 - GS

	// 메소드 - 일반

	// DB 연결
	private void getConnection() { // private인 이유는 메인클래스에서 사용하지 않기 때문이다.
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원 정리
	private void close() {  // private인 이유는 메인클래스에서 사용하지 않기 때문이다.
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

	// 작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() {

		// DB 값을 가져와서 ArrayList로 저장
		List<AuthorVo> authorList = new ArrayList<AuthorVo>(); // 리스트 선언

		this.getConnection();

		String select = "";
		select += " SELECT ";
		select += "     author_id, ";
		select += "     author_name, ";
		select += "     author_desc ";
		select += " FROM ";
		select += "		author ";
		select += " ORDER BY ";
		select += "		author_id ASC ";

		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5. 자원정리
		this.close();

		return authorList;
	}

	// 작가 삭제하기
	public int authorDelete(int authorId) {

		// 쿼리문이 잘못됐을 때 -1
		// 데이터 값이 잘못됐을 때 0
		// 실행됐을 때 1이 뜬다.
		int count = -1;

		this.getConnection();

		try {
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5. 자원정리
		this.close();

		return count;

	}

	// 작가 수정하기
	public int authorUpdate(AuthorVo autorVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String update = "";
			update += " UPDATE author ";
			update += " SET ";
			update += "     author_name = ?, ";
			update += "     author_desc = ? ";
			update += " WHERE ";
			update += "     author_id = ? ";

			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, autorVo.getAuthorName());
			pstmt.setString(2, autorVo.getAuthorDesc());
			pstmt.setInt(3, autorVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리

			System.out.println(count + "건 수정");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();

		return count;
	}

	// 작가 등록하기
	public int authorInsert(AuthorVo authorVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String insert = "";
			insert += " INSERT INTO author VALUES ( ";
			insert += "     seq_author_id.NEXTVAL, ";
			insert += "     ?, ";
			insert += "     ? ";
			insert += " ) ";

			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, authorVo.getAuthorName()); // 받은 값 넣기
			pstmt.setString(2, authorVo.getAuthorDesc()); // 받은 값 넣기

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();

		return count; // 성공한 데이터 개수를 리턴한다.
	}

}
