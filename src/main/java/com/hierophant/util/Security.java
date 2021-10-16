package com.hierophant.util;

public class Security {
	public static final long EXPIRATION_TIME = 28_800_000;// Adjust if needed  8 hours in miliseconds
	// the word Bearer in front of a token means that whoever gave me the token with the word Bearer in front of it
	// I don't need to verify it and just work with it
	public static final String TOKEN_PREFIX = "Bearer "; //token prefix
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";// the header name 
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verfied"; // give this message when user messed with token
	public static final String HIEROPHANT = "Created by hierophant"; // flex on our peers
	public static final String GET_ARRAY_ADMINISTRATION = "User Token Portal";// the name of array to get
	public static final String AUTHORIZATION = "Authorization";//Authorization
	public static final String[] PUBLIC_URL = {"/user/register", "user/login", "/home"}; // urls that does not need the token
}
