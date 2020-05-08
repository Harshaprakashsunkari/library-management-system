package com.capgemini.librarymanagementsystemspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspring.dao.StudentDAO;
import com.capgemini.librarymanagementsystemspring.dto.BookBean;

@Service
public class StudentServiceImp implements StudentService {
	@Autowired
	StudentDAO dao;

	@Override
	public boolean req(int id, int bookId) {
		return dao.req(id, bookId);
	}

	@Override
	public boolean returnBook(int bookId, int id) {
		return dao.returnBook(bookId, id);
	}

	@Override
	public List<Integer> getBookIds() {
		return dao.getBookIds();
	}

	@Override
	public List<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public BookBean searchBookType(int bookType) {
		return dao.searchBookType(bookType);
	}

	@Override
	public BookBean searchBookTitle(String name) {
		return dao.searchBookTitle(name);
	}

	@Override
	public BookBean searchBookAuthor(String Author) {
		return dao.searchBookAuthor(Author);
	}
}
