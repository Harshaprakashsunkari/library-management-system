package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.Scanner;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemjdbc.factory.AdminFactory;
import com.capgemini.librarymanagementsystemjdbc.factory.StudentFactory;
import com.capgemini.librarymanagementsystemjdbc.factory.UserFactory;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;

public class JdbcController {
	public static void main(String[] args) {
		doReg();
	}

	public static void doReg() {

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Press 1 to Register");
			System.out.println("Press 2 to Login");
			UserService service1 = UserFactory.getUserService();
			AdminService service = AdminFactory.getAdminService();

			int i = scanner.nextInt();
			switch(i) {
			case 1:

				System.out.println("Enter Name");
				String regName = scanner.next();
				System.out.println("Enter Email");
				String regEmail = scanner.next();
				System.out.println("Enter Password");
				String regPassword = scanner.next();
				System.out.println("Enter Mobile");
				long regMobile = scanner.nextLong();
				System.out.println("Enter Role");
				String regRole = scanner.next();
				System.out.println("Enter ID");
				int regID = scanner.nextInt();

				UserBean bean = new UserBean();
				bean.setId(regID);
				bean.setName(regName);
				bean.setMobile(regMobile);
				bean.setEmail(regEmail);
				bean.setPassword(regPassword);
				bean.setRole(regRole);
				try {
					int check = service1.register(bean);
					if(check == 0) {
						System.out.println("Email already exist");

					} else {
						System.out.println("Registered succesfully");

					}}catch(Exception e) {
						System.out.println("Invalid");
					}

				break;
			case 2:

				System.out.println("Enter Email");
				String email = scanner.next();
				System.out.println("Enter Password");
				String password = scanner.next();
				try {
					String auth = service1.auth(email, password);

					if(auth.equalsIgnoreCase("admin")) {

						admin();

					}else if(auth.equalsIgnoreCase("student")){
						student();
					}

					else {
						System.out.println("Invalid Email or Password");
					}
				}catch(Exception e) {
					System.out.println("Invalid Credentials");
				}

				break;
			}
		}while(true);	
	}



	public static void admin() {
		UserService service1 = UserFactory.getUserService();
		AdminService service = AdminFactory.getAdminService();

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Press 1 to Add a Book");
			System.out.println("Press 2 to Remove a Book");
			System.out.println("Press 3 to Issue book");
			System.out.println("Press 4 to Main");

			int choice1 = scanner.nextInt();
			switch (choice1) {
			case 1:

				System.out.println("Enter ID");
				int bookId = scanner.nextInt();
				System.out.println("Enter Book Name"); 
				String bookName = scanner.next();
				System.out.println("Enter Author Name"); 
				String bookAuthor = scanner.next();
				System.out.println("Enter Category");
				String bookCategory = scanner.next();
				System.out.println("Enter Publisher Name");
				String bookPubName =  scanner.next();

				BookBean bean2 = new BookBean();

				bean2.setBookId(bookId); 
				bean2.setBookName(bookName);
				bean2.setBookCategory(bookCategory);
				bean2.setBookAuthor(bookAuthor);
				bean2.setBookPublisher(bookPubName);

				int check = service.addBook(bean2);
				if(check==0) {
					System.out.println("Book already exists");
				} else {
					System.out.println("Book is added successfully");
				}
				break;


			case 2:
				System.out.println("Enter Book Id");
				int book_Id = scanner.nextInt();
				if (book_Id == 0) {
					System.out.println("Enter the Valid Book Id");
				} else {
					BookBean bean6 = new BookBean();

					bean6.setBookId(book_Id);
					int removed = service.remove(book_Id);
					if (removed == 0) {
						System.out.println("Book is not removed");
					} else {
						System.out.println("Book is removed successfully");
					}
				}

				break;

			case 3:
				System.out.println("Enter Book Id");
				int issueBook = scanner.nextInt();
				System.out.println("Enter Student Email Id");
				String issueStudentMail = scanner.next();
				boolean isIssued = service.issueBook(issueBook,issueStudentMail);
				if(isIssued) {
					System.out.println("Book is Issued successfully");

				}
				else {
					System.out.println("Book is not Issued");
				}

				break;

			}
		}while(true);
	}

	public static void student() {

		Scanner scanner = new Scanner(System.in);
		UserService service1 = UserFactory.getUserService();
		AdminService service = AdminFactory.getAdminService();
		StudentService service2 = StudentFactory.getStudentService();
		StudentDAOImp dao = new StudentDAOImp();
		AdminDAOImp adao = new AdminDAOImp();
		UserDAOImp udao = new UserDAOImp();
		do {
			try {

				System.out.println("Press 1 to Request a Book");
				System.out.println("Press 2 to Search Book by Id");
				System.out.println("Press 3 to Return the Book");
				System.out.println("Press 4 to main");


				int choice2 = scanner.nextInt();
				switch (choice2) {

				case 1:

					System.out.println("Enter Book Id"); 
					int reqBook = scanner.nextInt();

					boolean book = service2.req(reqBook,null); 
					if(book) {

						adao.issueBook(reqBook, null);
						System.out.println("Book is Requested Successfully");
					}else {
						System.out.println("Book Not Found");
					}

					break;

				case 2:

					System.out.println("Enter Book Id");
					int id = scanner.nextInt();
					BookBean bean5 = new BookBean();
					bean5.setBookId(id);
					BookBean list2 = service2.searchBookType(id);
					if(list2!=null) {
						System.out.println(list2.getBookId());
						System.out.println(list2.getBookName());
						System.out.println(list2.getBookAuthor());
						System.out.println(list2.getBookCategory());
						System.out.println(list2.getBookPublisher());
						System.out.println(list2);
					} else {
						System.out.println("Book Not Found");
					}
					break;

				case 3:
					System.out.println("Enter Id");
					int returned = scanner.nextInt();
					boolean returnBook = service2.returnBook(returned);
					if(returnBook) {
						System.out.println("Book is returned");
					}else {
						System.out.println("Book is not returned");
					}
					break;

				case 4:
					doReg();
					break;
				default:
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Credentials");
			}
			//break;

		}while(true);

	}	
}



