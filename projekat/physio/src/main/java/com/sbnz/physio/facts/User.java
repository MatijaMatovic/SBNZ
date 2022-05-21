package com.sbnz.physio.facts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
	private Long id;
	private String username;
	private String password;
	private String email;

}
