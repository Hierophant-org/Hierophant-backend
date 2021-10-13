package com.hierophant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hierophant.model.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, Integer>{
	
	// finds an image based on its id
	public Optional<Image> findById(int id);
}
