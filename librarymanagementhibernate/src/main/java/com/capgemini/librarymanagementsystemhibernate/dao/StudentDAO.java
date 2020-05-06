package com.capgemini.librarymanagementsystemhibernate.dao;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface StudentDAO {
	
	boolean req(int id, int bookId);
	BookBean searchBookType(int bookType);	
	boolean returnBook(int id, int bookId);
	
}
