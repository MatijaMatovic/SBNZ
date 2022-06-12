package com.sbnz.physio.controller;


import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;

import com.sbnz.physio.facts.Pain;
import com.sbnz.physio.facts.Patient;
import com.sbnz.physio.facts.Treatment;
import com.sbnz.physio.service.PainService;
import com.sbnz.physio.service.PatientService;

@RestController
@RequestMapping("/api/pain")
public class PainController {
	
	@Autowired 
	PainService painService;
	
	@Autowired
	PatientService patientService;
	
	@PutMapping(value = "/diagnose/{lbo}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Treatment> classifyPain(@PathVariable String lbo, @RequestBody Pain pain) {
		Patient patient;
		
		try {
			patient = patientService.findByLBO(lbo);			
		}
		catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		Treatment treatment = painService.classifyPain(pain, patient);
		
		patientService.save(patient); // Jer su mu dodate terapija i dijagnoza u listu
		
		return new ResponseEntity<>(treatment, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Treatment> testWorks() {
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
		
		
		//test za Cervical spondylosis A
		testPain.getSymptoms().add(Pain.Symptoms.NECK_PAIN_EXTENDING_INTO_NAPE);
		testPain.getSymptoms().add(Pain.Symptoms.MUSCLE_SPASM);
		testPain.getSymptoms().add(Pain.Symptoms.EXTENDED_POSITION_PAIN);
		testPain.getPrecursors().add(Pain.Precursors.BAD_POSTURE);
		
		
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
		
		
		/*testPain.getSymptoms().add(Pain.Symptoms.REST_PAIN);
		testPain.getSymptoms().add(Pain.Symptoms.LUMBAL_SPINE_DEGENERATION);
		testPain.getSymptoms().add(Pain.Symptoms.MOVEMENT_PAIN); 
		testPain.getSymptoms().add(Pain.Symptoms.LEG_IMMOBILITY);
		testPain.getSymptoms().add(Pain.Symptoms.LEG_ELECTRIC_PAIN);*/
		testPain.setSimplePainIntensity(Pain.SimplePainIntensity.STRONG);
		//testPain.getPrecursors().add(Pain.Precursors.LACK_PHYSICAL_ACTIVITY);
		//testPain.getPrecursors().add(Pain.Precursors.BACK_STRAINING_PROFESSION);
		
		Treatment treatment = painService.testClassifyPain(testPain);
		
		System.out.println("Localized at: " + testPain.getPainLocalization());
		System.out.println("Type: " + testPain.getPainType());
		//System.out.println("\n\nDiagnosis:\n\n" + diagnosis);
		
		return new ResponseEntity<>(treatment, HttpStatus.OK);
	}

}
