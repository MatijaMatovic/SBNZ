package com.sbnz.physio.facts;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Ova klasa ne mora da se cuva u bazi, sluzi samo da se prikaze korisniku */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {
	
	private Diagnosis diagnosis;
	private Patient patient;
	private List<Therapy> therapies = new ArrayList<>();
	private List<Excercise> excerciseRoutine = new ArrayList<Excercise>();
	
	public void addExcercise(Excercise excercise) {
		this.excerciseRoutine.add(excercise);
	}
}
