package com.javaex.ex05;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {

		// 0. 사전준비
		BookDao bookDao = new BookDao();
		TableDao tableDao = new TableDao();
		AuthorDao authorDao = new AuthorDao();
		Scanner sc = new Scanner(System.in);
		List<BookVo> bookList; 

		// 테이블 삭제
		tableDao.dropTableBook();
		tableDao.dropTableAuthor();
		System.out.println("");

		// 시퀀스 삭제
		tableDao.dropSeqBook();
		tableDao.dropSeqAuthor();
		System.out.println("");

		// 시퀀스 생성
		tableDao.creSeqAuthor();
		tableDao.creSeqBook();
		System.out.println("");

		// 작가 테이블 생성
		authorDao.creTableAuthor();
		System.out.println("");

		// 작가 테이블 값 입력
		BookVo iAuthorVo_1 = new BookVo("이문열", "경북 영양");
		authorDao.authorInsert(iAuthorVo_1);

		BookVo iAuthorVo_2 = new BookVo("박경리", "경상남도 통영");
		authorDao.authorInsert(iAuthorVo_2);

		BookVo iAuthorVo_3 = new BookVo("이고잉", "오잉??");
		authorDao.authorInsert(iAuthorVo_3);

		BookVo iAuthorVo_4 = new BookVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(iAuthorVo_4);

		BookVo iAuthorVo_5 = new BookVo("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert(iAuthorVo_5);

		BookVo iAuthorVo_6 = new BookVo("김영하", "알쓸신잡");
		authorDao.authorInsert(iAuthorVo_6);
		System.out.println("");

		// 책 테이블 생성
		bookDao.creTableBook();
		System.out.println("");

		// 책 테이블 값 입력
		BookVo iBookVo_1 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.BookInsert(iBookVo_1);

		BookVo iBookVo_2 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.BookInsert(iBookVo_2);

		BookVo iBookVo_3 = new BookVo("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.BookInsert(iBookVo_3);

		BookVo iBookVo_4 = new BookVo("자바프로그래밍 입문", "위키북스", "2015-04-01", 3);
		bookDao.BookInsert(iBookVo_4);

		BookVo iBookVo_5 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.BookInsert(iBookVo_5);

		BookVo iBookVo_6 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.BookInsert(iBookVo_6);

		BookVo iBookVo_7 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.BookInsert(iBookVo_7);

		BookVo iBookVo_8 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.BookInsert(iBookVo_8);
		System.out.println("");

		// 책 데이터 1개 수정
		BookVo uBookVo = new BookVo("잘생긴게 최고야", "정우성 어록", "2012-12-12", 3, 8);
		bookDao.bookUpdate(uBookVo);
		System.out.println("");

		// 책 데이터 1개 삭제
		BookVo dBookVo = new BookVo(4);
		bookDao.bookDelete(dBookVo);
		System.out.println("");
		
		// 테이블 출력
		bookList = bookDao.getBookList();
		printList(bookList);
		
		

		// Scanner를 통해서 키워드 검색 (like문 사용) // "검색어를 입력해주세요" // "검색어: 문"
		System.out.println();
		System.out.println("검색어를 입력해주세요. ");
		System.out.print("검색어 > ");
		String keyword = sc.nextLine();

		printList(bookDao.search(keyword));
		System.out.println();
		// BookDao.getBookList("문"); -> 메소드 오버로딩 사용 // 검색된 책 정보 출력

		sc.close();
	}

	public static void printList(List<BookVo> bookList) {
		for (int i = 0; i < bookList.size(); i++) {

			BookVo bookVo = bookList.get(i);

			int bookId = bookVo.getBookId();
			String title = bookVo.getTitle();
			String pubs = bookVo.getPubs();
			String pubDate = bookVo.getPubDate();
			int authorId = bookVo.getAuthorId();
			String authorName = bookVo.getAuthorName();
			String authorDesc = bookVo.getAuthorDesc();

			System.out.println(bookId + ", " + title + ", " + pubs + ", " + pubDate + ", " + authorId + ", "
					+ authorName + ", " + authorDesc);
		}
	}
}
