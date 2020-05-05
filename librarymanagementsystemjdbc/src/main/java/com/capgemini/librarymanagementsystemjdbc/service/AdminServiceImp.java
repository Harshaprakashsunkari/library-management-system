package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.factory.AdminFactory;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public class AdminServiceImp implements AdminService{
	
	private AdminDAO dao = AdminFactory.getAdminDAO();

	@Override
	public int addBook(BookBean info) {
		return dao.addBook(info);
	}

	@Override
	public int remove(int bookId) {
		return dao.remove(bookId);
	}

	@Override
	public boolean issueBook(int bookId,String email) {
		return dao.issueBook(bookId, email);
	}
}
