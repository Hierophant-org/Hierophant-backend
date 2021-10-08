package com.hierophant.Hierophant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.hierophant.model.Comment;
import com.hierophant.model.Image;
import com.hierophant.model.Post;
import com.hierophant.model.User;
import com.hierophant.repository.CommentDao;
import com.hierophant.repository.ImageDao;
import com.hierophant.repository.PostDao;
import com.hierophant.repository.UserDao;
import com.hierophant.service.CommentService;
import com.hierophant.service.ImageService;
import com.hierophant.service.PostService;
import com.hierophant.service.UserService;

@SpringBootTest
class HierophantApplicationTests {

	
	//CommentController
////=========================================///	
	
	
	
	//ImageController
////=========================================///	

	
	
	
	//PostController
////=========================================///	

	
	
	
	
	//UserController
////=========================================///	
	
	
	
	
	//UserDao
////=========================================///
	
	@Test
	void testUserDaoFindById()
	{
		UserDao uDao = mock(UserDao.class);
		
		User user1 = new User();
		user1.setEmail("someone@somewhere.com");
		user1.setUserId(1);
		user1.setUsername("someGuyInTheWorld");
		user1.setPassword("ThisIsAPassword");
		Optional<User> Opuser = Optional.ofNullable(user1);
		
		when(uDao.findById(user1.getUserId())).thenReturn(Opuser);
		
		
		
		assertEquals (uDao.findById(user1.getUserId()).get() , user1);
		verify(uDao).findById(user1.getUserId());
	}
	
	@Test
	void testUserDaoFindByUserName()
	{
        UserDao uDao = mock(UserDao.class);
		
		User user1 = new User();
		user1.setEmail("someone@somewhere.com");
		user1.setUserId(1);
		user1.setUsername("someGuy");
		user1.setPassword("ThisIsAPassword");
		Optional<User> Opuser = Optional.ofNullable(user1);
		
		when(uDao.findByUsername(user1.getUsername())).thenReturn(Opuser);
		
		
		assertEquals (uDao.findByUsername(user1.getUsername()).get() , user1);
		verify(uDao).findByUsername(user1.getUsername());
	}
	
	
	
	
	
	
	
	//ImageDao
////=========================================///
	
	@Test
	void testImageDoaFindById()
	{
		ImageDao imgDao = mock(ImageDao.class);
		
		Image img1 = new Image();
		img1.setImageId(100);
		
		
		Optional<Image> Opcom = Optional.ofNullable(img1);
		
		when(imgDao.findById(img1.getImageId())).thenReturn(Opcom);
		
		
		assertEquals(imgDao.findById(img1.getImageId()) , Opcom);
		
		verify(imgDao).findById(img1.getImageId());
	}	
	
	
	
	
	
	//PostDao
////=========================================///
	
	@Test
	void testPostDoaFindByUserId()
	{
		PostDao postDao = mock(PostDao.class);
		
		Post post1 = new Post();
		User user1 = new User();
		user1.setUserId(1);
		post1.setPostId(1000);
		post1.setTitle("Test Post 1");
		post1.setUpvotes(100000);
		post1.setUserId(user1);
		
		Post post2 = new Post();
		User user2 = new User();
		user2.setUserId(2);
		post2.setPostId(2000);
		post2.setTitle("Test Post 2");
		post2.setUpvotes(100);
		post2.setUserId(user2);
		
		List<Post> posts = new ArrayList<>();
		posts.add(post1);
		posts.add(post2);
	
		
		when(postDao.findByUserId(post1.getPostId())).thenReturn(posts);
		
		List<Post> CommentList = postDao.findByUserId(post1.getPostId());
		
		assertTrue(CommentList.contains(post1));
		assertTrue(CommentList.contains(post2));
		
		verify(postDao).findByUserId(post1.getPostId());
	}
	
	@Test
	void testPostDoaFindById()
	{
		CommentDao comDao = mock(CommentDao.class);
		
		Comment com1 = new Comment();
		com1.setComId(100);
		com1.setCommText("This Is A Test Comment");
		
		Optional<Comment> Opcom = Optional.ofNullable(com1);
		
		when(comDao.findById(com1.getComId())).thenReturn(Opcom);
		
		
		assertEquals(comDao.findById(com1.getComId()) , Opcom);
		
		verify(comDao).findById(com1.getComId());
	}
	
	
	
 	
	
	
	
    //CommentDao
////=========================================///
	
