package com.capgemini.librarymanagementsystemspring.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;
import com.capgemini.librarymanagementsystemspring.dto.BookIssueDetailsBean;

@Repository
public class AdminDAOImp implements AdminDAO {
	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public boolean addBook(BookBean info) {
		EntityManager manager = null;
		EntityTransaction transaction = null;

		boolean isBookAdded = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(info);
			isBookAdded = true;
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return isBookAdded;
	}

	@Override
	public boolean delete(int bId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		boolean del = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean record = manager.find(BookBean.class, bId);
			if (manager.contains(record)) {
				del = true;
				manager.remove(record);
				System.out.println("Record removed successfully");
			} else {
				del = false;
				System.out.println("Record not found");
			}

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return del;
	}

	@Override
	public boolean update(BookBean book) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		boolean isUpdated = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(book);
			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public List<Integer> getBookIds() {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		List<Integer> bookBeans = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.getTransaction();
			Query q = manager.createQuery("select b.bookId from BookBean b");
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
	public BookBean searchBookTitle(String name) {
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
			try {
				res = query.getSingleResult();
				transaction.commit();
			} catch (NoResultException result) {
				System.out.println("No results found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();

		return res;
	}

	@Override
	public BookBean searchBookAuthor(String Author) {
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
			res = query.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();

		return res;
	}

	@Override
	public boolean issueBook(int id, int bookId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BookIssueDetailsBean b = new BookIssueDetailsBean();
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query q = manager.createQuery("select b from BookBean b where b.bookId = :bbookId and b.copies>=1");
			Query ss = q.setParameter("bbookId", bookId);
			List count = ss.getResultList();
			System.out.println(count);
			int S = count.size();
			if (S >= 1) {
				Query q1 = manager.createQuery(
						"select r from RequestBookBean r where r.id = :id and r.reqBookPK.bookId = :bookId");
				q1.setParameter("id", id);
				q1.setParameter("bookId", bookId);
				List count1 = q1.getResultList();
				int s = count1.size();
				System.out.println(s);
				if (s >= 1) {
					Query q2 = manager.createQuery("select count(id) as idCount from BorrowedBookBean b where id=:id");
					q2.setParameter("id", id);
					List count2 = q2.getResultList();
					int s1 = count2.size();
					if (s1 >= 1) {
						int noOfBooksBorrowed = count2.indexOf(0);
						if (noOfBooksBorrowed < 3) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							LocalDate date = LocalDate.now();
							Calendar c = Calendar.getInstance();
							c.setTime(new java.util.Date());
							c.add(Calendar.DATE, 15);
							String date1 = sdf.format(c.getTime());
							Query userEmail = manager.createQuery("select u.email from UserBean u  where id = :id");
							userEmail.setParameter("id", id);
							List userEmail1 = userEmail.getResultList();
							Query q3 = manager.createNativeQuery(
									"insert into bookissuespring (id,bookId,email,issueDate,returnDate) values (? , ? , ? , ? , ?) ");
							q3.setParameter(1, id);
							q3.setParameter(2, bookId);
							q3.setParameter(3, userEmail1.get(0));
							q3.setParameter(4, date);
							q3.setParameter(5, date1);
							int count3 = q3.executeUpdate();
							if (count3 != 0) {
								Query userEmail4 = manager.createQuery("select u.email from UserBean u where id = :id");
								userEmail4.setParameter("id", id);
								List userEmail44 = userEmail4.getResultList();
								Query q4 = manager.createNativeQuery(
										"insert into borrowbookspring (id,bookId,email) values (?,?,?)");
								q4.setParameter(1, id);
								q4.setParameter(2, bookId);
								q4.setParameter(3, userEmail44.get(0));
								q4.executeUpdate();

								Query q5 = manager.createQuery(
										"delete from RequestBookBean r where r.id = :id and r.reqBookPK.bookId = :bookId");
								q5.setParameter("id", id);
								q5.setParameter("bookId", bookId);
								q5.executeUpdate();
								Query q6 = manager.createQuery(
										"update BookBean b set b.copies = b.copies-1 where b.bookId = :bookId");
								q6.setParameter("bookId", bookId);
								q6.executeUpdate();
								transaction.commit();
								return true;
							}

						}

					}
				}

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
