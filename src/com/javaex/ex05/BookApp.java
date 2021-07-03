package com.javaex.ex05;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		// DB에서 Author 테이블, Book 테이블 완성 (sql)
		// Author 테이블, Book 테이블 시퀀스 완성 (sql)
		
		// 0. 사전준비
		BookDao bookDao = new BookDao();  // BookDao 호출
		List<BookVo> bookVo;  // 리스트 생성
		
		// 1. AuthorDao.authorInsert(); 작가 데이터 전체 추가	
		
		// 2. BookDao.bookInsert(); 책 데이터 1개 추가
		
		// 3. BookDao.bookUpdate(); 책 데이터 1개 수정
		
		// 4. BookDao.bookDelete(); 책 데이터 1개 삭제
		
		// 5. 책 리스트 출력
		
        /////////////////////////////////////////////////////////////////////////////////////////
		
		// 1. Scanner를 통해서 키워드 검색 (like문 사용)
		// "검색어를 입력해주세요"
		// "검색어: 문"
		
		// BookDao.getBookList("문"); -> 메소드 오버로딩 사용
		// 검색된 책 정보 출력
		
		
	}

}
