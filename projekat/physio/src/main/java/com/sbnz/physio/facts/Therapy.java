package com.sbnz.physio.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* Ova klasa ne mora da se cuva u klasi, sluzi samo da se prikaze korisniku */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	private TherapyType therapyType;
	private Integer sessions;
	private String locality;
	@Override
	public String toString() {
		
		return "new Therapy(TherapyType."+therapyType+", "+String.valueOf(sessions)+", \""+locality+"\")";
	}

}
