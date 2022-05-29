package com.sbnz.physio.facts;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {
	private Long id;
	private String LBO;
	private String name;
	private String surname;
	private List<Diagnosis> diagnoses = new ArrayList<>();
	
	public void addDiagnosis(Diagnosis diagnosis) {
		this.diagnoses.add(diagnosis);
	}
}
