package com.sbnz.physio.service;

import java.time.LocalDate;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.sbnz.physio.facts.Diagnosis;
import com.sbnz.physio.facts.Pain;
import com.sbnz.physio.facts.Patient;

@Service
public class PainService {
	private final KieContainer kieContainer;
	
	public PainService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public Diagnosis classifyPain(Pain pain) {
		KieSession kieSession = kieContainer.newKieSession("physio-rules");
		
		Diagnosis diagnosis = new Diagnosis();
		
		Patient patient = new Patient();
		
		Diagnosis prevDiagnosis1 = new Diagnosis();
		prevDiagnosis1.setDiagnosisDate(LocalDate.now());
		prevDiagnosis1.setIllness(Diagnosis.Illness.ACUTE_CERVICAL_SYNDROME);
		
		Diagnosis prevDiagnosis2 = new Diagnosis();
		prevDiagnosis2.setDiagnosisDate(LocalDate.now());
		prevDiagnosis2.setIllness(Diagnosis.Illness.LUMBAGO);
		
		patient.addDiagnosis(prevDiagnosis1);
		patient.addDiagnosis(prevDiagnosis2);

		kieSession.insert(pain);
		kieSession.insert(diagnosis);
		kieSession.insert(patient);
		kieSession.fireAllRules();
		
		System.out.println("Patient: \n" + patient);
		
		return diagnosis;
		
	}

}
