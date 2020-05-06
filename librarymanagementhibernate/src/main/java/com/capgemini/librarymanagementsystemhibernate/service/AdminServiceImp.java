package com.capgemini.librarymanagementsystemhibernate.service;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.factory.AdminFactory;

public class AdminServiceImp implements AdminService{
	private AdminDAO dao = AdminFactory.getAdminDAO();
	
	@Override
	public boolean addBook(BookBean info) {
		return dao.addBook(info);
	}

	@Override
	public boolean remove(int bookId) {
		return dao.remove(bookId);
	}

	@Override
	public boolean issueBook(int id, int bookId) {
		return dao.issueBook(id, bookId);
	}
}
