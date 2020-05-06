package com.capgemini.librarymanagementsystemhibernate.service;


import com.capgemini.librarymanagementsystemhibernate.dao.StudentDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.factory.StudentFactory;

public class StudentServiceImp implements StudentService{
	private StudentDAO dao = StudentFactory.getStudentDAO();
	
	@Override
	public boolean req(int id, int bookId) {
		return dao.req(id, bookId);
	}

	@Override
	public BookBean searchBookType(int bookType) {
		return dao.searchBookType(bookType);
	}

	@Override
	public boolean returnBook(int id, int bookId) {
		return dao.returnBook(id, bookId);
	}

			

}
