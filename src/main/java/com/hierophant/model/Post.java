package com.hierophant.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@Column(name = "postId", nullable = false, unique = true, updatable = false) // non-nullable and unique =tru is
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;// unique id for each post

	private String title;//title given to post
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name = "postUserId", referencedColumnName="userId")
	@JsonBackReference(value="userPost")
	private User userId;//who posted it

	@OneToOne(cascade = CascadeType.ALL, targetEntity=Image.class)
    @JoinColumn(name = "postImageId", referencedColumnName = "imageId")
	private Image image;//what image they gave

	 @OneToMany(mappedBy = "postId") // inverse side: it has a mappedBy attribute, and can't decide how the association is mapped, since the other side already decided it.
	 @Column(nullable = true)
	 @JsonManagedReference(value="postCom")
	 private List<Comment> comments;//list of comments
	
	private int upvotes;//how funny the meme is - the only important metric to users
}
