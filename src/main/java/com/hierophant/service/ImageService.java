package com.hierophant.service;





import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hierophant.model.Image;
import com.hierophant.repository.ImageDao;

@Service
public class ImageService {
	@Autowired
	private ImageDao imgDao;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<Image> findById(int imgId)
	{
		try
		{
		return imgDao.findById(imgId);	
		}
		catch(IllegalArgumentException e)
		{
			log.warn("In ImageService.findById() imgId was invalid. Returning empty Optional.");
		}
		return Optional.empty();
	}
	// inserts a image to the database
		public Image insert(Image image)
		{
			try
			{
			return imgDao.save(image);
			}
			catch(IllegalArgumentException e)
			{
				log.warn("In CommentService.insert() image was invalid. Returning null.");
			}
			return null;
			
		}
				
		// updates a image in the database (edit)
		public Image update(Image image)
		{
			try
			{
			return imgDao.save(image);
			
			}
			catch(IllegalArgumentException e)
			{
				log.warn("In CommentService.update() image was invalid. Returning false.");
			}
			return null;
			
		}
				
		// deletes a image from the database
		public void deleteById(int id)
		{
			try
			{
				imgDao.deleteById(id);
			
			}
			catch(IllegalArgumentException e)
			{
				log.warn("In CommentService.deleteById() image was invalid. Returning false.");
			}
			
		}
	
	
		
}
