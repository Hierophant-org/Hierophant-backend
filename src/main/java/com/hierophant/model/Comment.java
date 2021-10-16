package com.hierophant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	//model of comments table
	@Id
	@Column(name = "comId", nullable = false, unique = true, updatable = false) // non-nullable and unique =tru is
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comId;// unique id for each comment

	@ManyToOne(fetch = FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name = "comUserId", referencedColumnName = "userId")
	@JsonBackReference(value="userCom")
	private User userId;//who commented

	@ManyToOne(fetch = FetchType.EAGER, targetEntity=Post.class)
	@JoinColumn(name = "comPostId", referencedColumnName = "postId")
	@JsonBackReference(value="postCom")
	private Post postId;//which post they commented on
	
	private String commText;//what horrible they commented

	private int upvotes;//how much other people likes their comment
}