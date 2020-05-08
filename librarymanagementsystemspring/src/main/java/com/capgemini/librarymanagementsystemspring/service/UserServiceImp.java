package com.capgemini.librarymanagementsystemspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspring.dao.UserDAO;
import com.capgemini.librarymanagementsystemspring.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemspring.dto.UserBean;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDAO dao;

	@Override
	public boolean register(UserBean bean) {
		return dao.register(bean);
	}

	@Override
	public UserBean auth(String email, String password) {
		return dao.auth(email, password);
	}

}
