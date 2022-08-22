package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BoardDao;
import com.example.demo.entity.Board;
//Junit은 단위(Unit) 테스트 도구 - 메소드의 동작을 확인
@SpringBootTest
public class BoardDaoTest {
	@Autowired
	private BoardDao boardDao;
	
	//Test 케이스 1. dao 생성 -> null 이 아님
	//@Test
	public void initTest() {
		assertNotNull(boardDao);
	}
	
	// insert, delete, update 의 실행 결과는 변경된 행의 개수
	//Test 케이스 2. save : Board -> 결과값이 1이다
	//Transactional : 일련의 sql을 모아서 하나의 작업으로 지정
	//					함께 commit 되거나 rollback 되어야 한다(일부분만 동작할 수 어ㅗㅄ다)
//	@Transactional
//	@Test
	public void saveTest() {
		Board board = Board.builder().title("xxx").content("yyy").writer("summer").build();
		assertEquals(1, boardDao.save(board));
	}
	//Test 케이스 3.count : count -> 개수를 수동으로 확인 해서 assert한다
//	@Test
	public void countTest1() {
		assertEquals(10, boardDao.count(null));
	}
	
	//Test 케이스 4.findAll : 글이 14개 있다 . 11~ 14까지 4개를 읽어오잔
	//@Test
	public void findAllTest1() {
		assertEquals(4, boardDao.findAll(null, 11, 14).size());
	}
	//Test 케이스 5.(내용,제목) 조회수, 좋아요ㅡ 싫어요에ㅜ 대해 값을 주며뉴 update -> 결과가 0이 아니다
//	@Test
	public void updateTest1() {
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).readCnt(1).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).goodCnt(1).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).commentCnt(15).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).title("변경").content("변경").build()));
	}
	
	//바람직 하지 않은 결과도 테스트 해야한다
	// Test 케이스 6. 1번글을 읽으면 비어있다, 2번글을 읽으면 존재한다
	public void findByIdTest1() {
		assertEquals(true, boardDao.findById(1).isEmpty());
		assertEquals(true, boardDao.findById(2).isPresent());
	}
	
	// Test 케이스 7. 1번글은 없다, 2번글의 글쓴이는 있다
		public void findWriterTest1() {
			assertEquals(true, boardDao.findWriterById(1).isEmpty());
			assertEquals(true, boardDao.findWriterById(2).isPresent());
		}
		//Test 케이스 7. 1번글은 없다, 2번글의 글쓴이는 있다
		@Transactional
		@Test
		public void deleteByIdTest1() {
			assertEquals(0, boardDao.deleteById(1));
			assertEquals(1, boardDao.deleteById(2));
		}
		
	
	@Transactional
	//@Test
	public void diTest() {
		assertNotNull(boardDao);
	}
	
	//@Test
	public void countTest() {
		assertEquals(0, boardDao.count(null));
		assertEquals(0, boardDao.count("spring"));
	}
	
	//@Test
	public void findAllTest() {
		boardDao.findAll(null, 1, 10);
		boardDao.findAll("spring", 11, 20);
	}
	
	//@Test
	public void saveTest1() {
		Board board = Board.builder().title("aaaa").content("bbb").writer("spring").build();
		assertEquals(1, boardDao.save(board));
	}
	
	//@Test
	public void updateTest() {
		boardDao.update(Board.builder().bno(1).readCnt(1).build());
		boardDao.update(Board.builder().bno(1).goodCnt(1).build());
		boardDao.update(Board.builder().bno(1).commentCnt(15).build());
		boardDao.update(Board.builder().bno(1).title("변경").content("변경").build());
	}
	
	//@Test
	public void findByIdTest() {
		System.out.println(boardDao.findById(1));
	}
	
	//@Test
	public void findWriterTest() {
		System.out.println(boardDao.findWriterById(1));
	}
	
//	@Transactional
//	@Test
	public void deleteByIdTest() {
		assertEquals(1, boardDao.deleteById(1));
	}
}
