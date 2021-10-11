package com.hierophant.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class PostController {
	@Autowired
	PostService postService;

	@GetMapping("/find")
	public ResponseEntity<Optional<Post>> findByTitle(@RequestParam("title") String postTitle) {
		return ResponseEntity.ok(postService.findByTitle(postTitle));
	}

	@GetMapping("/findBy")
	public ResponseEntity<Optional<Post>> findById(@RequestParam("id") int comId) {
		return ResponseEntity.ok(postService.findById(comId));
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Post>> findAll() {
		return ResponseEntity.ok(postService.findAll());
	}
	@GetMapping("/findAllUsers")
	public ResponseEntity<List<User>> findAllUsers() {
		return ResponseEntity.ok(postService.findUserByPost());
	}

//	@GetMapping("/user/{username}")
//	public ResponseEntity<List<Post>> findByUserName(@PathVariable("username") String userName) {
////		return null;
//		return ResponseEntity.ok(postService.findByUserName(userName));
//	}

	@GetMapping("/user")
	public ResponseEntity<List<Post>> findByUserId(@RequestParam("id") int userId) {
		return ResponseEntity.ok(postService.findByUserId(userId));
	}

	@PostMapping("/insert")
	public ResponseEntity<Post> insert(@Valid @RequestBody Post p) {
		return ResponseEntity.ok(postService.insert(p));
	}

	@PatchMapping("/update")
	public ResponseEntity<Post> update(@Valid @RequestBody Post p) {
		return ResponseEntity.ok(postService.update(p));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int userId) {
		// Untested
		postService.deleteById(userId);
		return ResponseEntity.noContent().build();
	}
}
