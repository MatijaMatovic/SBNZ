package com.sbnz.physio.facts;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Excercise {
	public enum Type {
		DYNAMIC, STATIC, REST
	}
	
	@EqualsAndHashCode.Include
	private Long orderNo;
	private Type excerciseType;
	private Integer repetitions;
	private String name;

}
