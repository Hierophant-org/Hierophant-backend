package com.hierophant.Hierophant.modelTests;

import static org.junit.Assert.assertFalse;
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
	void testToString() {
		assertEquals("Image(imageId=3, imgHtml=google.com, name=testName, type=JSON, topText=tophat, bottomText=cat, pic=null)", i1.toString());
	}
	@Test
	void testHashCode() {
		assertEquals(-1267939041, i1.hashCode());
	}
	
	@Test
	void testEquals() {
		assertTrue(i1.equals(i1));
	}
	@Test
	void testEquals_False() {
		String str = "byte array size example";
		byte array[] = str.getBytes();
		Image i2 = new Image(25, "notgoogle.com", "test4Name", "JSsN", "tophsat", "cxat", array);
		assertFalse(i1.equals(i2));
	}
	@Test
	void testEquals_False_2() {
		String str = "byte array size example";
		byte array[] = str.getBytes();
		Image i2 = new Image(4, "google.com", "testName", "JSON", "tophat", "cat", null);
		assertFalse(i1.equals(i2));
	}
	@Test
	void testEquals_False_3() {
		String str = "byte array size example";
		byte array[] = str.getBytes();
		Image i2 = new Image(4, "google.com", "testName", "JSON", "tophat", "cat", null);
		assertFalse(i1.equals(i2.getBottomText()));
	}
	@Test
	void testGetandSetName() {
		assertEquals("testName", i1.getName());
		i1.setName("testName");
		assertEquals("testName",i1.getName());
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
	@Test
	public void simpleEqualsContract() {
	    EqualsVerifier.simple().forClass(Image.class).verify();
	}
}
