package com.capgemini.librarymanagementsystemhibernate.dao;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface AdminDAO {
	
	boolean addBook(BookBean info);
	boolean remove(int bookId);
	boolean issueBook(int id , int bookId);
	
}
