package com.capgemini.librarymanagementsystemspring.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;

public interface StudentDAO {

	boolean req(int id, int bookId);
	boolean returnBook(int bookId, int id);
	
	public List<Integer> getBookIds();
	public List<BookBean> getBooksInfo();
	BookBean searchBookType(int bookId);
	public BookBean searchBookTitle(String bookTitle); 
	public BookBean searchBookAuthor(String Author);
	
}
