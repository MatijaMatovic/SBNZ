package com.sbnz.physio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.physio.facts.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findById(Long id);
	
	Optional<Patient> findByLBO(String LBO);
}
