package com.capgemini.librarymanagementsystemjdbc.dao;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;


public interface AdminDAO {
		
	int addBook(BookBean info);
	int remove(int bookId);
	boolean issueBook(int bookId, String email);
	
	
}
