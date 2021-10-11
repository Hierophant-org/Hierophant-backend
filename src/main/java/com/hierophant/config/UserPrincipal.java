package com.hierophant.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hierophant.model.User;

public class UserPrincipal implements UserDetails {
	// Serial Version
	private static final long serialVersionUID = 1L;
	// Making a prefix user role since we don't use that in our database
	String ROLE_PREFIX = "ROLE_USER";
	// Instantiate a User object
	private User user;
	
	// Initialize the user object, this case inject the dependency through constructor
	public UserPrincipal(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// We will never use this so I'm setting it to prefix
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority(ROLE_PREFIX));
		return list;
	}

	@Override
	public String getPassword() {
		// Get the password so Spring Security can configured it
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// Get the username so Spring Security can configured it
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// We will never use this so I'm setting to true
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// We will never use this so I'm setting to true
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// We will never use this so I'm setting to true
		return true;
	}

	@Override
	public boolean isEnabled() {
		// This could be some we can do, we can set it to false by default and let Admin approve it
		// but for now since we're not using it so I'm setting it to true
		return true;
	}

}
