package com.sbnz.physio.controller;

import com.sbnz.physio.facts.Excercise;
import com.sbnz.physio.facts.ExcerciseEvent;
import com.sbnz.physio.facts.ExerciseReplacement;
import com.sbnz.physio.service.PainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excercise")
public class ExcerciseController {
	@Autowired 
	PainService painService;
	
	@GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExerciseReplacement> testWorks(@RequestBody ExcerciseEvent e1) {
	
		//ExcerciseEvent e1 = new ExcerciseEvent(ExcerciseEvent.PainType.STIFFNESS, Excercise.Type.DYNAMIC, "trbusni mislici");
		ExerciseReplacement er = painService.classifyExcercise(e1);
		
		return new ResponseEntity<>(er, HttpStatus.OK);
	}
	
}
