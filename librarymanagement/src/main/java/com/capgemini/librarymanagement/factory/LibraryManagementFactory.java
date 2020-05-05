package com.capgemini.librarymanagement.factory;

import com.capgemini.librarymanagement.dao.AdminDAO;
import com.capgemini.librarymanagement.dao.AdminDAOImplementation;
import com.capgemini.librarymanagement.dao.StudentDAO;
import com.capgemini.librarymanagement.dao.StudentDAOImplement;
import com.capgemini.librarymanagement.service.LibraryService;
import com.capgemini.librarymanagement.service.LibraryServiceImplement;

public class LibraryManagementFactory {
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImplement();
	}
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImplementation();
	}
	
	public static LibraryService getLibraryService() {
		return new LibraryServiceImplement();
	}
}
