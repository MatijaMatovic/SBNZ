package com.sbnz.physio.facts;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pain {
	
	public enum SimplePainIntensity { MILD, MEDIUM, STRONG}
	
	public enum PainLocalization {
		CERVICAL, THORACAL, LUMBAL
	}
	
	public enum PainType {
		NEUROLOGICAL, MECHANICAL
	}
	
	public enum Symptoms {
		MUSCLE_SPASM,
		REST_PAIN,
		MOVEMENT_PAIN,
		EXTERTION_PAIN,
		EXTENDED_POSITION_PAIN,
		NECK_PAIN_EXTENDING_INTO_NAPE,
		SHARP_NON_EXTENDING_NECK_PAIN,
		SHOULDER_AND_ARM_ELECTRIC_PAIN,
		ARM_AND_HAND_NUMBNESS,
		WEAK_UNCOORDINATED_HANDS,
		NECK_TO_BACK_PAIN,
		SCAPULA_PAIN,  //lopatice
		SHARP_THORACAL_PAIN,
		BREATHING_PAIN,
		RIB_TO_SPINE_JOINT_PAIN,
		LEG_PAIN_AND_SPASM,
		THORACAL_NUMBNESS,
		LOIN_PAIN,
		SHARP_LUMBAL_SACRAL_PAIN,
		LUMBAL_SACRAL_IMMOBILITY,
		LEG_IMMOBILITY,
		LEG_SPREADING_SYMPTOMS,
		LEG_ELECTRIC_PAIN,
		LEG_TINGLING_BURNING,
		LUMBAL_SPINE_DEGENERATION,
		
		SHOULDER_IMMOBILLITY,
		SHOULDER_STIFFNESS,
		SCAPULA_IMMOBILLITY,
		RIB_PAIN,
		WHOLE_SPINE_PAIN,
		ROTATIONAL_IMMOBILLITY,
		SIDE_EXTENSION_PAIN,
		TRAPS_TIGHTNESS,
		TEMPLE_PAIN,
		HEAD_TOP_PAIN,
		STOMACH_PAIN,
		SUDDEN_DEEP_PAIN
	}
	
	public enum Precursors {
		INTENSE_SUDDEN_MOVEMENT,
		HOT_COLD_TEMPERATURE_CHANGE,
		COLD_TEMPERATURES,
		BAD_POSTURE,
		LACK_PHYSICAL_ACTIVITY,
		MYOGELOSIS,
		NECK_EXTERTION,
		BACK_EXTERTION,
		KYPHO_SCOLIOSIS,
		UNDEVELOPED_THORACAL_MUSCULATURE,
		OBESITY,
		BACK_STRAINING_PROFESSION,
		
		PROLONGED_UNCONFORTABLE_POSITION,
		PROLONGED_DEHIDRATATION
	}
	
	private Long id;
	private PainType painType;
	private PainLocalization painLocalization;
	private SimplePainIntensity simplePainIntensity;
	private List<Symptoms> symptoms = new ArrayList<Symptoms>();
	private List<Precursors>  precursors = new ArrayList<Precursors>();
	

	@Override
	public String toString() {
		String symptomsString = "";
		for (Symptoms s : this.symptoms) {
			String symptomString = "\n\t-" + s.toString();
			symptomsString += symptomString;
		}
		
		String precursorsString = "";
		for (Precursors s : this.precursors) {
			String precursorString = "\n\t-" + s.toString();
			precursorsString += precursorString;
		}
		
				
		return "Pain Type: " + painType + "\nPain Localization: " + painLocalization 
						+ "\nSymptoms: " + symptomsString + "\nPrecursors: " + precursorsString;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pain other = (Pain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean hasSymptom(Symptoms symptom) {
		return this.symptoms.contains(symptom);
	}
	
	
	
	
	
	
	
	

}
