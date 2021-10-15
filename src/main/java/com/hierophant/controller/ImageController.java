package com.hierophant.controller;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.multipart.MultipartFile;

import com.hierophant.model.Image;
import com.hierophant.service.ImageService;
import com.hierophant.service.PostService;
@RestController
@RequestMapping("/images")
@CrossOrigin(origins={"http://hierophant-frontend-bucket.s3-website.us-east-2.amazonaws.com/","http://localhost:4200/"})

public class ImageController {

	@Autowired
	ImageService imageService;
    @Autowired
	PostService ps;
	
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
	
	@PostMapping("/uploadImage")
	public Image upLoadImage(@RequestParam("myImage") MultipartFile file)throws IOException
	{
		Image img = new Image( ps.getPostCount() +1 , file.getOriginalFilename() , file.getContentType() , file.getBytes()); 
		
		final Image savedImage = imageService.insert(img);
		System.out.println("Image Saved!");
		return savedImage;
		
	}
	@GetMapping("/getMemes")
	public HttpResponse<String> getMemes()throws IOException, InterruptedException
	{
		 HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	        		.uri(URI.create("https://api.imgflip.com/get_memes"))
	                .build();

	        HttpResponse<String> response = client.send(request,
	                HttpResponse.BodyHandlers.ofString());

	        System.out.println(response);
		
		return response;
		
	}
}
