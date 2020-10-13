package com.capgemini.librarymanagementsystemspring.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagementsystemspring.exceptions.LibraryManagementException;

public class LibraryManagementValidation {
	
	public boolean validatedId(int id) throws LibraryManagementException {
		String idRegEx = "[0-9]{1}[0-9]{1}" ;
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LibraryManagementException("Id should contains exactly 2 digits");
		}
		return result;
	}
	public boolean validatedName(String name) throws LibraryManagementException {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LibraryManagementException("Name should contain only Alpabates");
		}
		return result;
	}

	public static boolean validatedMobile(long mobile) throws LibraryManagementException {
		String mobileRegEx = "(0/91)?[4-9][0-9]{9}" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(mobileRegEx);
		if (Pattern.matches(mobileRegEx, String.valueOf(mobile))) {
				result = true;	
		} else {
			throw new LibraryManagementException("Mobile Number  will start between 6 & 9 and It should contains 10 numbers ");
		}
		return result;
	}
	
	public boolean validatedEmail(String email) throws LibraryManagementException {
		String emailRegEx = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LibraryManagementException("Enter proper email ");
		}
		return result;
	}

	public boolean validatedPassword(String password) throws LibraryManagementException {
		String passwordRegEx = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%]).{6,20})" ;
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) { 
			result = true;
		} else {
			throw new LibraryManagementException("Password should contain atleast 6 characters ,one uppercase,one lowercase,one number,one special symbol(@#$%) "); 
		}

		return result;
	}

}