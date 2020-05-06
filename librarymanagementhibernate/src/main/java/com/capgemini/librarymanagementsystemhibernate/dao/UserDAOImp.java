package com.capgemini.librarymanagementsystemhibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;

public class UserDAOImp implements UserDAO{

	@Override
	public boolean register(UserBean bean) {
		EntityManagerFactory factory=null;
		EntityManager manager=null;
		EntityTransaction transaction=null;
		boolean isRegistered = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			manager.persist(bean);
			transaction.commit();
			isRegistered = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isRegistered;
	}

	@Override
	public UserBean auth(String email, String password) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory=Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			UserBean user = manager.find(UserBean.class, email);
			user.setEmail(email);
			manager.persist(user);
			if(user.getPassword().equals(password)) {
				return user;
			}
			transaction.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
			
	}
}
