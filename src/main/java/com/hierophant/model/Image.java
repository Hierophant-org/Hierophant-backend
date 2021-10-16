package com.hierophant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor//Constructors auto formed
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;//unique id for each image
	
	private String imgHtml;//html link to template
	
	private String name;//image given name
    private String type;//image type
    
    private byte[] pic;//picture in bytes
    
	public Image(int id , String name , String type , byte[] pic)
	{
		this.imageId = id;//id of image
		this.name = name;
		this.type = type;
		this.pic = pic;
	}
}
