package com.hierophant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hierophant.model.Comment;
import com.hierophant.model.User;

@Repository
@Transactional
public interface CommentDao extends JpaRepository<Comment, Integer> {
	
	// finds a single comment
	public Optional<Comment> findById(int comId);
	
	// finds all the comments from a specific user
//	public List<Comment> findByUsername(String userName);
	public List<Comment> findByUserId(int userId);
	
	//finds comments for given post
	@Query("FROM Comment c WHERE c.postId=postId")
	public List<Comment> findByPostId(int postId);
	
	//find which user posted the comment
	@Query("SELECT user FROM Comment c JOIN c.userId user WHERE c.comId=?1")
	public Optional<User> findUserIdByComId(int id);
}
