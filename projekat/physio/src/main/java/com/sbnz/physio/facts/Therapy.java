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

/* Ova klasa ne mora da se cuva u klasi, sluzi samo da se prikaze korisniku */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Therapy {
	public enum TherapyType { 
		ELECTRO_PHORESYS_NOVOCAINE, 
		ELECTRO_PHORESYS_B_VIT,
		ELECTRO_PHORESYS_SODIUM,
		ULTRASOUD, 
		DIODYNAMIC_ELLECTROCURRENTS ,
		INTERFERENCE_CURRENTS,
		MASSAGE,
		CHIROPRACTIC_ADJUSTMENT,
		BIOPTRON_LAMP,
		LASER,
		MAGNETOTHERAPY,
		NECK_COLAR,
		PARAFFIN,
		TENS_ELECTROTHERAPY,
		REST,
		SURGERY
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TherapyType therapyType;
	
	@Column(nullable = false)
	private Integer sessions;
	
	@Column(nullable = false)
	private String locality;
	
	
	
	public Therapy(TherapyType therapyType, Integer sessions, String locality) {
		super();
		this.id = null;
		this.therapyType = therapyType;
		this.sessions = sessions;
		this.locality = locality;
	}
	
	
	@Override
	public String toString() {
		
		return "new Therapy(TherapyType."+therapyType+", "+String.valueOf(sessions)+", \""+locality+"\")";
	}






}
