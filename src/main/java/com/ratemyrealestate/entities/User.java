package com.ratemyrealestate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.ratemyrealestate.dao.FormValidationGroup;
import com.ratemyrealestate.dao.PersistenceValidationGroup;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@NotNull
//	@Size(min=7, max=15, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
//	@Pattern(regexp="^\\w{7,}$",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String username;
	
	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=5, max=10, groups={FormValidationGroup.class})
	@Pattern(regexp="^\\S+$", groups={FormValidationGroup.class})
	private String password;
	
	private boolean enabled = false;
	
	private String authority;

	public User() {
	}
	
	public User(String username, String password, boolean enabled,
			String authority) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
//		result = prime * result + id;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (enabled != other.enabled)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", enabled=" + enabled + ", authority="
				+ authority + "]";
	}
	
}
