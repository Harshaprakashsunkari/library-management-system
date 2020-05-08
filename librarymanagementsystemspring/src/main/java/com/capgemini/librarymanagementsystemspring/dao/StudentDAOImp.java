package com.capgemini.librarymanagementsystemspring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;
@Repository
public class StudentDAOImp implements StudentDAO{
@PersistenceUnit
EntityManagerFactory factory;
	@Override
	public BookBean searchBookTitle(String name) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BookBean res = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			
			String jpql = "select m from BookBean m where m.book_title =:mbook_title";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("mbook_title", name);
			res=query.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
			
		
	return  res;
	
	}

	@Override
	public BookBean searchBookAuthor(String Author) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BookBean res = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			
			String jpql = "select m from BookBean m where m.author =:mauthor";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("mauthor", Author);
			res=query.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
			
		
	return  res;
	
	}

	@Override
	public List<Integer> getBookIds() {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		List<Integer> bookBeans = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.getTransaction();
			Query q = manager.createQuery("select bookId from BookBean b");
			bookBeans = q.getResultList();
			transaction.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
			
		
		return bookBeans;
	
	}

	@Override
	public List<BookBean> getBooksInfo() {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		List<BookBean> bookBeans = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.getTransaction();
			Query q = manager.createQuery("from BookBean");
			bookBeans = q.getResultList();
			transaction.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
			
		
		return bookBeans;
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
				q2.setParameter("bid", bookId);
				List qq1 = q2.getResultList();
				Query q3 = manager.createQuery("select e.email from UserBean e where id=:id");
				q3.setParameter("id", id);
				List qq3 = q3.getResultList();
				Query req = manager.createNativeQuery("insert into requestbookspring (bookId,email,book_title,id,name,type) values (?,?,?,?,?,?)");
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
	public boolean returnBook(int bookId, int id) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query q = manager.createQuery("select b from BorrowedBookBean b where b.borrowBookPK.bookId = :bookId");
			q.setParameter("bid", bookId);	
			List count = q.getResultList();
			int s = count.size();
			if(s >= 0) {
				Query q1 =	manager.createQuery("select u.name from UserBean u where id=:id");
				q1.setParameter("id", id);
				List qq =  q1.getResultList();
				Query q2 = manager.createQuery("select b.book_title from BookBean b where bookId=:bookId");
				q2.setParameter("bid", bookId);
				List qq1 = q2.getResultList();
				Query q3 = manager.createQuery("select e.email from UserBean e where id=:id");
				q3.setParameter("id", id);
				List qq3 = q3.getResultList();
				Query req = manager.createNativeQuery("insert into requestbookspring (bookId,email,book_title,id,name,type) values (?,?,?,?,?,?)");
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
