package com.capgemini.librarymanagementsystemhibernate.service;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface AdminService {
	
	boolean addBook(BookBean info);
	boolean remove(int bookId);
	boolean issueBook(int id , int bookId);

}
