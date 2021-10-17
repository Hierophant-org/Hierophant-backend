package com.hierophant.Hierophant.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.hierophant.controller.CommentController;
import com.hierophant.model.Comment;
import com.hierophant.model.Image;
import com.hierophant.model.Post;
import com.hierophant.model.User;
import com.hierophant.service.CommentService;

public class CommentControllerTests {
	static CommentController comCon = new CommentController();
	static CommentService mockComServ = mock(CommentService.class);
	static User u1;
	static Image i1;
	static Post p1;
	static Comment c1;

	@BeforeAll
	static void beforeAll() {
		comCon.commentService = mockComServ;
	}

	@BeforeEach
	void before() {
		u1 = new User(4, "testuser", "testpassword", "emailperson@gmail.com", new ArrayList<Post>(),
				new ArrayList<Comment>());
		i1 = new Image(3, "title", "google.com", "testName", "tophat", "cat", null);
		p1 = new Post(2, "testTitle", u1, i1, new ArrayList<Comment>(), 42);
		c1 = new Comment(1, u1, p1, "Good soup meme", 0);
	}

	@AfterEach
	public void after() {
		comCon.commentService = mockComServ;
		u1 = null;
		i1 = null;
		p1 = null;
		c1 = null;
	}

	@Test
	void testFindByIdFinds() {
		int comId = 1;
		Optional<Comment> oC = Optional.of(new Comment());
		when(mockComServ.findById(comId)).thenReturn(oC);

		assertEquals(ResponseEntity.ok(oC), comCon.findById(comId));
	}

	@Test
	void testFindByPostIdFinds() {
		int postId = p1.getPostId();
		List<Comment> list = new ArrayList<>();
		list.add(c1);
		when(mockComServ.findByPostId(postId)).thenReturn(list);

		assertEquals(ResponseEntity.ok(list), comCon.findByPostId(postId));
	}

	@Test
	void testFindUserFinds() {
		int postId = p1.getPostId();
		Optional<User> oC = Optional.of(u1);
		when(mockComServ.findUserByCommentId(postId)).thenReturn(oC);

		assertEquals(ResponseEntity.ok(oC), comCon.findAllUsers(postId));
	}

	@Test
	void testFindByUserIdFinds() {
		int id = u1.getUserId();
		List<Comment> list = new ArrayList<>();
		list.add(c1);
		when(mockComServ.findByUserId(id)).thenReturn(list);

		assertEquals(ResponseEntity.ok(list), comCon.findByUserId(id));
	}

	@Test
	void testInsert() {
		when(mockComServ.insert(c1)).thenReturn(c1);

		assertEquals(ResponseEntity.ok(c1), comCon.insert(c1));
	}

	@Test
	void testUpdate() {
		when(mockComServ.update(c1)).thenReturn(c1);
		assertEquals(ResponseEntity.ok(c1), comCon.update(c1));
	}

	@Test
	void testDelete() {
		assertEquals(ResponseEntity.noContent().build(), comCon.deleteById(c1.getComId()));
		verify(mockComServ).deleteById(c1.getComId());
	}
}
