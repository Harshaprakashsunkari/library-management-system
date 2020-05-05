package com.capgemini.librarymanagement.service;

import com.capgemini.librarymanagement.dto.Admin;
import com.capgemini.librarymanagement.dto.Book;
import com.capgemini.librarymanagement.dto.Student;

public interface LibraryService {
	
	boolean register(Student student);
	Student studentLogin(String email,String password);
	boolean placeReq(int bookId,String bookAuthor);
	Book searchBook(int bookId);	
	boolean register(Admin admin);
	Admin adminLogin(String adEmail, String password);
	boolean addBook(Book book);
	boolean removeBook(int bookId);
	boolean issueBook(int bookId);
	boolean returnBook(int bookId);
}
