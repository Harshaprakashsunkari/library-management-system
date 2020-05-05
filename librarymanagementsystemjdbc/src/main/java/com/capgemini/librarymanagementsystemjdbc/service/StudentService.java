package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public interface StudentService {
	
	boolean req(int bookId, String email);
	BookBean searchBookType(int bookType);
	boolean returnBook(int bookId);
	
}
