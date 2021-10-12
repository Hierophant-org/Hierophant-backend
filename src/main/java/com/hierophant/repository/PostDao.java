package com.hierophant.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hierophant.model.Post;
import com.hierophant.model.User;

@Repository
public interface PostDao extends JpaRepository<Post, Integer>{
	
	// finds the post based on its title 
	public Optional<Post> findByTitle(String postTitle);
	
	// finds the post based on its id
	public Optional<Post> findById(int comId);
	
	// finds all the post from a particular user
//	public List<Post> findByUsername(String userName);
	public List<Post> findByUserId(int userId);
	
	@Query("SELECT user FROM Post p JOIN p.userId user")
	public List<User> findAllIncludeUser();
}
