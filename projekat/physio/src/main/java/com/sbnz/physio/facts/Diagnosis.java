package com.sbnz.physio.facts;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
public class Diagnosis {
	public enum PainIntensity { MILD, MEDIUM, STRONG, WORSENING }
	
	public enum Illness {
		ACUTE_CERVICAL_SYNDROME,
		PRIMARY_CERVICAL_SYNDROME,
		CERVICAL_SPONDYLOSIS,
		CERVICAL_DISCUS_HERNIA,
		THORACIC_SYNDROME,
		THORACIC_SPONDYLOSIS,
		THORACIC_DISCUS_HERNIA,
		LUMBAL_SYNDROME,
		LUMBAGO,
		SCIATICA,
		LUMBAL_DISCUS_HERNIA
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate diagnosisDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pain_id", referencedColumnName = "id")
	private Pain pain;
	
	@Enumerated(EnumType.STRING)
	private Illness illness;
	
	@Enumerated(EnumType.STRING)
	private PainIntensity painIntensity;
	

}
