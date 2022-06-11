package com.sbnz.physio.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.physio.facts.Patient;
import com.sbnz.physio.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public Patient findByID(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Patient with such ID"));
	}
	
	public Patient findByLBO(String LBO) {
		return patientRepository.findByLBO(LBO).orElseThrow(() -> new EntityNotFoundException("No Patient with such LBO"));
	}
}
