package com.hierophant.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;



@RestController
@RequestMapping("/s3")
public class s3Service {
	////s3 stuff
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	 @Autowired
	    private AmazonS3 amazonS3;

	    @Value("${s3.bucket.name}")
	    private String s3BucketName;

	    public String generateUrl(String fileName, HttpMethod httpMethod) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
	        return amazonS3.generatePresignedUrl(s3BucketName, fileName, calendar.getTime(), httpMethod).toString();
	    }
	    
	    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
	        final File file = new File(multipartFile.getOriginalFilename());
	        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
	            outputStream.write(multipartFile.getBytes());
	        } catch (IOException e) {
	            log.error("Error {} occurred while converting the multipart file", e.getLocalizedMessage());
	        }
	        return file;
	    }
	    @Async
	    public S3ObjectInputStream findByName(String fileName) {
	        log.info("Downloading file with name {}", fileName);
	        return amazonS3.getObject(s3BucketName, fileName).getObjectContent();
	    }
	    @Async
	    public void save(final MultipartFile multipartFile) {
	        try {
	            final File file = convertMultiPartFileToFile(multipartFile);
	            final String fileName = LocalDateTime.now() + "_" + file.getName();
	            log.info("Uploading file with name {}", fileName);
	            final PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, fileName, file);
	            amazonS3.putObject(putObjectRequest);
	            Files.delete(file.toPath()); // Remove the file locally created in the project folder
	        } catch (AmazonServiceException e) {
	            log.error("Error {} occurred while uploading file", e.getLocalizedMessage());
	        } catch (IOException ex) {
	            log.error("Error {} occurred while deleting temporary file", ex.getLocalizedMessage());
	        }
	    }
}
