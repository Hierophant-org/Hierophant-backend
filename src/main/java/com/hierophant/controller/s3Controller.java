package com.hierophant.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.hierophant.service.s3Service;

public class s3Controller {

	s3Service s3Service = new s3Service();
	
	
//	@PostMapping("/savefile")
//    public ResponseEntity<Object> saveFile(@RequestParam("extension") String extension) {
//        return new ResponseEntity<>(s3Service.save(extension), HttpStatus.OK);
//    }
    private static final String MESSAGE_1 = "Uploaded the file successfully";
    private static final String FILE_NAME = "fileName";

    @Autowired
    

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
        return new ResponseEntity<>(MESSAGE_1, HttpStatus.OK);
    }
}
