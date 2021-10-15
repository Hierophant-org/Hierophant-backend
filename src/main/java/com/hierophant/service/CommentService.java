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
	CommentDao commentDao;// import a comment DAO

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<Comment> findById(int comId) {
		//find comment based on id
		try {
			return commentDao.findById(comId);
		} catch (IllegalArgumentException e) {
			log.error("In CommentService.findById() com_id was invalid. Returning empty Optional.");
		}
		return Optional.empty();

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Comment> findByUserId(int userId) {
		//find comment based on userId
		try {
			return commentDao.findByUserId(userId);
		} catch (IllegalArgumentException e) {
			log.error("In CommentService.findByUserId() userId was invalid. Returning null.");
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Comment insert(Comment comment) {
		//insert comment
		try {
			return commentDao.save(comment);
		} catch (IllegalArgumentException e) {
			log.error("In CommentService.insert() comment was invalid. Returning null.");
		}
		return null;

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Comment update(Comment comment) {
		//update comment, unused
		try {
			return commentDao.save(comment);
		} catch (IllegalArgumentException e) {
			log.error("In CommentService.update() comment was invalid. Returning false.");
		}
		return null;

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteById(int id) {
		//delete comment
		try {
			commentDao.deleteById(id);
		} catch (IllegalArgumentException e) {
			log.error("In CommentService.deleteById() comment was invalid. Returning false.");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Comment> findByPostId(int postId) {
		//find comment list by Post
		try {
			return commentDao.findByPostId(postId);
		} catch (IllegalArgumentException e) {
			log.error("In CommentService.findByPostId(postId) postId was invalid. Returning null.");
		}
		return null;
	}

	public Optional<User> findUserByCommentId(int comId) {
		//find user that posted comment
		try {
			return commentDao.findUserIdByComId(comId);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			log.error("In CommentService.findUserIdByComId(comId) comId was invalid. Returning null.");
			e.printStackTrace();
			return null;
		}
	}

}
