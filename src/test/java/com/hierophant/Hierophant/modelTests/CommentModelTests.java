package com.hierophant.Hierophant.modelTests;

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
import com.hierophant.model.AuthRequest;
import com.hierophant.model.Comment;
import com.hierophant.model.Image;
import com.hierophant.model.Post;
import com.hierophant.model.User;
import com.hierophant.service.CommentService;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CommentModelTests {
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
	void testGetandSetId() {
		assertEquals(1, c1.getComId());
		c1.setComId(5);
		assertEquals(5,c1.getComId());
	}
	@Test
	void testGetandSetUsername() {
		assertEquals("Good soup meme", c1.getCommText());
		c1.setCommText("Bad soup meme");
		assertEquals("Bad soup meme",c1.getCommText());
	}
	@Test
	void testGetandSetPost() {
		assertEquals(p1, c1.getPostId());
		Post p2 = new Post(10, "testTitle", u1, i1, new ArrayList<Comment>(), 42);
		c1.setPostId(p2);
		assertEquals(p2,c1.getPostId());
	}
	@Test
	void testGetandSetUser() {
		assertEquals(u1, c1.getUserId());
		User u2 = new User(40, "testuser", "testpassword", "emailperson@gmail.com", new ArrayList<Post>(),
				new ArrayList<Comment>());;
		c1.setUserId(u2);
		assertEquals(u2,c1.getUserId());
	}
	@Test
	void testGetandSetUpvotes() {
		assertEquals(14, c1.getUpvotes());
		c1.setUpvotes(21);
		assertEquals(21,c1.getUpvotes());
	}
	@Test
	void testToString() {
		assertEquals("Comment(comId=1, userId=User(userId=4, username=testuser, password=testpassword, email=emailperson@gmail.com, posts=[], comments=[]), postId=Post(postId=2, title=testTitle, userId=User(userId=4, username=testuser, password=testpassword, email=emailperson@gmail.com, posts=[], comments=[]), image=Image(imageId=3, imgHtml=title, name=google.com, type=testName, topText=tophat, bottomText=cat, pic=null), comments=[], upvotes=42), commText=Good soup meme, upvotes=14)", c1.toString());
	}

	@Test
	void testHashCode() {
		assertEquals(-965939091, c1.hashCode());
	}
	
	@Test
	void testHashCode_inccorect() {
		assertNotEquals(34567894, c1.hashCode());
	}

	@Test
	void testEquals() {
		assertTrue(c1.equals(c1));
	}
	@Test
	public void simpleEqualsContract() {
	    EqualsVerifier.simple().forClass(Comment.class).verify();
	}
}
