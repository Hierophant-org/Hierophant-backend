package com.hierophant.Hierophant.modelTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hierophant.model.AuthRequest;
import com.hierophant.model.Comment;
import com.hierophant.model.Image;
import com.hierophant.model.Post;
import com.hierophant.model.Token;
import com.hierophant.model.User;

public class TokenModelTests {
	static User u1;
	static Image i1;
	static Post p1;
	static Comment c1;
	static Token t = new Token("joel","sdadfnmasdhafj");

	@BeforeAll
	static void beforeAll() {
	}

	@BeforeEach
	void before() {
		u1 = new User(4, "testuser", "testpassword", "emailperson@gmail.com", new ArrayList<Post>(),
				new ArrayList<Comment>());
		i1 = new Image(3, "google.com", "testName", "JSON", "tophat", "cat", null);
		i1 = new Image(3, "google.com", "testName", "JSON", "tophat", "cat", null);
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
	void testGetandSetUsername() {
		assertEquals("joel", t.getUsername());
		t.setUsername("this");
		assertEquals("this",t.getUsername());
	}
	@Test
	void testGetandSetToken() {
		assertEquals("sdadfnmasdhafj", t.getToken());
		t.setToken("sdadsasfcfnmasdhafj");
		assertEquals("sdadsasfcfnmasdhafj",t.getToken());
	}
	@Test
	void testToString() {
		assertEquals("Token(username=joel, token=sdadfnmasdhafj)", t.toString());
	}
	
	@Test
	void testHashCode() {
		assertEquals(-1128164843, t.hashCode());
	}
	
	@Test
	void testEquals() {
		assertTrue(t.equals(t));
	}
}
