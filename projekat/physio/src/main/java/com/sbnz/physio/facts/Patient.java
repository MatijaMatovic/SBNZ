package com.sbnz.physio.facts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String LBO;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "patient_diagnoses",
			joinColumns = @JoinColumn(name = "patient_lbo", referencedColumnName = "LBO"),
			inverseJoinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
	)
	private List<Diagnosis> diagnoses = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private List<Treatment> treatments = new ArrayList<>();
	
	public void addDiagnosis(Diagnosis diagnosis) {
		this.diagnoses.add(diagnosis);
	}
	
	public void addTreatment(Treatment treatment) {
		if (treatment.getPatient() == null)
			treatment.setPatient(this);
		
		this.treatments.add(treatment);
	}
}
