package com.hierophant.Hierophant.modelTests;

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

public class ImageModelTests {
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
	void testGetandSetId() {
		assertEquals(3, i1.getImageId());
		i1.setImageId(5);
		assertEquals(5,i1.getImageId());
	}
	@Test
	void testGetandSetBottomText() {
		assertEquals("cat", i1.getBottomText());
		i1.setBottomText("cat2");
		assertEquals("cat2",i1.getBottomText());
	}
	@Test
	void testGetandSetTopText() {
		assertEquals("tophat", i1.getTopText());
		i1.setTopText("hat");
		assertEquals("hat",i1.getTopText());
	}
	@Test
	void testGetandSetHtml() {
		assertEquals("google.com", i1.getImgHtml());
		i1.setImgHtml("other html");
		assertEquals("other html",i1.getImgHtml());
	}
	@Test
	void testGetandSetPic() {
		assertEquals(null, i1.getPic());
		byte[] b = new byte[0];
		i1.setPic(b);
		assertEquals(b,i1.getPic());
	}
	@Test
	void testGetandSetUpvotes() {
		assertEquals("JSON", i1.getType());
		i1.setType("new type");
		assertEquals("new type",i1.getType());
	}
}
