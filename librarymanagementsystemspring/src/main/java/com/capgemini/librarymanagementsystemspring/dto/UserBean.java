package com.capgemini.librarymanagementsystemspring.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserBean {
	@Id
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private int id;
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private long mobile;
	@Column
	private String role;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "primary", fetch = FetchType.EAGER)
	private List<RequestBookBean> reqBook;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "primary")
	private List<BookIssueDetailsBean> bookIssue;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "primary")
	private List<BorrowedBookBean> borrowBook;

}
