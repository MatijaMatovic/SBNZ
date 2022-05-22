package com.sbnz.physio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.physio.facts.Pain;
import com.sbnz.physio.service.PainService;

@RestController
@RequestMapping("/api/pain")
public class PainController {
	
	@Autowired 
	PainService painService;
	
	@GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pain> testWorks() {
		Pain testPain = new Pain();
		
		testPain.getSymptoms().add(Pain.Symptoms.NECK_PAIN_EXTENDING_INTO_NAPE);
		testPain.getPrecursors().add(Pain.Precursors.BACK_STRAINING_PROFESSION);
		testPain.getPrecursors().add(Pain.Precursors.BAD_POSTURE);
		
		painService.classifyPain(testPain);
		
		System.out.println("Localized at: " + testPain.getPainLocalization());
		System.out.println("Type: " + testPain.getPainType());
		
		return new ResponseEntity<Pain>(testPain, HttpStatus.OK);
	}

}
