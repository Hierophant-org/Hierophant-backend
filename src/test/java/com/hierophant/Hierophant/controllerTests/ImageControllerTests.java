package com.hierophant.Hierophant.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.hierophant.controller.ImageController;
import com.hierophant.model.Comment;
import com.hierophant.model.Image;
import com.hierophant.model.Post;
import com.hierophant.model.User;
import com.hierophant.service.ImageService;
import com.hierophant.service.PostService;

public class ImageControllerTests {
	static ImageController imageCon = new ImageController();
	static ImageService mockComServ = mock(ImageService.class);
	static PostService mockPostServ = mock(PostService.class);
	static User u1;
	static Image i1;
	static Post p1;
	static Comment c1;

	@BeforeAll
	static void beforeAll() {
		imageCon.imageService = mockComServ;
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
		imageCon.imageService = mockComServ;
		u1 = null;
		i1 = null;
		p1 = null;
		c1 = null;
	}

	@Test
	void testFindByIdFinds() {
		int id = i1.getImageId();
		Optional<Image> oC = Optional.of(i1);
		when(mockComServ.findById(id)).thenReturn(oC);

		assertEquals(ResponseEntity.ok(oC), imageCon.findById(id));
	}

	@Test
	void testInsert() {
		when(mockComServ.insert(i1)).thenReturn(i1);

		assertEquals(ResponseEntity.ok(i1), imageCon.insert(i1));
	}

	@Test
	void testUpdate() {
		when(mockComServ.update(i1)).thenReturn(i1);
		assertEquals(ResponseEntity.ok(i1), imageCon.update(i1));
	}

	@Test
	void testDelete() {
		assertEquals(ResponseEntity.noContent().build(), imageCon.deleteById(i1.getImageId()));
		verify(mockComServ).deleteById(i1.getImageId());
	}
//	@Test
//	void testUploadImage() {
//		when(mockPostServ.getPostCount()).thenReturn(10);
//		assertEquals(ResponseEntity.noContent().build(), imageCon.deleteById(i1.getImageId()));
//		verify(mockComServ).deleteById(i1.getImageId());
//	}
}
