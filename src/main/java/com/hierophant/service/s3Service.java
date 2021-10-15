package com.hierophant.service;



import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class s3Service {

	
	AWSCredentials credentials = new BasicAWSCredentials(
			  "AKIAYI6HBH7Z4FE5DVWG", 
			  "FzIWWma5tJU6TvS11ISKtuh9JC0EyMtgk5h6iutO"
			);
	
	
	AmazonS3 s3client = AmazonS3ClientBuilder
			  .standard()
			  .withCredentials(new AWSStaticCredentialsProvider(credentials))
			  .withRegion(Regions.US_EAST_2)
			  .build();
	String bucketName = "hierophant-bucket";
	public void createBucket()
	{
		//String bucketName = "baeldung-bucket";

		if(s3client.doesBucketExist(bucketName)) {
		    System.out.println("Bucket name is not available."
		      + " Try again with a different Bucket name.");
		    return;
		}
	
		s3client.createBucket(bucketName);
	}
	
	
	public void listBuckets()
	{
	List<Bucket> buckets = s3client.listBuckets();
			
	for(Bucket bucket : buckets) {
	    System.out.println(bucket.getName());
	  }
	}
	
	public void putFileInBucket(MultipartFile file)
	{
	s3client.putObject(
			  bucketName, 
			  file.getName(), 
			  new File("/Users/user/Document/hello.txt")
			);
	}
	public void listObjectsInBucket()
	{
	ObjectListing objectListing = s3client.listObjects(bucketName);
	for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
	   System.out.println(os.getKey());
	}
	}
	public void getObjectFromBucket()
	{
	S3Object s3object = s3client.getObject(bucketName, "picture/pic.png");
	S3ObjectInputStream inputStream = s3object.getObjectContent();
	try {
		FileUtils.copyInputStreamToFile(inputStream, new File("/Users/user/Desktop/hello.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}

    
