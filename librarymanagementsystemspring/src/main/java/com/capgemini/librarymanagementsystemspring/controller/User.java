package com.capgemini.librarymanagementsystemspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;
import com.capgemini.librarymanagementsystemspring.dto.UserBean;
import com.capgemini.librarymanagementsystemspring.dto.LibraryManagementResponse;
import com.capgemini.librarymanagementsystemspring.service.AdminService;
import com.capgemini.librarymanagementsystemspring.service.AdminServiceImp;
import com.capgemini.librarymanagementsystemspring.service.StudentService;
import com.capgemini.librarymanagementsystemspring.service.StudentServiceImp;
import com.capgemini.librarymanagementsystemspring.service.UserService;
import com.capgemini.librarymanagementsystemspring.service.UserServiceImp;

@RestController
public class User {
	
	@Autowired
	UserService service;
	@Autowired
	AdminService service1;
	@Autowired
	StudentService service2;

	@PostMapping(path = "/addUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public LibraryManagementResponse addUser(@RequestBody UserBean bean) {
		boolean isAdded = service.register(bean);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (isAdded) {
			response.setMessage("Record Inserted successfully");
		} else {
			response.setError(true);
			response.setMessage("Unable to add new record");
		}
		return response;
	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse authentication(@RequestBody String email, String password) {
		UserBean userLogin = service.auth(email, password);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (userLogin != null) {
			response.setMessage("Login succesful");
		} else {
			response.setError(true);
			response.setMessage("Failed to Login");
		}
		return response;
	}

	@PostMapping(path = "/addBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse addBook(@RequestBody BookBean bean) {
		boolean isBookAdded = service1.addBook(bean);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (isBookAdded) {
			response.setMessage("Book is added succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be added");
		}
		return response;

	}

	@PutMapping(path = "/bookUpdate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse updateBook(@RequestBody BookBean bean) {
		boolean isBookUpdated = service1.update(bean);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (isBookUpdated) {
			response.setMessage("Book is updated succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book is not updated");
		}
		return response;
	}

	@DeleteMapping(path = "/deleteBook/{bookId} ", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse deleteBook(@PathVariable(name = "bookId") int bookId) {
		boolean isBookDeleted = service1.delete(bookId);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (isBookDeleted) {
			response.setMessage("Book is deleted succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book is not deleted");
		}
		return response;
	}

	@GetMapping(path = "/getBookId", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse getBookIds() {
		List<Integer> getId = service1.getBookIds();
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (getId != null && !getId.isEmpty()) {
			response.setMessage("Book Id found");
			response.setBookBeanId(getId);
		} else {
			response.setError(true);
			response.setMessage("No book is available");
		}
		return response;
	}

	@GetMapping(path = "/getBookInfo", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse getBookInfo() {
		List<BookBean> getInfo = service1.getBooksInfo();
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (getInfo != null && !getInfo.isEmpty()) {
			response.setMessage("Book info found");
			response.setBookBeanList(getInfo);
		} else {
			response.setError(true);
			response.setMessage("No book is available");
		}
		return response;
	}

	@GetMapping(path = "/getBookByName", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse getBookByName(String bookTitle) {
		BookBean bean = service1.searchBookTitle(bookTitle);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (bean != null) {
			response.setMessage("Book info found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("No book is available");
		}
		return response;
	}

	@GetMapping(path = "/getBookByAuthor", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse getBookByAuthor(String author) {
		BookBean bean = service1.searchBookAuthor(author);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (bean != null) {
			response.setMessage("Book info found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("No book is available");
		}
		return response;
	}

	@GetMapping(path = "/getBookById", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse getBookById(int bookId) {
		BookBean bean = service1.searchBookType(bookId);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (bean != null) {
			response.setMessage("Book info found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("No book is available");
		}
		return response;
	}

	@GetMapping(path = "/issueBook/{userId}/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse issueBook(@PathVariable(name = "userId") int id,
			@PathVariable(name = "bookId") int bookId) {
		boolean isBookIssued = service1.issueBook(id, bookId);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (isBookIssued) {
			response.setMessage("Book issued succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be issued");
		}
		return response;
	}

	@GetMapping(path = "/requestBook/{userId}/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse requestBook(@PathVariable(name = "userId") int id,
			@PathVariable(name = "bookId") int bookId) {
		boolean isBookRequested = service2.req(id, bookId);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (isBookRequested) {
			response.setMessage("Book is requested succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be requested");
		}
		return response;
	}

	@GetMapping(path = "/returnBook/{userId}/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryManagementResponse returnBook(@PathVariable(name = "userId") int id,
			@PathVariable(name = "bookId") int bookId) {
		boolean isBookReturnRequested = service2.returnBook(bookId, id);
		LibraryManagementResponse response = new LibraryManagementResponse();
		if (isBookReturnRequested) {
			response.setMessage("Book return requested succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot place return request");
		}
		return response;
	}

}
