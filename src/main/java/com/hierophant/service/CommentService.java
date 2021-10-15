package com.hierophant.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hierophant.model.Comment;
import com.hierophant.model.User;
import com.hierophant.repository.CommentDao;

@Service
public class CommentService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CommentDao commentDao;
	
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<Comment> findById(int comId)
	{

		try
		{
		return commentDao.findById(comId);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In CommentService.findById() com_id was invalid. Returning empty Optional.");
		}
		return Optional.empty();

	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Comment> findByUserId(int userId)
	{

		try
		{
		return commentDao.findByUserId(userId);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In CommentService.findByUserId() userId was invalid. Returning null.");
		}
		return null;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Comment insert(Comment comment)
	{
		try
		{
		return commentDao.save(comment);
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In CommentService.insert() comment was invalid. Returning null.");
		}
		return null;
		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Comment update(Comment comment)
	{
		try
		{
		return commentDao.save(comment);
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In CommentService.update() comment was invalid. Returning false.");
		}
		return null;
		
	}
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteById(int id)
	{
		try
		{
			commentDao.deleteById(id);
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In CommentService.deleteById() comment was invalid. Returning false.");
		}		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Comment> findByPostId(int postId)
	{
		try
		{
			return commentDao.findByPostId(postId);
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In CommentService.findByPostId(postId) postId was invalid. Returning null.");
		}
		return null;		
	}
	public Optional<User> findUserByCommentId(int comId){
		try {
			return commentDao.findUserIdByComId(comId);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
