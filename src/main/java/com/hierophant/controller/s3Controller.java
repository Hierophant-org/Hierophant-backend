package com.hierophant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.HttpMethod;
import com.hierophant.model.User;
import com.hierophant.service.s3Service;
@RestController // RestController is a specific type of Controller that already assumes you're returning a @ResponseBody
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/s3")
public class s3Controller {

	@Autowired
	s3Service s3Service; //  = new s3Service()
	private static Logger log = LoggerFactory.getLogger(s3Controller.class);

	@Autowired
	ResourceLoader resourceLoader;

	@PostMapping("/saveImage")
	// The Valid annotation makes sure that User must comply with the restriction we set in the model
	public String saveImage(@RequestParam("data") MultipartFile image)throws IOException { // we're taking in the User object in the HTTP RequestBody
		System.out.println("File received "+image);
		s3Service.putFileInBucket(image);
		return "this is the response "+image.toString();
	}
}
