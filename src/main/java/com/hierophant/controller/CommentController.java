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

import com.hierophant.model.Comment;
import com.hierophant.model.User;
import com.hierophant.service.CommentService;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins={"http://hierophant-frontend-bucket.s3-website.us-east-2.amazonaws.com/","http://localhost:4200/"})
public class CommentController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CommentService commentService;

	@GetMapping("/find")
	public ResponseEntity<Optional<Comment>> findById(@RequestParam("id") int comId) {
		//find based on id
		log.info("finding comment Id:");
		return ResponseEntity.ok(commentService.findById(comId));
	}
	
	@GetMapping("/findByPostId")
	public ResponseEntity<List<Comment>> findByPostId(@RequestParam("id") int postId) {
		//find comment based on postId
		log.info("finding comment where post is id:");
		return ResponseEntity.ok(commentService.findByPostId(postId));
	}
	@GetMapping("/findUser")
	public ResponseEntity<Optional<User>> findAllUsers(@RequestParam("id") int comId) {
		//find user based on comment ID
		log.info("finding users where comment has id:");
		return ResponseEntity.ok(commentService.findUserByCommentId(comId));
	}
	@GetMapping("/user")
	public ResponseEntity<List<Comment>> findByUserId(@RequestParam("id") int userId) {
		log.info("finding comments where users has id:");
		//find comments posted by a current user
		return ResponseEntity.ok(commentService.findByUserId(userId));
	}

	@PostMapping("/insert")
	public ResponseEntity<Comment> insert(@Valid @RequestBody Comment c) {
		log.info("Inserting Comment");
		//insert comment into DB
		return ResponseEntity.ok(commentService.insert(c));
	}

	@PatchMapping("/update")
	public ResponseEntity<Comment> update(@Valid @RequestBody Comment c) {
		log.info("Updating Comment ");
		//update comment in DB
		return ResponseEntity.ok(commentService.update(c));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
		//delete comment from DB
		log.info("Deleting Comment: ");
		// Unused
		commentService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
