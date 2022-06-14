package com.sbnz.physio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.physio.events.ExcerciseEvent;
import com.sbnz.physio.events.InjuryEvent;
import com.sbnz.physio.facts.ExerciseReplacement;
import com.sbnz.physio.service.CEPService;

@RestController
@RequestMapping("/api/cep")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CEPController {
	
	@Autowired
	CEPService cepService;
	
	@PostMapping(value = "/injury", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExerciseReplacement> reportInjury(@RequestBody InjuryEvent injuryEvent) {
		ExerciseReplacement replacement = cepService.registerInjury(injuryEvent);
		
		return new ResponseEntity<>(replacement, HttpStatus.OK);
	}
	
	@PostMapping(value = "/event", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExerciseReplacement> reportEvent(@RequestBody ExcerciseEvent excerciseEvent) {
		ExerciseReplacement replacement = cepService.registerExcerciseEvent(excerciseEvent);
		
		return new ResponseEntity<>(replacement, HttpStatus.OK);
	}
	
	@PostMapping(value = "/exhaustion")
	public void reportExhaustion() {
		cepService.registerExhaustion();
	}
}
