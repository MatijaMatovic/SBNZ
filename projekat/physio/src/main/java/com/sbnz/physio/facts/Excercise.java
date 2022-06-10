package com.sbnz.physio.facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class Excercise {
	public enum Type {
		DYNAMIC, STATIC, REST
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private Long orderNo;
	
	@Enumerated(EnumType.STRING)
	private Type excerciseType;
	
	@Column(nullable = false)
	private Integer repetitions;
	
	@Column(nullable = false)
	private String name;
	

	public Excercise(Long orderNo, Type excerciseType, Integer repetitions, String name) {
		super();
		this.id = null;
		this.orderNo = orderNo;
		this.excerciseType = excerciseType;
		this.repetitions = repetitions;
		this.name = name;
	}
	
	

}
