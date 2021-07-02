package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;

		// DB에서 데이터 가져오기
		authorList = authorDao.getAuthorList(); // DB에서 작가 정보 리스트를 가져온다.
		
		// 리스트 메소드로 출력
		printList(authorList);
		

		/* 
		- 리스트를 for문으로 출력 - 1번 방법
		for (int i = 0; i < authorList.size(); i++) {
			int authorId = authorList.get(i).getAuthorId();
			String authorName = authorList.get(i).getAuthorName();
			String authorDesc = authorList.get(i).getAuthorDesc();

			System.out.println(authorId + "\t" + authorName + "\t" + authorDesc);
		}
		
		
		
		- 리스트를 for문으로 출력 - 2번 방법
		for (int i = 0; i < authorList.size(); i++) {

			AuthorVo authorVo = authorList.get(i);

			int authorId = authorVo.getAuthorId();
			String authorName = authorVo.getAuthorName();
			String authorDesc = authorVo.getAuthorDesc();

			System.out.println(authorId + "\t" + authorName + "\t" + authorDesc);
		}
		
		- 리스트를 for문으로 출력 - 3번 방법
		for (int i = 0; i < authorList.size(); i++) {

			AuthorVo authorVo = authorList.get(i);

			System.out.println(
					authorVo.getAuthorId() + "\t" + authorVo.getAuthorName() + "\t" + authorVo.getAuthorDesc());
		}
		*/
		
		
		
		// 작가 등록
		int iCount = authorDao.authorInsert("정우성", "정우성 어록"); // DB에 작가 정보 Insert
		
		if (iCount > 0) {
			System.out.println("[등록되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의히세요.(" + iCount + ")]");
		}
		

		// 리스트 출력
		authorList = authorDao.getAuthorList(); // DB에서 작가 정보 리스트를 가져온다.

		// 리스트를 메소드로 출력
		printList(authorList);
		
		
		// 작가 수정
		int uCount = authorDao.authorUpdate(7, "이정재", "내가 왕이될 상인가"); // 3번 데이터 수정

		if (uCount > 0) {
			System.out.println("[수정되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의히세요.(" + uCount + ")]");
		}

		
		// 리스트 출력
		authorList = authorDao.getAuthorList(); // DB에서 작가 정보 리스트를 가져온다.

		// 리스트를 메소드로 출력
		printList(authorList);

		// 작가 삭제
		authorDao.authorDelete(7); // 3번 데이터 삭제

		// 리스트 출력
		authorList = authorDao.getAuthorList(); // DB에서 작가 정보 리스트를 가져온다.

		// 리스트를 메소드로 출력
		printList(authorList);
	}
	
	public static void printList(List<AuthorVo> authorList) {
		for (int i = 0; i < authorList.size(); i++) {

			AuthorVo authorVo = authorList.get(i);

			int authorId = authorVo.getAuthorId();
			String authorName = authorVo.getAuthorName();
			String authorDesc = authorVo.getAuthorDesc();

			System.out.println(authorId + "\t" + authorName + "\t" + authorDesc);
		}
	}
	
	
}
