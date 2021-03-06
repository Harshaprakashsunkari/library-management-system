package com.capgemini.librarymanagementsystemhibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="bookdetails")
public class BookBean {
	@Id
	@Column(unique=true, updatable = false)
	private int bid;
	@Column
	private String book_title;
	@Column(updatable = false)
	private String category;
	@Column(updatable = false)
	private String author;
	@Column(updatable = false)
	private String book_publisher;
	@Column(updatable = false)
	private String publisher_name;
	@Column(updatable = false)
	private int copies;

}









