package com.sbnz.physio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.physio.facts.Diagnosis;
import com.sbnz.physio.facts.Pain;
import com.sbnz.physio.service.PainService;

@RestController
@RequestMapping("/api/pain")
public class PainController {
	
	@Autowired 
	PainService painService;
	
	@GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Diagnosis> testWorks() {
		Pain testPain = new Pain();
		
		/*testPain.getSymptoms().add(Pain.Symptoms.NECK_PAIN_EXTENDING_INTO_NAPE);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.EXTENDED_POSITION_PAIN);
		
		testPain.getPrecursors().add(Pain.Precursors.COLD_TEMPERATURES);*/
		
		/*//test za Primary cervical syndrome A
		testPain.getSymptoms().add(Pain.Symptoms.NECK_PAIN_EXTENDING_INTO_NAPE);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.EXTENDED_POSITION_PAIN);
		testPain.getPrecursors().add(Pain.Precursors.PROLONGED_UNCONFORTABLE_POSITION);
		testPain.getPrecursors().add(Pain.Precursors.BAD_POSTURE);*/
		
		/*//test za Primary cervical syndrome B
		testPain.getSymptoms().add(Pain.Symptoms.NECK_PAIN_EXTENDING_INTO_NAPE);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.EXTENDED_POSITION_PAIN);
		
		testPain.getPrecursors().add(Pain.Precursors.COLD_TEMPERATURES);*/
		
		
		/*//test za Cervical spondylosis A
		testPain.getSymptoms().add(Pain.Symptoms.NECK_PAIN_EXTENDING_INTO_NAPE);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.EXTENDED_POSITION_PAIN);
		testPain.getPrecursors().add(Pain.Precursors.PROLONGED_DEHIDRATATION);*/
		
		
		//test za Cervical spondylosis B
		/*testPain.getSymptoms().add(Pain.Symptoms.NECK_PAIN_EXTENDING_INTO_NAPE);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.EXTENDED_POSITION_PAIN);
		testPain.getPrecursors().add(Pain.Precursors.MYOGELOSIS);*/
		
		//test za Thoracic syndrom A
		/*testPain.getSymptoms().add(Pain.Symptoms.NECK_TO_BACK_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.SCAPULA_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getPrecursors().add(Pain.Precursors.BAD_POSTURE);*/
		
		
		//test za Thoracic syndrom B dodati u servisu jos akutni cervikalni i primarni cervikalni
		/*testPain.getSymptoms().add(Pain.Symptoms.NECK_TO_BACK_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.SCAPULA_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getPrecursors().add(Pain.Precursors.BACK_EXTERTION);*/
	
		
		//test za Thoracic spondylosis A
		/*testPain.getSymptoms().add(Pain.Symptoms.NECK_TO_BACK_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.SCAPULA_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getPrecursors().add(Pain.Precursors.MYOGELOSIS);*/
		
		
		//test za Thoracic spondylosis B
		/*testPain.getSymptoms().add(Pain.Symptoms.NECK_TO_BACK_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.SCAPULA_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.RIB_TO_SPINE_JOINT_PAIN);*/

		
		//test za Thoracic discus hernia A
		/*testPain.getSymptoms().add(Pain.Symptoms.SHARP_THORACAL_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.THORACAL_NUMBNESS);
		testPain.getSymptoms().add(Pain.Symptoms.LEG_PAIN_AND_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.MOVEMENT_PAIN);*/
		
		//test za Thoracic discus hernia B
		/*testPain.getSymptoms().add(Pain.Symptoms.SHARP_THORACAL_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.THORACAL_NUMBNESS);
		testPain.getSymptoms().add(Pain.Symptoms.LEG_PAIN_AND_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.MOVEMENT_PAIN);*/
		
		
		//test za Lumbal syndrome
		/*testPain.getSymptoms().add(Pain.Symptoms.LOIN_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.EXTERTION_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.SHARP_LUMBAL_SACRAL_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.MOVEMENT_PAIN);
		testPain.getPrecursors().add(Pain.Precursors.OBESITY);*/
		
		
		testPain.getSymptoms().add(Pain.Symptoms.REST_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.LUMBAL_SPINE_DEGENERATION);
		testPain.getSymptoms().add(Pain.Symptoms.MOVEMENT_PAIN); 
		testPain.getSymptoms().add(Pain.Symptoms.LEG_IMMOBILITY);
		testPain.getSymptoms().add(Pain.Symptoms.LEG_ELECTRIC_PAIN);
		//testPain.getPrecursors().add(Pain.Precursors.LACK_PHYSICAL_ACTIVITY);
		//testPain.getPrecursors().add(Pain.Precursors.BACK_STRAINING_PROFESSION);
		
		Diagnosis diagnosis = painService.classifyPain(testPain);
		
		System.out.println("Localized at: " + testPain.getPainLocalization());
		System.out.println("Type: " + testPain.getPainType());
		System.out.println("\n\nDiagnosis:\n\n" + diagnosis);
		
		return new ResponseEntity<>(diagnosis, HttpStatus.OK);
	}

}