package com.transaction.zone.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long userId;

	@Getter
	@Setter
	private String userName;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String mobileNumber;

	@Getter
	@Setter
	private String profileName;

	@Getter
	@Setter
	private String password;

}
