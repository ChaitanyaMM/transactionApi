package com.transaction.zone.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Data
@Entity
@Table(name = "user")

public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter private Long userId;
	
	@Getter @Setter private String userName;
	
	@Getter @Setter private String email;
	 
	@Getter @Setter private String mobileNumber;
	
	@Getter @Setter private String profileName;
	
	@Getter @Setter private String password;
	 
	


}
