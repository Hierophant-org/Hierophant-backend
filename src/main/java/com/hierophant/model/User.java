package com.hierophant.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	// class to represent each user in DB
	@Id
	@Column(name = "userId", nullable = false, unique = true, updatable = false) // non-nullable and unique =tru is //
																					// primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;// unique id for each user

	@NotBlank
	@Length(min = 2, max = 30)
	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*")
	private String username;// username, 1-30 length

	@NotEmpty
	@Length(min = 4)
	private String password;// user password, 1-30 length

	@Email // must contain @ and .something
	private String email;// user email, email format

	@OneToMany(mappedBy = "userId", cascade = { CascadeType.REMOVE, CascadeType.DETACH }, fetch = FetchType.EAGER)
	@Column(nullable = true)
	@JsonManagedReference(value = "userPost")
	private List<Post> posts;//list of posts

	@OneToMany(mappedBy = "userId", cascade = { CascadeType.REMOVE, CascadeType.DETACH }, fetch = FetchType.EAGER)

	@Column(nullable = true)
	@JsonManagedReference(value = "userCom")
	private List<Comment> comments;//list of comments
}
