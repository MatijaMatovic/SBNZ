package com.sbnz.physio.dto;

import com.sbnz.physio.facts.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDTO {

	private String LBO;
	private String name;
	private String surname;	
	
	public PatientDTO(Patient patient) {
		this.LBO = patient.getLBO();
		this.name = patient.getName();
		this.surname = patient.getSurname();
	}
	
	public Patient convertToPatient() {
		Patient patient = new Patient();
		
		patient.setLBO(LBO);
		patient.setName(name);
		patient.setSurname(surname);
		
		return patient;
	}
}
