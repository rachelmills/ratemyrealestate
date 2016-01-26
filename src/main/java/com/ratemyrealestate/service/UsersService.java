package com.ratemyrealestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ratemyrealestate.dao.GetUserByUsernameParameter;
import com.ratemyrealestate.dao.UsersDAO;
import com.ratemyrealestate.dto.SignupForm;
import com.ratemyrealestate.dto.UserDetailsIml;
import com.ratemyrealestate.entities.User;
import com.ratemyrealestate.repositories.UserRepository;

@Service
public class UsersService implements UserDetailsService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private UsersDAO usersDAO;

	@Autowired
	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	public User getUser(int userId) {
		return usersDAO.getUser(userId);
	}

	public void createUser(SignupForm signupForm) {
		User user = new User(signupForm.getEmail(), passwordEncoder.encode(signupForm.getPassword()), true, "");
		user.setEnabled(true);
		user.setAuthority("ROLE_USER");
		usersDAO.create(user);
	}

	public boolean exists(String username) {
		return usersDAO.exists(username);
	}
	
	public User getUser(String username) {
		return usersDAO.getUserByUsername(username);
	}
//
//	@Secured("ROLE_ADMIN")
//	public List<User> getAllUsers() {
//		return usersDAO.getAllUsers();
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOneByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsIml(user);
	}
	
}
