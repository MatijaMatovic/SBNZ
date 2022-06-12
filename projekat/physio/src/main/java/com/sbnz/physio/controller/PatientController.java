package com.sbnz.physio.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.physio.dto.PatientDTO;
import com.sbnz.physio.facts.Patient;
import com.sbnz.physio.service.PatientService;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@GetMapping(value = "/one/{lbo}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> getByLbo(@PathVariable String lbo) {
		Patient patient;
		try {
			patient = patientService.findByLBO(lbo);
		}
		catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No patient with such LBO");
		}
		
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
		Patient newPatient = patientDTO.convertToPatient();
		
		Patient saved = patientService.save(newPatient);
		
		return new ResponseEntity<PatientDTO>(new PatientDTO(saved), HttpStatus.OK);
	}
	
	@PutMapping(value = "/edit",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PatientDTO> editPatient(@RequestBody PatientDTO patientDTO) {
		Patient patient;
		try {
			patient = patientService.findByLBO(patientDTO.getLBO());

		}
		catch (EntityNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

		patient.setName(patientDTO.getName());
		patient.setSurname(patientDTO.getSurname());

		patient = patientService.save(patient);

		return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
	}
	
	@GetMapping(value = "/all"
	)
	public ResponseEntity<List<Patient>> editPatient() {
		return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
	}

}
