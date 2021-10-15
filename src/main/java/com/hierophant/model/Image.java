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
@AllArgsConstructor
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;
	
	private String imgHtml;//html link to template
	
	private String name;
    private String type;
    
    private byte[] pic;
    
	public Image(int id , String name , String type , byte[] pic)
	{
		this.imageId = id;
		this.name = name;
		this.type = type;
		this.pic = pic;
	}
}
