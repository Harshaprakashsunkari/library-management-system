package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementException;

public class StudentDAOImp implements StudentDAO{
	Scanner scan = new Scanner(System.in);

	@Override
	public boolean req(int bookId,String email) {

		AdminDAOImp imp = new AdminDAOImp();
		UserDAOImp dao1 = new UserDAOImp(); 
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
			pro.load(fin);
			BookBean b = new BookBean();
			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
				String query = pro.getProperty("req_book");
				try(PreparedStatement pstmt = conn.prepareStatement(query)){	
					pstmt.setInt(1, bookId);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {

					}
					return true;
				}
			}
		}
		catch (LibraryManagementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public BookBean searchBookType(int bookType) {

		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
				String query = pro.getProperty("search_book_id");
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setInt(1, bookType);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {	
						bean.setBookId(rs.getInt("bookId"));
						bean.setBookName(rs.getString("book_title"));
						bean.setBookAuthor(rs.getString("author"));
						bean.setBookCategory(rs.getString("category"));
						bean.setBookPublisher(rs.getString("publisher"));
						return bean;
					} else {
						System.out.println("book not found");
					}
				}
			}
		}catch (LibraryManagementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean returnBook(int bookId) {

		System.out.println(bookId);
		//System.out.println("return book called");
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("db.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro.getProperty("user"),pro.getProperty("password"))){
				String query = pro.getProperty("return_book");
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setInt(1,bookId);
					int returned_books = pstmt.executeUpdate();
					if(returned_books!=0) {
						System.out.println("Book is returned successfully");
						return true;
					}else {
						System.out.println("Book not returned");	
						return false;
					}
				}
			}		
		}
		catch (LibraryManagementException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}


