package com.hierophant.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.hierophant.model.Image;
import com.hierophant.service.ImageService;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins="http://hierophant-frontend-bucket.s3-website.us-east-2.amazonaws.com/")
public class ImageController {

	@Autowired
	ImageService imageService;

	@GetMapping("/find")
	public ResponseEntity<Optional<Image>> findById(@RequestParam("id") int id) {
		return ResponseEntity.ok(imageService.findById(id));
	}

	@PostMapping("/insert")
	public ResponseEntity<Image> insert(@Valid @RequestBody Image i) {
		return ResponseEntity.ok(imageService.insert(i));
	}

	@PatchMapping("/update")
	public ResponseEntity<Image> update(@Valid @RequestBody Image i) {
		return ResponseEntity.ok(imageService.update(i));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
		// Untested
		imageService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
