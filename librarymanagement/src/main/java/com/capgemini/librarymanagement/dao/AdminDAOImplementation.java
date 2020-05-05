package com.capgemini.librarymanagement.dao;

import com.capgemini.librarymanagement.database.DataBase;
import com.capgemini.librarymanagement.dto.Admin;
import com.capgemini.librarymanagement.dto.Book;
import com.capgemini.librarymanagement.exception.LibraryManagementException;

public class AdminDAOImplementation implements AdminDAO {

	public boolean register(Admin admin) {
		try {
			if (DataBase.ADMIN.containsKey(admin.getAdEmail()) && admin.getAdEmail().equals(null)) {
				throw new LibraryManagementException("Email Id already exists");
			} else {
				DataBase.ADMIN.put(admin.getAdEmail(), admin);
				return true;
			}
		}catch (LibraryManagementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Admin adminLogin(String adEmail, String adPassword) {
		try {
			if(DataBase.ADMIN.get(adEmail) != null) {
				Admin admin = DataBase.ADMIN.get(adEmail);
				if (admin.getAdEmail().equals(adEmail) && (admin.getAdPassword().equals(adPassword))) {
					return admin;
				} else {
					throw new LibraryManagementException("Invalid credentials");
				}
			}else {
				throw new LibraryManagementException("Invalid Email or Password");
			}
		} catch (LibraryManagementException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public boolean addBook(Book book) {
		try {
			DataBase.BOOK.put(book.getBookId(), book);
			boolean addBook = DataBase.BOOK.containsKey(book.getBookId());
			if (addBook) {
				return addBook;
			} else {
				throw new LibraryManagementException("Book is not added");
			}
		}catch (LibraryManagementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeBook(int bookId) {
		try {
			boolean remove = DataBase.BOOK.containsKey(bookId);
			if (remove) {
				DataBase.BOOK.remove(bookId);
				return true;
			} else {
				throw new LibraryManagementException("Book is not available");
			}
		}
		catch (LibraryManagementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean issueBook(int bookId) {
		try {
			boolean issue = DataBase.BOOK.containsKey(bookId);
			if (issue) {
				DataBase.BOOK.remove(bookId);
				return true;
			} else {
				throw new LibraryManagementException("Book is not available");
			}
		}catch (LibraryManagementException e) {
			e.printStackTrace();
			return false;
		}

	}

}