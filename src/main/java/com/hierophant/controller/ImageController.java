package com.hierophant.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.net.http.HttpResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.hierophant.model.Image;
import com.hierophant.service.ImageService;
import com.hierophant.service.PostService;


@RestController
@RequestMapping("/images")
@CrossOrigin(origins={"http://hierophant-frontend-bucket.s3-website.us-east-2.amazonaws.com/","http://localhost:4200/"})

public class ImageController {
	private static Logger log = LoggerFactory.getLogger(ImageController.class);
	@Autowired
	ImageService imageService;
    @Autowired
	PostService ps;
	
	@GetMapping("/find")
	public ResponseEntity<Optional<Image>> findById(@PathVariable("id") int id) {
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
	  
	@PostMapping("/upLoad")
	public ResponseEntity<Image> upLoadImage(@RequestParam("myImage") MultipartFile file)throws IOException{
		try
		{
			int id = ps.getPostCount()+1;
		Image img = new Image( 
				id , 
				file.getOriginalFilename() , 
				file.getContentType() , 
				file.getBytes()); 
		final Image savedImage = imageService.insert(img);
		log.info("UpLoad Success, "+savedImage.getName()+" saved to db");
		return ResponseEntity.ok(savedImage);
		}
		catch(IOException e)
		{	
		log.warn("Upload Failed! ");
		}
		return null;
	}
	
	
	
	
	//this was a method for downloading the memes from imgflip to the server and saving them in a file called memes. 
	//it was going to be a workaround for the cors canvas tainting issue but the problem was solved in another way.
	//I just left it here in case it may be needed for some reason in the future.
	/***********************************
	public HttpResponse<String> downloadMemes()throws IOException, InterruptedException
	{        
			URL obj = new URL("https://api.imgflip.com/get_memes");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
                 String[] subs = response.toString().split(",");
                
                 int memecounter = 0;
                 for(int i = 0 ; i < subs.length; i++)
                 {
                	 
				   if(subs[i].contains("url"))
				   {
					String[] details = subs[i].split(":");
					String[] file = details[2].split("/");
					String filename = file[file.length -1].replace("\"", "");
					System.out.println(filename);
					String[] ext = filename.split("\\.");
					System.out.println();
					String url = "https://i.imgflip.com/"+filename;
					downloadImage(url , String.valueOf(memecounter+"."+ext[1]));
					memecounter++;
				   }
                 }
				 
			} else { 
				System.out.println("GET request not worked");
			}
			return null;
	}
	
	String fileLocation = new File("src\\main\\resources\\memes\\").getAbsolutePath();

	public void downloadImage(String url , String name)
	{
		log.info("downloading meme image : "+url+" : "+name );
			try(InputStream in = new URL(url).openStream()){
			    Files.copy(in, Paths.get(fileLocation+"\\"+name) , StandardCopyOption.REPLACE_EXISTING);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	   
		} 
		  
	***************/

}
