package com.sbnz.physio.facts;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* Ova klasa ne mora da se cuva u bazi, sluzi samo da se prikaze korisniku */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Treatment {
	
	private Diagnosis diagnosis;
	@ToString.Exclude
	private Patient patient;
	private List<Therapy> therapies = new ArrayList<>();
	private List<Excercise> excerciseRoutine = new ArrayList<>();
	
	public void addExcercise(Excercise excercise) {
		this.excerciseRoutine.add(excercise);
	}
	
	public void addTherapy(Therapy therapy) {
		this.therapies.add(therapy);
	}
	
	public void setPatient(Patient p) {
		this.patient = p;
		p.addTreatment(this);
	}
}
