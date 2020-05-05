package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public interface AdminService {
	
	int addBook(BookBean info);
	int remove(int bookId);
	boolean issueBook(int bookId, String email);
}
