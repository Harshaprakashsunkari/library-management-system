package com.capgemini.librarymanagementsystemspring.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;

public interface AdminDAO {
	
	boolean addBook(BookBean info);
	boolean delete(int bookId);
	boolean update(BookBean book);
	
	List<Integer> getBookIds();
	List<BookBean> getBooksInfo();
	BookBean searchBookType(int bookId);
	BookBean searchBookTitle(String booktitle);
	BookBean searchBookAuthor(String author);
	
	boolean issueBook(int id, int bookId);	
}
