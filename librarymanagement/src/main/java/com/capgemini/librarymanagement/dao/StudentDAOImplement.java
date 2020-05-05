package com.capgemini.librarymanagement.dao;

import com.capgemini.librarymanagement.database.DataBase;
import com.capgemini.librarymanagement.dto.Book;
import com.capgemini.librarymanagement.dto.Student;
import com.capgemini.librarymanagement.exception.LibraryManagementException;

public class StudentDAOImplement implements StudentDAO {

	DataBase db = new DataBase();

	public boolean register(Student student) {

		try {

			if (DataBase.STUDENT.containsKey(student.getEmail()) || student.getEmail().equals(null)) {
				throw new LibraryManagementException("Email Id already exists");
			} else {
				DataBase.STUDENT.put(student.getEmail(), student);
			}
		} catch (LibraryManagementException e) {
			System.err.println(e.getMessage());
		}
		return true;
	}

	public Student studentLogin(String studentEmail, String studentPassword) {

		try {
			if(DataBase.STUDENT.get(studentEmail) != null) {
				Student student = DataBase.STUDENT.get(studentEmail);
				if ((student.getEmail().equals(studentEmail)) && (student.getStPassword().equals(studentPassword))) {
					return student;
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

	public boolean placeReq(int bookId, String bookAuthor) {

		try {
			boolean req = DataBase.BOOK.containsKey(bookId);
			if (req) {
				return true;
			} else {
				throw new LibraryManagementException("Book is not requested");
			}
		} catch (LibraryManagementException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public Book searchBook(int bookId) {
		try {
			Book book = null;
			boolean isExist = DataBase.BOOK.containsKey(bookId);
			if (isExist) {
				book = DataBase.BOOK.get(bookId);
				return book;
			} else {
				throw new LibraryManagementException("Enter Valid Book Id");
			}
		} catch (LibraryManagementException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public boolean returnBook(int bookId) {
		try {
			boolean returned = DataBase.BOOK.containsKey(bookId);
			if (returned) {
				Book b = DataBase.BOOK.get(bookId);
				DataBase.BOOK.put(b.getBookId(), b);
				return true;
			} else {
				throw new LibraryManagementException("The Book issued is not matching");
			}
		} catch (LibraryManagementException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}