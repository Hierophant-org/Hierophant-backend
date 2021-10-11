package com.hierophant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hierophant.model.User;
import com.hierophant.repository.UserDao;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	User userObj;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<User> findById(int userId)
	{
		try
		{
			return userDao.findById(userId);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In UserService.findById() userId was invalid. Returning empty Optional.");
		}
		return Optional.empty();
		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<User> findByUserName(String userName)
	{
		try
		{
			return userDao.findByUsername(userName);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In UserService.findByUserName() userName was invalid. Returning empty Optional.");
		}
		return Optional.empty();
	}
	// adds a user to the database
		public User insert(User user)
		{
			try
			{
				return userDao.save(user);	
			}
			catch(IllegalArgumentException e)
			{
				log.warn("In UserService.insert() user was invalid. Returning null.");
			}
			return null;
			
		}
		
		// updates user in database
		public User update(User user)
		{
			try
			{
				return userDao.save(user);	
			}
			catch(IllegalArgumentException e)
			{
				log.warn("In UserService.update() user was invalid. Returning false.");
			}
			return null;
			
		}
		
		// deletes a user from the database
		public void deleteById(int id) 
		{
			try
			{
				userDao.deleteById(id);	
			}
			catch(IllegalArgumentException e)
			{
				log.warn("In UserService.deleteById() id was invalid. Returning false.");
			}			
		}
		
		public List<User> findAll() {
			try {
				return userDao.findAll();
			}
			catch(IllegalArgumentException e)
			{
				log.warn("In UserService.findAll() Something went wrong. Returning null.");
				return null;
			}			
		}

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Optional<User> u = userDao.findByUsername(username);
			
			u.ifPresent(val -> {
				userObj = val;
			});
			return new org.springframework.security.core.userdetails.User(userObj.getUsername(), userObj.getPassword(), new ArrayList<>());  
			
		}
	
}
