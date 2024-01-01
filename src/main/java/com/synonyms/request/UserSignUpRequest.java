package com.synonyms.request;

import com.synonyms.enums.UserRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserSignUpRequest implements Serializable {

	@NotBlank
	private String email;

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@NotNull
	private UserRole role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank
	private String password;

	public UserSignUpRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public UserSignUpRequest(){
	}
}
