package com.hierophant.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hierophant.model.Post;
import com.hierophant.model.User;
import com.hierophant.service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = { "http://hierophant-frontend-bucket.s3-website.us-east-2.amazonaws.com/",
		"http://localhost:4200/" })
public class PostController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PostService postService;

	@GetMapping("/find")
	public ResponseEntity<Optional<Post>> findByTitle(@RequestParam("title") String postTitle) {
		// find based on title
		// unused
		log.info("finding post:" + postTitle);
		return ResponseEntity.ok(postService.findByTitle(postTitle));
	}

	@GetMapping("/findBy")
	public ResponseEntity<Optional<Post>> findById(@RequestParam("id") int postId) {
		log.info("finding post basd on Id:" + postId);
		// find by comment id
		return ResponseEntity.ok(postService.findById(postId));
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Post>> findAll() {
		log.info("finding all posts");
		// find all posts
		return ResponseEntity.ok(postService.findAll());
	}

	@GetMapping("/findAllUsers")
	public ResponseEntity<List<User>> findAllUsers() {
		log.info("finding post users");
		// find list of users
		return ResponseEntity.ok(postService.findUserByPost());
	}

	@GetMapping("/user")
	public ResponseEntity<List<Post>> findByUserId(@RequestParam("id") int userId) {
		// find by poster's username
		log.info("finding post by userID:" + userId);
		return ResponseEntity.ok(postService.findByUserId(userId));
	}

	@PostMapping("/insert")
	public ResponseEntity<Post> insert(@Valid @RequestBody Post p) {
		// insert post
		log.info("inserting post:" + p);
		return ResponseEntity.ok(postService.insert(p));
	}

	@PatchMapping("/update")
	public ResponseEntity<Post> update(@Valid @RequestBody Post p) {
		// update post
		log.info("updating post:" + p);
		return ResponseEntity.ok(postService.update(p));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int postId) {
		// delete post
		// Untested and unused
		log.info("deleting post:" + postId);
		postService.deleteById(postId);
		return ResponseEntity.noContent().build();
	}
}
