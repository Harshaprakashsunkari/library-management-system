package com.capgemini.librarymanagement.dao;

import com.capgemini.librarymanagement.dto.Admin;
import com.capgemini.librarymanagement.dto.Book;

public interface AdminDAO {

	boolean register(Admin admin);
	Admin adminLogin(String adEmail, String password);
	boolean addBook(Book book);
	boolean removeBook(int bookId);
	boolean issueBook(int bookId);

}
