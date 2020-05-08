package com.capgemini.librarymanagementsystemspring.dto;

import java.util.List;

import lombok.Data;

@Data
public class LibraryManagementResponse {

	private boolean error;
	private String message;
	private UserBean user;
	private List<UserBean> userBean;
	private BookBean book;
	private List<Integer> bookBeanId;
	private List<BookBean> bookBeanList;
	private BookIssueDetailsBean bookIssue;
	private RequestBookBean requestBook;
	private BorrowedBookBean borrowBook;

}
