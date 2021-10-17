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

import com.hierophant.controller.PostController;
import com.hierophant.model.Comment;
import com.hierophant.model.Image;
import com.hierophant.model.Post;
import com.hierophant.model.User;
import com.hierophant.service.PostService;

public class PostControllerTests {
	static PostController postCon = new PostController();
	static PostService mockPostServ = mock(PostService.class);
	static User u1;
	static Image i1;
	static Post p1;
	static Comment c1;

	@BeforeAll
	static void beforeAll() {
		postCon.postService = mockPostServ;
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
		postCon.postService = mockPostServ;
		u1 = null;
		i1 = null;
		p1 = null;
		c1 = null;
	}
	@Test
	void testFindByTitleFinds() {
		List<Post> list = new ArrayList<>();
		list.add(p1);
		Optional<Post> oC = Optional.of(p1);
		when(mockPostServ.findByTitle(p1.getTitle())).thenReturn(oC);
		assertEquals(ResponseEntity.ok(oC), postCon.findByTitle(p1.getTitle()));
	}
	@Test
	void testFindById() {
		int postId = p1.getPostId();
		Optional<Post> oC = Optional.of(p1);
		when(mockPostServ.findById(postId)).thenReturn(oC);
		assertEquals(ResponseEntity.ok(oC), postCon.findById(postId));
	}

	@Test
	void testFindByPostId() {
		int postId = p1.getPostId();
		Optional<Post> oC = Optional.of(p1);
		when(mockPostServ.findById(postId)).thenReturn(oC);
		assertEquals(ResponseEntity.ok(oC), postCon.findById(postId));
	}
	@Test
	void testFindAll() {
		List<Post> list = new ArrayList<>();
		Post p2 = new Post(5, "testTitle5", u1, i1, new ArrayList<Comment>(), 42);
		Post p3 = new Post(6, "testTitle6", u1, i1, new ArrayList<Comment>(), 24);
		Post p4 = new Post(7, "testTitle7", u1, i1, new ArrayList<Comment>(), 15);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		when(mockPostServ.findAll()).thenReturn(list);
		assertEquals(ResponseEntity.ok(list), postCon.findAll());
	}
	@Test
	void testFindPoster() {
		when(mockPostServ.findUserByPost(p1)).thenReturn(u1);
		assertEquals(ResponseEntity.ok(u1), postCon.findPoster(p1));
	}

	@Test
	void testFindByUserId() {
		int id = u1.getUserId();
		List<Post> list = new ArrayList<>();
		Post p2 = new Post(5, "testTitle5", u1, i1, new ArrayList<Comment>(), 42);
		Post p3 = new Post(6, "testTitle6", u1, i1, new ArrayList<Comment>(), 24);
		Post p4 = new Post(7, "testTitle7", u1, i1, new ArrayList<Comment>(), 15);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		when(mockPostServ.findByUserId(id)).thenReturn(list);
		assertEquals(ResponseEntity.ok(list), postCon.findByUserId(id));
	}

	@Test
	void testInsert() {
		when(mockPostServ.insert(p1)).thenReturn(p1);
		assertEquals(ResponseEntity.ok(p1), postCon.insert(p1));
	}

	@Test
	void testUpdate() {
		when(mockPostServ.update(p1)).thenReturn(p1);
		assertEquals(ResponseEntity.ok(p1), postCon.update(p1));
	}

	@Test
	void testDelete() {
		assertEquals(ResponseEntity.noContent().build(), postCon.deleteById(p1.getPostId()));
		verify(mockPostServ).deleteById(p1.getPostId());
	}
}