	@Test
	void testCommentDoaFindByUserId()
	{
		CommentDao comDao = mock(CommentDao.class);
		
		Comment com1 = new Comment();
		com1.setComId(100);
		com1.setCommText("This Is A Test Comment");
		Comment com2 = new Comment();
		com2.setComId(100);
		com2.setCommText("This Is A Test Comment");
		Comment com3 = new Comment();
		com3.setComId(100);
		com3.setCommText("This Is A Test Comment");
		Comment com4 = new Comment();
		com4.setComId(100);
		com4.setCommText("This Is A Test Comment");
		
		List<Comment> comments = new ArrayList<>();
		comments.add(com1);
		comments.add(com2);
		comments.add(com3);
		comments.add(com4);
		
		when(comDao.findByUserId(1)).thenReturn(comments);
		
		List<Comment> CommentList = comDao.findByUserId(1);
		
		assertTrue(CommentList.contains(com1));
		assertTrue(CommentList.contains(com2));
		assertTrue(CommentList.contains(com3));
		assertTrue(CommentList.contains(com4));
		
		
		
		verify(comDao).findByUserId(1);
	}
	
	@Test
	void testCommentDoaFindById()
	{
		CommentDao comDao = mock(CommentDao.class);
		
		Comment com1 = new Comment();
		com1.setComId(100);
		com1.setCommText("This Is A Test Comment");
		
		Optional<Comment> Opcom = Optional.ofNullable(com1);
		
		when(comDao.findById(com1.getComId())).thenReturn(Opcom);
		
		
		assertEquals(comDao.findById(com1.getComId()) , Opcom);
		
		verify(comDao).findById(com1.getComId());
	}
	
	
	
 	
	
	
            //CommentService
	////=========================================///
	@Test
	void testCommentServiceFindByUserId()
	{
		CommentService comServe = mock(CommentService.class);
		
		Comment com1 = new Comment();
		com1.setComId(100);
		com1.setCommText("This Is A Test Comment");
		Comment com2 = new Comment();
		com2.setComId(100);
		com2.setCommText("This Is A Test Comment");
		Comment com3 = new Comment();
		com3.setComId(100);
		com3.setCommText("This Is A Test Comment");
		Comment com4 = new Comment();
		com4.setComId(100);
		com4.setCommText("This Is A Test Comment");
		
		List<Comment> comments = new ArrayList<>();
		comments.add(com1);
		comments.add(com2);
		comments.add(com3);
		comments.add(com4);
		
		when(comServe.findByUserId(1)).thenReturn(comments);
		
		List<Comment> CommentList = comServe.findByUserId(1);
		
		assertTrue(CommentList.contains(com1));
		assertTrue(CommentList.contains(com2));
		assertTrue(CommentList.contains(com3));
		assertTrue(CommentList.contains(com4));
		
		
		
		verify(comServe).findByUserId(1);
	}
	
	@Test
	void testCommentServiceFindById()
	{
		CommentService comServe = mock(CommentService.class);
		
		Comment com1 = new Comment();
		com1.setComId(100);
		com1.setCommText("This Is A Test Comment");
		
		Optional<Comment> Opcom = Optional.ofNullable(com1);
		
		when(comServe.findById(com1.getComId())).thenReturn(Opcom);
		
		
		assertEquals(comServe.findById(com1.getComId()) , Opcom);
		
		verify(comServe).findById(com1.getComId());
	}
	
	@Test
	void testCommentServiceInsert()
	{
        CommentService comServe = mock(CommentService.class);
		Comment com1 = new Comment();
		com1.setComId(100);
		com1.setCommText("This Is A Test Comment");
		
		when(comServe.insert(com1)).thenReturn(com1);
		
		assertEquals(comServe.insert(com1) , com1);
		verify(comServe).insert(com1);
	}
	
	@Test
	void testCommentServiceUpdate()
	{
		CommentService comServe = mock(CommentService.class);
		Comment com1 = new Comment();
		com1.setComId(100);
		com1.setCommText("This Is A Test Comment");
		
		when(comServe.update(com1)).thenReturn(com1);
		
		assertEquals(comServe.update(com1) , com1);
		verify(comServe).update(com1);
	}
	
	
    //PostService
////=========================================///
	
	@Test
	void testPostServiceInsert()
	{
		PostService postServe = mock(PostService.class);
		
		Post post1 = new Post();
		User user1 = new User();
		user1.setUserId(1);
		post1.setPostId(1000);
		post1.setTitle("Test Post 1");
		post1.setUpvotes(100000);
		post1.setUserId(user1);
		
		Post post2 = new Post();
		User user2 = new User();
		user2.setUserId(2);
		post2.setPostId(2000);
		post2.setTitle("Test Post 2");
		post2.setUpvotes(100);
		post2.setUserId(user2);
		
		when(postServe.insert(post1)).thenReturn(post1);
		when(postServe.insert(post2)).thenReturn(post2);
		
		assertEquals(post1 , postServe.insert(post1));
		assertEquals(post2 , postServe.insert(post2));	
		verify(postServe).insert(post1);
		verify(postServe).insert(post2);
	}
	
	@Test
	void testPostServiceUpdate()
	{
		PostService postServe = mock(PostService.class);
		Post post1= new Post();
		User user = new User();
		user.setUserId(1);
		post1.setPostId(100);
		post1.setTitle("Test Post 2 has been updated");
		post1.setUpvotes(100000);
		post1.setUserId(user);
		when(postServe.update(post1)).thenReturn(post1);
		
		assertEquals(postServe.update(post1) , post1);
		verify(postServe).update(post1);
	}
	
