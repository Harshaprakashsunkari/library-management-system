package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public class StudentDAOImp implements StudentDAO{

	@Override
	public boolean req(int id, int bookId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query q = manager.createQuery("select b from BookBean b where b.bookId = :bbookId");
			Query	s = q.setParameter("bbookId", bookId);
			List count = s.getResultList();
			int c = count.size();
			if(c != 0) {

				Query q1 =	manager.createQuery("select u.name from UserBean u where id=:id");
				q1.setParameter("id", id);
				List qq =  q1.getResultList();
				Query q2 = manager.createQuery("select b.book_title from BookBean b where bookId=:bookId");
				q2.setParameter("bookId", bookId);
				List qq1 = q2.getResultList();
				Query q3 = manager.createQuery("select e.email from UserBean e where id=:id");
				q3.setParameter("id", id);
				List qq3 = q3.getResultList();
				Query req = manager.createNativeQuery("insert into requestbook1 (bookId,email,book_title,id,name,type) values (?,?,?,?,?,?)");
				req.setParameter(1, bookId);
				req.setParameter(2,qq3.get(0));
				req.setParameter(3, qq1.get(0));
				req.setParameter(4, id);
				req.setParameter(5, qq.get(0));
				req.setParameter(6, "request");
				
				req.executeUpdate();
				transaction.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		
		return false;	
	}

	@Override
	public BookBean searchBookType(int bookType) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BookBean record = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			record = manager.find(BookBean.class, bookType);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();

		return record;

	}

	@Override
	public boolean returnBook(int id, int bookId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query q = manager.createQuery("select b from BorrowedBookBean b where b.borrowBookPK.bookId = :bookId");
			q.setParameter("bookId", bookId);	
			List count = q.getResultList();
			int s = count.size();
			if(s >= 0) {
				Query q1 =	manager.createQuery("select u.name from UserBean u where id=:id");
				q1.setParameter("id", id);
				List qq =  q1.getResultList();
				Query q2 = manager.createQuery("select b.book_title from BookBean b where bookId=:bookId");
				q2.setParameter("bookId", bookId);
				List qq1 = q2.getResultList();
				Query q3 = manager.createQuery("select e.email from UserBean e where id=:id");
				q3.setParameter("id", id);
				List qq3 = q3.getResultList();
				Query req = manager.createNativeQuery("insert into requestbook1 (bookId,email,book_title,id,name,type) values (?,?,?,?,?,?)");
				req.setParameter(1, bookId);
				req.setParameter(2,qq3.get(0));
				req.setParameter(3, qq1.get(0));
				req.setParameter(4, id);
				req.setParameter(5, qq.get(0));
				req.setParameter(6, "return");
				int count1 = req.executeUpdate();
				transaction.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return false;
	}


	
}
