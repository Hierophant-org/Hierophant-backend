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
import org.springframework.security.authentication.AuthenticationManager;

import com.hierophant.controller.UserController;
import com.hierophant.model.AuthRequest;
import com.hierophant.model.Comment;
import com.hierophant.model.Image;
import com.hierophant.model.Post;
import com.hierophant.model.User;
import com.hierophant.service.UserService;
import com.hierophant.util.JwtToken;

public class UserControllerTests {
	static UserController userCon = new UserController();
	static UserService mockUserServ = mock(UserService.class);
	static JwtToken jwtMock = mock(JwtToken.class);
	static AuthenticationManager authenticationMock= mock(AuthenticationManager.class);
	static User u1;
	static Image i1;
	static Post p1;
	static Comment c1;

	@BeforeAll
	static void beforeAll() {
		userCon.userService = mockUserServ;
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
		userCon.userService = mockUserServ;
		u1 = null;
		i1 = null;
		p1 = null;
		c1 = null;
	}
	@Test
	void testFindById() {
		int id = u1.getUserId();
		Optional<User> oC = Optional.of(u1);
		when(mockUserServ.findById(id)).thenReturn(oC);
		assertEquals(ResponseEntity.ok(oC), userCon.findById(id));
	}
	@Test
	void testFindByUsername() {
		String name = u1.getUsername();
		Optional<User> oC = Optional.of(u1);
		when(mockUserServ.findByUserName(name)).thenReturn(oC);
		assertEquals(ResponseEntity.ok(oC), userCon.findByUsername(name));
	}
	@Test
	void testFindAll() {
		List<User> list = new ArrayList<>();
		User u2 = new User(2, "testuser2", "testpassword", "emailperson@gmail.com", new ArrayList<Post>(),
				new ArrayList<Comment>());
		User u3 = new User(3, "testuser3", "testpassword", "emailperson@gmail.com", new ArrayList<Post>(),
				new ArrayList<Comment>());
		User u4 = new User(4, "testuser4", "testpassword", "emailperson@gmail.com", new ArrayList<Post>(),
				new ArrayList<Comment>());
		
		list.add(u1);
		list.add(u2);
		list.add(u3);
		list.add(u4);
		when(mockUserServ.findAll()).thenReturn(list);
		assertEquals(ResponseEntity.ok(list), userCon.findAll());
	}
	@Test
	void testInsert() {
		when(mockUserServ.insert(u1)).thenReturn(u1);
		assertEquals(ResponseEntity.ok(u1), userCon.insert(u1));
	}
	@Test
	void testUpdate() {
		when(mockUserServ.update(u1)).thenReturn(u1);
		assertEquals(ResponseEntity.ok(u1), userCon.update(u1));
	}
	@Test
	void testDelete() {
		assertEquals(ResponseEntity.noContent().build(), userCon.deleteById(u1.getUserId()));
		verify(mockUserServ).deleteById(u1.getUserId());
	}
	@Test
	void testWelcome() {
		assertEquals("The token worked y'all", userCon.welcome());
	}
//	@Test
//	void testGenerateToken() throws Exception {
//		AuthRequest authreqMock = mock(AuthRequest.class);
//		when(authenticationMock.authenticate())
//		assertEquals("The token worked y'all", userCon.generateToken(authreqMock));
//	}
	
}
