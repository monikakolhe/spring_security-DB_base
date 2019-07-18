/**
 * 
 */
package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
/**
 * @author monika.kolhe
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public String addUser(User user) {
		if(null!= user)
		{
			User userDB = this.userRepository.findById(user.getId()).orElse(null);
			if(null != userDB)
			{
				return "User already Registered here";
			}
			else {
				String password = new BCryptPasswordEncoder().encode(user.getPassword());
				user.setPassword(password);
				this.userRepository.save(user);
				return "User Registered here";
			}
		}else
		{
			return "Please add the user info";
		}
		
	}


	@Override
	public List<User> getAll() {
		List<User> users = this.userRepository.findAll();
		return users;
	}




}
