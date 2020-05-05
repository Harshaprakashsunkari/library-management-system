package com.capgemini.librarymanagementsystemjdbc.service;


import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.factory.StudentFactory;

public class StudentServiceImp implements StudentService{

	private StudentDAO dao = StudentFactory.getStudentDAO();
	@Override
	public boolean req(int bookId,String email ) {
		return dao.req(bookId,email);
	}

	@Override
	public BookBean searchBookType(int bookType) {
		return dao.searchBookType(bookType);
	}

	@Override
	public boolean returnBook(int bookId) {
		return dao.returnBook(bookId);
	}

}
