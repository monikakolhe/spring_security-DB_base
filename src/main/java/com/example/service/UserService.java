/**
 * 
 */
package com.example.service;

import java.util.List;

import com.example.entity.User;

/**
 * @author monika.kolhe
 *
 */
public interface UserService {

	public String addUser(User user);
	public List<User> getAll();

}
