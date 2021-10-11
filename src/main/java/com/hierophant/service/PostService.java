package com.hierophant.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hierophant.model.Post;
import com.hierophant.model.User;
import com.hierophant.repository.PostDao;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<Post> findById(int comId)
	{

		try
		{
		return postDao.findById(comId);
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In PostService.findById() comId was invalid. Returning empty Optional.");
		}
		return Optional.empty();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Post> findByUserName(String userName)
	{
		try
		{
		//return postDao.findByUsername(userName);
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In PostService.findByUserName() userName was invalid. Returning null.");
		}
		return null;
		
	}


	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Post> findByUserId(int userId)
	{

		
		try
		{
			return postDao.findByUserId(userId);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In PostService.findByUserId() userId was invalid. Returning null.");
		}
		return null;

	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<Post> findByTitle(String postTitle)
	{

		try
		{

			return postDao.findByTitle(postTitle);	

		}
		catch(IllegalArgumentException e)
		{
			log.warn("In PostService.findByTitle() postTitle was invalid. Returning empty Optional.");
		}
		return Optional.empty();

	}
	// inserts a post to the database
	public Post insert(Post post)
	{
		try
		{
			return postDao.save(post);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In PostService.insert() post was invalid. Returning null.");
		}
		return null;
		
	}
	
	// updates a post in the database (edit)
	public Post update(Post post)
	{
		try
		{
			postDao.save(post);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In PostService.update() post was invalid. Returning false.");
		}
		return null;
		
	}
	
	// deletes a post from the database
	public void deleteById(int id)
	{
		try
		{
			postDao.deleteById(id);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In PostService.deleteById() id was invalid. Returning false.");
		}		
	}
	public List<User> findUserByPost(){
		try {
			return postDao.findAllIncludeUser();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public List<Post> findAll() {
		try {
			List<Post> p=postDao.findAll();
			List<User> u=findUserByPost();
			for(int i =0; i<p.size(); i++) {
				p.get(i).setUserId(u.get(i));
				System.out.println(p.get(i).getUserId().getUserId());
			}
			return p;
		} catch(IllegalArgumentException e)
		{
			log.warn("In PostService.findAll() something went wrong. Returning null.");
			return null;
		}
	}
}
