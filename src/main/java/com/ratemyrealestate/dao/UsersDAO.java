package com.ratemyrealestate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ratemyrealestate.entities.User;
import com.ratemyrealestate.repositories.UserRepository;

@Repository
@Transactional
@Component("usersDao")
public class UsersDAO {
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private SessionFactory sessionFactory;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	public Session session() {
//		return sessionFactory.getCurrentSession();
//	}
//
	@Transactional
	public void create(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		session().save(user);
		userRepository.save(user);
	}
//	
//	public void delete(int id) {
//		Criteria crit = session().createCriteria(User.class);
//		crit.add(Restrictions.idEq(id));
//		User user = (User) crit.uniqueResult();
//		session().delete(user);
//	}
//
	public User getUser(int id) {
//		Criteria crit = session().createCriteria(User.class);
//		crit.add(Restrictions.idEq(id));
//		User user = (User) crit.uniqueResult();
		return userRepository.findOne(id);
//		return user;
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}

	public boolean exists(String username) {
		User existingUser = userRepository.findOneByUsername(username);
		if (existingUser != null) {
			return true;
		}
		return false;
	}

//	@SuppressWarnings("unchecked")
//	public List<User> getAllUsers() {
//		return session().createQuery("from User").list();
//	}
}
