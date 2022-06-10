package com.sbnz.physio.facts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
@Entity
public class Treatment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
	private Diagnosis diagnosis;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_lbo", referencedColumnName = "LBO")
	private Patient patient;
	
	@ManyToMany
	@JoinTable(
			name = "treatment_therapies", 
			joinColumns = @JoinColumn(name="treatment_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="therapy_id", referencedColumnName = "id")
	)
	private List<Therapy> therapies = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
			name = "treatment_excercises",
			joinColumns = @JoinColumn(name = "treatment_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "excercise_id", referencedColumnName = "id")
	)
	private List<Excercise> excerciseRoutine = new ArrayList<>();
	
	
	
	public Treatment(Diagnosis diagnosis, Patient patient, List<Therapy> therapies, List<Excercise> excerciseRoutine) {
		super();
		this.id = null;
		this.diagnosis = diagnosis;
		this.patient = patient;
		this.therapies = therapies;
		this.excerciseRoutine = excerciseRoutine;
	}
	
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
