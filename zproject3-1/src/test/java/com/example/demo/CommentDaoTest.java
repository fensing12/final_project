package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CommentDao;
import com.example.demo.entity.Comment;

@SpringBootTest
public class CommentDaoTest {
	@Autowired
	private CommentDao dao;
	
	
	//findWritertByBno 테스트
	@Test
	public void findWriterByBnoTest() {
		assertEquals(true, commentDao.findWritertById(1500).isEmpty());
		assertEquals(true, commentDao.findWritertById(2).isPresent());
	}
	
	//deleteByCno 테스트
	@Transactional
	public void deleteByCnoTest() {
		
	}
	
	//@Test
	public void saveTest() {
		dao.save(Comment.builder().bno(1).content("zzz")
			.writer("winter").build());
	}
	
	//@Test
	public void findByBnoTest() {
		dao.findByBno(2);
	}
	
	//@Test
	public void findWriterTEst() {
		assertEquals("winter", dao.findWriterById(2).get());
	}
	
	@Transactional
	//@Test
	public void deleteByCnoTest() {
		assertEquals(1, dao.deleteByCno(2));
	}
	
	@Transactional
	@Test
	public void deleteByBnoTest() {
		assertNotEquals(0, dao.deleteByBno(1));
	}
}
















