package com.capgemini.librarymanagementsystemspring.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@Table(name = "request_details")
@JsonDeserialize
public class RequestBookBean {
	@EmbeddedId
	private RequestBookPK reqBookPK;

	@Column
	private int id;
	@Column
	private String name;
	@Column
	private String book_title;

	@Column
	private String type;

	@Exclude

	@ManyToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "email", insertable = false, updatable = false)
	private UserBean primary;

}
