package com.capgemini.librarymanagementsystemhibernate.service;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface StudentService {

	boolean req(int id, int bookId);
	BookBean searchBookType(int bookType);
	boolean returnBook(int id, int bookId);
	
}
