package com.hierophant.controller;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.hierophant.service.s3Service;
@RestController // RestController is a specific type of Controller that already assumes you're returning a @ResponseBody
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/s3")
public class s3Controller {

	@Autowired
	s3Service s3Service; //  = new s3Service()
	
	
//	@PostMapping("/savefile")
//    public ResponseEntity<Object> saveFile(@RequestParam("extension") String extension) {
//        return new ResponseEntity<>(s3Service.save(extension), HttpStatus.OK);
//    }
    private static final String MESSAGE_1 = "Uploaded the file successfully";
    private static final String FILE_NAME = "file-name";

    
  

    @GetMapping("/getfile")
    public ResponseEntity<Object> findByName(@RequestBody(required = false) Map<String, String> params) {
        return ResponseEntity
                .ok()
                ///.cacheControl(CacheControl.noCache())
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + params.get(FILE_NAME) + "\"")
                .body(new InputStreamResource(s3Service.findByName(params.get(FILE_NAME))));

    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestParam("file") MultipartFile multipartFile) {
        s3Service.save(multipartFile);
        return new ResponseEntity<>(MESSAGE_1, HttpStatus.OK);
    }
    @PostMapping("/getUrl")
    public ResponseEntity<Object> getUrl(@RequestParam("file") MultipartFile multipartFile) {
        String url = s3Service.generateUrl(multipartFile.getName(), HttpMethod.POST);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
    

@PostMapping("/file")
public ResponseEntity<Object> file(@RequestParam("file") MultipartFile multipartFile) throws IOException {
	 System.out.println("file endpoint hit");
	 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    
     String uploadDir = "user-photos/" + fileName;

     Path uploadPath = Paths.get(uploadDir);
     
     if (!Files.exists(uploadPath)) {
         Files.createDirectories(uploadPath);
     }
      
     try (InputStream inputStream = multipartFile.getInputStream()) {
         Path filePath = uploadPath.resolve(fileName);
         Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
     } catch (IOException ioe) {        
         throw new IOException("Could not save image file: " + fileName+"  "+ ioe);
     }       
	return new ResponseEntity<>("file received", HttpStatus.OK);
    
    
}
}
