package com.capgemini.librarymanagementsystemhibernate.controller;

import java.util.Scanner;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAOImp;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;
import com.capgemini.librarymanagementsystemhibernate.factory.AdminFactory;
import com.capgemini.librarymanagementsystemhibernate.factory.StudentFactory;
import com.capgemini.librarymanagementsystemhibernate.factory.UserFactory;
import com.capgemini.librarymanagementsystemhibernate.service.AdminService;
import com.capgemini.librarymanagementsystemhibernate.service.StudentService;
import com.capgemini.librarymanagementsystemhibernate.service.UserService;

public class HibernateController {
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

				UserBean bean = new UserBean();
				bean.setEmail("harsha@gmail.com");
				bean.setId(56);
				bean.setMobile(981761780);
				bean.setName("Harsha");
				bean.setPassword("Harsha@123");
				bean.setRole("admin");
				try {
					boolean check = service1.register(bean);
					if(check==false) {
						System.out.println("Email already exist");

					} else {
						System.out.println("Registered successfully");

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
					UserBean auth = service1.auth(email, password);

					if(auth.getRole().equalsIgnoreCase("admin")) {

						admin();

					}else if(auth.getRole().equalsIgnoreCase("student")){ 
						student();
					}


					else {
						System.out.println("Invalid Email or Password");
					}
				}catch(Exception e) {
					e.printStackTrace();
				}

				break;
			}
		}while(true);	
	}

	public static void admin() {

		UserService service1 = UserFactory.getUserService();
		AdminService service = AdminFactory.getAdminService();

		BookBean bean = new BookBean();
		AdminDAO dao = new AdminDAOImp();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Press 1 to Add Books");
		System.out.println("Press 2 to remove the Books");
		System.out.println("Press 3 to issue book");
		System.out.println("Press 4 to main");

		int choice1 = scanner.nextInt();
		switch (choice1) {

		case 1:

			System.out.println("Enter Book Id");
			int bookId = scanner.nextInt();
			System.out.println("Enter Book Title"); 
			String bookTitle = scanner.next();
			System.out.println("Enter Category");
			String category = scanner.next();
			System.out.println("Enter Author"); 
			String author = scanner.next();
			System.out.println("Enter Publisher Name");
			String PubName =  scanner.next();
			System.out.println("Enter number of copies");
			int copies = scanner.nextInt();
			
			bean.setBid(bookId);
			bean.setBook_title(bookTitle);
			bean.setCategory(category);
			bean.setAuthor(author);
			bean.setPublisher_name(PubName);
			bean.setCopies(copies);
			
			boolean check = service.addBook(bean);
			if(check == false) {
				System.out.println("Book already exists");
			} else {
				System.out.println("Book added successfully");
			}
			break;

		case 2:

			System.out.println("Enter Book_Id");
			int book_Id = scanner.nextInt();
			if (book_Id == 0) {
				System.out.println("Enter the Valid Book Id");
			} else {
				BookBean bean6 = new BookBean();

				bean6.setBid(book_Id);
				boolean remove = service.remove(book_Id);
				if (remove == false) {
					System.out.println("Book is not removed");
				} else {
					System.out.println("Book is removed successfully");
				}
			}
			break;

		case 3 :

			System.out.println("Enter Id");
			int stId = scanner.nextInt();
			System.out.println("Enter Book Id");
			int issuedId=scanner.nextInt();
			boolean check4=service.issueBook(stId, issuedId);
			if(check4) {
				System.out.println("Book Issued successfully");
			}else {
				System.out.println("Book is not issued");
			}
			break;

		case 4 :
			doReg();
		}
	}
	public static void student() {
		Scanner scanner = new Scanner(System.in);
		StudentService service2 = StudentFactory.getStudentService();

		do {
			try {		
				System.out.println("Press 1 to request book");
				System.out.println("Press 2 to Search Book by Id");
				System.out.println("Press 3 to return book");
				System.out.println("Press 4 to main");

				int choice2 = scanner.nextInt();

				switch (choice2) {

				case 1:

					System.out.println("Enter Id");
					int stuId1 = scanner.nextInt();
					System.out.println("Enter Book Id");
					int reqBook = scanner.nextInt();
					System.out.println("enter id");
					String  reqEmail = scanner.next();
					boolean book = service2.req(stuId1, reqBook);
					if(book == true) {
						System.out.println("Requested successfully");
					}else {
						System.out.println("Book not found");
					}
					break;
					
				case 2:

					System.out.println("Enter Book id");
					int id = scanner.nextInt();
					BookBean bean5 = new BookBean();
					bean5.setBid(id);
					BookBean list2 = service2.searchBookType(id);
					if(list2!=null) {
						System.out.println(list2.getBid());
						System.out.println(list2.getBook_title());
						System.out.println(list2.getCategory());
						System.out.println(list2.getAuthor());
						System.out.println(list2.getCopies());
						System.out.println(list2.getPublisher_name());
						System.out.println(list2);
					} else {
						System.out.println("Book Not Found");
					}
					break;
					
				case 3:

					System.out.println("Enter Id");
					int stuId = scanner.nextInt();
					System.out.println("Enter Book Id");
					int book_id = scanner.nextInt();
					boolean returnBook = service2.returnBook(stuId, book_id);
					if(returnBook) {
						System.out.println("Book returned successfully");
					}else {
						System.out.println("Book not returned");
					}
					break;


				case 4:
					doReg();

				default:
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Credentials");
			}
			break;
		}while(true);
	}
}
