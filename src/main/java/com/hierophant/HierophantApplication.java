package com.hierophant;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hierophant.controller.ImageController;

@SpringBootApplication
public class HierophantApplication {

	public static void main(String[] args) {
		SpringApplication.run(HierophantApplication.class, args);
		ImageController IC = new ImageController();
//		try {
//			IC.downloadMemes();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
