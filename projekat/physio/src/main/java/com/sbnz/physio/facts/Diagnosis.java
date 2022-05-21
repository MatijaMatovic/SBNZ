package com.sbnz.physio.facts;

import java.util.Date;

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
@EqualsAndHashCode
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
	
	@EqualsAndHashCode.Include
	private Long id;
	private Date diagnosisDate;
	private Pain pain;
	private Illness illness;
	private PainIntensity painIntensity;
	

}
