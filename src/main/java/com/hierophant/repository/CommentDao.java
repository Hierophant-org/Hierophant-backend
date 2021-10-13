package com.hierophant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hierophant.model.Comment;

@Repository
@Transactional
public interface CommentDao extends JpaRepository<Comment, Integer> {
	
	// finds a single comment
	public Optional<Comment> findById(int comId);
	
	// finds all the comments from a specific user
//	public List<Comment> findByUsername(String userName);
	public List<Comment> findByUserId(int userId);
}