	@Test
	void testPostServiceFindByUserId()
	{
		PostService postServe = mock(PostService.class);
		
		Post post1 = new Post();
		User user = new User();
		user.setUserId(1);
		post1.setPostId(100);
		post1.setTitle("Test Post");
		post1.setUpvotes(100000);
		post1.setUserId(user);
		
		
		List<Post> posts = new ArrayList<>();
		posts.add(post1);
		
		
		when(postServe.findByUserId(1)).thenReturn(posts);
		
		List<Post> PostList = postServe.findByUserId(1);
		
		assertTrue(PostList.contains(post1));
		verify(postServe).findByUserId(1);
	}
	
	@Test
	void testPostServiceFindById()
	{
		PostService postServe = mock(PostService.class);
		
		Post post1 = new Post();
		User user = new User();
		user.setUserId(1);
		post1.setPostId(100);
		post1.setTitle("Test Post");
		post1.setUpvotes(100000);
		post1.setUserId(user);
		
		Optional<Post> Opcom = Optional.ofNullable(post1);
		
		when(postServe.findById(post1.getPostId())).thenReturn(Opcom);
		
		
        assertEquals(postServe.findById(post1.getPostId()) , Opcom);
		
		verify(postServe).findById(post1.getPostId());
	}
	
	@Test
	void testPostServiceFindByTitle()
	{
		PostService postServe = mock(PostService.class);
		
		Post post1 = new Post();
		User user = new User();
		user.setUserId(1);
		post1.setPostId(100);
		post1.setTitle("Tester123");
		post1.setUpvotes(100000);
		post1.setUserId(user);
		
		Optional<Post> Opcom = Optional.ofNullable(post1);
		
		when(postServe.findByTitle("Tester123")).thenReturn(Opcom);
		
		
		
		assertEquals(postServe.findByTitle("Tester123").get() , post1);
		verify(postServe).findByTitle(post1.getTitle());
	}
	
	
    //UserService
////=========================================///
	
	
	@Test
	void testUserServiceFindById()
	{
		UserService uServe = mock(UserService.class);
		
		User user1 = new User();
		user1.setEmail("someone@somewhere.com");
		user1.setUserId(1);
		user1.setUsername("someGuyInTheWorld");
		user1.setPassword("ThisIsAPassword");
		Optional<User> Opuser = Optional.ofNullable(user1);
		
		when(uServe.findById(user1.getUserId())).thenReturn(Opuser);
		
		
	
		assertEquals(uServe.findById(user1.getUserId()) , Opuser);
		verify(uServe).findById(user1.getUserId());
	}
	
	@Test
	void testUserServiceInsert()
	{
        UserService uServe = mock(UserService.class);
        User user1 = new User();
		user1.setEmail("someone@somewhere.com");
		user1.setUserId(1);
		user1.setUsername("someGuyInTheWorld");
		user1.setPassword("ThisIsAPassword");
		when(uServe.insert(user1)).thenReturn(user1);
		
		assertEquals(uServe.insert(user1) , user1);
		verify(uServe).insert(user1);
	}
	
	@Test
	void testUserServiceUpdate()
	{
		UserService uServe = mock(UserService.class);
		User user1 = new User();
		
		
		when(uServe.update(user1)).thenReturn(user1);
		
		assertEquals(uServe.update(user1) ,user1);
		verify(uServe).update(user1);
	}
	
	
	
    //ImageService
////=========================================///

	@Test
	void testImageServiceFindById()
	{
		ImageService IServe = mock(ImageService.class);
		
		Image img = new Image();
		img.setTopText("something");
		img.setBottomText("something");
		img.setImageId(100);
		img.setImgHtml("http://someurlforanimg.com/image");
		
		when(IServe.findById(img.getImageId())).thenReturn(Optional.ofNullable(img));
		
		assertEquals(IServe.findById(img.getImageId()) , Optional.ofNullable(img));
		verify(IServe).findById(img.getImageId());
	}
	
	@Test
	void testImageServiceInsert()
	{
		ImageService IServe = mock(ImageService.class);
		
		Image img = new Image();
		img.setTopText("something");
		img.setBottomText("something");
		img.setImageId(100);
		img.setImgHtml("http://someurlforanimg.com/image");
		
		when(IServe.insert(img)).thenReturn(img);
		
		assertEquals(IServe.insert(img) , img);
		verify(IServe).insert(img);
	}
	@Test
	void testImageServiceUpdate()
	{
		ImageService IServe = mock(ImageService.class);
		
		Image img = new Image();
		img.setTopText("something");
		img.setBottomText("something");
		img.setImageId(100);
		img.setImgHtml("http://someurlforanimg.com/image");
		
		when(IServe.update(img)).thenReturn(img);
		
		assertEquals(IServe.update(img) , img);
		verify(IServe).update(img);
	}
	
	
}
