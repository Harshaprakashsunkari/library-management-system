package com.capgemini.librarymanagement.database;

import java.util.HashMap;

import com.capgemini.librarymanagement.dto.Admin;
import com.capgemini.librarymanagement.dto.Book;
import com.capgemini.librarymanagement.dto.Student;

public class DataBase {

	public static final HashMap<String, Student> STUDENT = new HashMap<String, Student>();
	public static final HashMap<String, Admin> ADMIN = new HashMap<String, Admin>();
	public static final HashMap<Integer, Book> BOOK = new HashMap<Integer, Book>();
	
}
