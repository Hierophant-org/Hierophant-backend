package com.hierophant.Hierophant.modelTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

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

public class UserModelTests {
	static User u1;
	static Image i1;
	static Post p1;
	static Comment c1;

	@BeforeAll
	static void beforeAll() {
	}

	@BeforeEach
	void before() {
		u1 = new User(4, "testuser", "testpassword", "emailperson@gmail.com", new ArrayList<Post>(),
				new ArrayList<Comment>());
		i1 = new Image(3, "title", "google.com", "testName", "tophat", "cat", null);
		p1 = new Post(2, "testTitle", u1, i1, new ArrayList<Comment>(), 42);
		c1 = new Comment(1, u1, p1, "Good soup meme", 14);
	}

	@AfterEach
	public void after() {
		u1 = null;
		i1 = null;
		p1 = null;
		c1 = null;
	}

	@Test
	void testToString() {
		assertEquals("User(userId=4, username=testuser, password=testpassword, email=emailperson@gmail.com, posts=[], comments=[])", u1.toString());
	}

	@Test
	void testHashCode() {
		assertEquals(979153047, u1.hashCode());
	}
	
	@Test
	void testHashCode_inccorect() {
		assertNotEquals(34567894, u1.hashCode());
	}

	@Test
	void testEquals() {
		assertTrue(u1.equals(u1));
	}
	@Test
	void testEquals_False_later() {
		User u2 = u1;
		u1.setEmail("this@email.com");
		assertTrue(u1.equals(u2));
	}
	@Test
	void testEquals_False() {
		User u2 = new User(11, "nottestuser", "testdpassword", "emaisdlperson@gmail.com", null ,null);
		assertFalse(u1.equals(u2));
	}
	@Test
	void testGetandSetId() {
		assertEquals(4, u1.getUserId());
		u1.setUserId(5);
		assertEquals(5, u1.getUserId());
	}

	@Test
	void testGetandSetUsername() {
		assertEquals("testuser", u1.getUsername());
		u1.setUsername("testuser2");
		assertEquals("testuser2", u1.getUsername());
	}

	@Test
	void testGetandSetComments() {
		assertEquals(new ArrayList<Comment>(), u1.getComments());
		ArrayList<Comment> a = new ArrayList<Comment>();
		a.add(c1);
		u1.setComments(a);
		assertEquals(a, u1.getComments());
	}

	@Test
	void testGetandSetEmail() {
		assertEquals("emailperson@gmail.com", u1.getEmail());
		u1.setEmail("person@gmail.com");
		assertEquals("person@gmail.com", u1.getEmail());
	}

	@Test
	void testGetandSetPassword() {
		assertEquals("testpassword", u1.getPassword());
		u1.setPassword("noTestpassword");
		assertEquals("noTestpassword", u1.getPassword());
	}

	@Test
	void testGetandSetPosts() {
		assertEquals(new ArrayList<Post>(), u1.getPosts());
		ArrayList<Post> p = new ArrayList<Post>();
		p.add(p1);
		u1.setPosts(p);
		assertEquals(p, u1.getPosts());
	}
}
