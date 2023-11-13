package com.jsp.springboot1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.springboot1.dao.UserDao;
import com.jsp.springboot1.dto.ResponseStructure;
import com.jsp.springboot1.dto.User;
import com.jsp.springboot1.exception.NoSuchUserIdFoundException;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public ResponseStructure<User> saveUser(User user) {
		if (user != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setData(userDao.saveUser(user));
			responseStructure.setStatus_code(HttpStatus.CREATED.value());
			responseStructure.setMessage("User saved successfully");
			return responseStructure;
		} else {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setData(null);
			responseStructure.setMessage("User not found to save");
			responseStructure.setStatus_code(400);
			return responseStructure;
		}
	}

	public boolean deleteUserByID(int id) {
		return userDao.deleteUserByID(id);
	}

	public User updateUserById(int id, User user) {
		User u = userDao.getUserById(id);
		if (u != null) {
			u.setName(user.getName());
			u.setEmail(user.getEmail());
			return userDao.updateUserById(id, u);
		} else {
			return null;
		}
	}

	public ResponseStructure<User> getUserByID(int id) throws NoSuchUserIdFoundException {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = userDao.getUserById(id);
		if (user != null) {
			responseStructure.setData(user);
			responseStructure.setMessage("user found");
			responseStructure.setStatus_code(HttpStatus.CREATED.value());

		}else {
			throw new NoSuchUserIdFoundException();
		}

		return responseStructure;
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public User validateUser(String email, String password) {
		return userDao.validateUser(email, password);
	}

}
