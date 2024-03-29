package com.capgemini.librarymanagementsystemjdbc.dao;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public interface StudentDAO {
	
	boolean req(int bookId, String email);
	BookBean searchBookType(int bookType);
	boolean returnBook(int bookId);
}
