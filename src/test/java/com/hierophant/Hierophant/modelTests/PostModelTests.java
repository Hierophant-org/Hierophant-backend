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

public class PostModelTests {
	static User u1;
	static Image i1;
	static Post p1;
	static Comment c1;
	static ArrayList<Post> p = new ArrayList<>();
	static ArrayList<User> u = new ArrayList<>();
	static ArrayList<Comment> c = new ArrayList<>();

	@BeforeAll
	static void beforeAll() {
	}

	@BeforeEach
	void before() {

		u1 = new User(4, "testuser", "testpassword", "emailperson@gmail.com", p, c);
		i1 = new Image(3, "title", "google.com", "testName", "tophat", "cat", null);
		p1 = new Post(2, "testTitle", u1, i1, c, 42);
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
		assertEquals(2, p1.getPostId());
		p1.setPostId(5);
		assertEquals(5, p1.getPostId());
	}

	@Test
	void testGetandSetTitle() {
		assertEquals("testTitle", p1.getTitle());
		p1.setTitle("Bad soup meme");
		assertEquals("Bad soup meme", p1.getTitle());
	}

	@Test
	void testGetandSetComments() {
		assertEquals(new ArrayList<Comment>(), p1.getComments());
		ArrayList<Comment> a = new ArrayList<Comment>();
		a.add(c1);
		p1.setComments(a);
		assertEquals(a, p1.getComments());
	}

	@Test
	void testGetandSetUser() {
		assertEquals(u1, p1.getUserId());
		User u2 = new User(40, "testuser", "testpassword", "emailperson@gmail.com", new ArrayList<Post>(),
				new ArrayList<Comment>());
		p1.setUserId(u2);
		assertEquals(u2, p1.getUserId());
	}

	@Test
	void testGetandSetUpvotes() {
		assertEquals(42, p1.getUpvotes());
		p1.setUpvotes(21);
		assertEquals(21, p1.getUpvotes());
	}

	@Test
	void testGetandSetImage() {
		assertEquals(i1, p1.getImage());
		Image i2 = new Image(14, "sadf", "asdg.com", "adsg", "bt", "qwc", null);
		p1.setImage(i2);
		assertEquals(i2, p1.getImage());
	}

	@Test
	void testToString() {
		assertEquals("Post(postId=2, title=testTitle, userId=User(userId=4, username=testuser, password=testpassword, email=emailperson@gmail.com, posts=[], comments=[]), image=Image(imageId=3, imgHtml=title, name=google.com, type=testName, topText=tophat, bottomText=cat, pic=null), comments=[], upvotes=42)",p1.toString());
	}

	@Test
	void testHashCode() {
		assertEquals(170902870, p1.hashCode());
	}

	@Test
	void testHashCode_inccorect() {
		assertNotEquals(34567894, p1.hashCode());
	}

	@Test
	void testEquals() {
		assertTrue(p1.equals(p1));
	}

	@Test
	void testEquals_Wrong_Type() {
		assertFalse(p1.equals(p));
	}
}
