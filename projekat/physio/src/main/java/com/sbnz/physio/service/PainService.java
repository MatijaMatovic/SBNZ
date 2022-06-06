package com.sbnz.physio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.glassfish.jersey.server.monitoring.RequestEvent.ExceptionCause;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.sbnz.physio.facts.Diagnosis;
import com.sbnz.physio.facts.Excercise;
import com.sbnz.physio.facts.Diagnosis.PainIntensity;
import com.sbnz.physio.facts.ExcerciseEvent;
import com.sbnz.physio.facts.ExerciseReplacement;
import com.sbnz.physio.facts.Pain;
import com.sbnz.physio.facts.Pain.PainType;
import com.sbnz.physio.facts.Patient;
import com.sbnz.physio.facts.Therapy;
import com.sbnz.physio.facts.Therapy.TherapyType;

import com.sbnz.physio.facts.Treatment;

@Service
public class PainService {
	private final KieContainer kieContainer;
	
	public PainService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public Treatment classifyPain(Pain pain) {
		KieSession kieSession = kieContainer.newKieSession("physio-rules");
		
		
		Diagnosis diagnosis = new Diagnosis();
		
		Patient patient = new Patient();
		
		Pain nekiBol = new Pain();
		nekiBol.setPainType(PainType.MECHANICAL);
		
		Diagnosis prevDiagnosis1 = new Diagnosis();
		prevDiagnosis1.setDiagnosisDate(LocalDate.now().minusMonths(1));
		prevDiagnosis1.setIllness(Diagnosis.Illness.PRIMARY_CERVICAL_SYNDROME);
		prevDiagnosis1.setPainIntensity(PainIntensity.WORSENING);
		prevDiagnosis1.setPain(nekiBol);
		
		
		Diagnosis prevDiagnosis2 = new Diagnosis();
		prevDiagnosis2.setDiagnosisDate(LocalDate.now());
		prevDiagnosis2.setIllness(Diagnosis.Illness.LUMBAGO);
		prevDiagnosis2.setPain(nekiBol);
		
		patient.addDiagnosis(prevDiagnosis1);
		//patient.addDiagnosis(prevDiagnosis1);
		//patient.addDiagnosis(prevDiagnosis1); //za test za Cervical spondylosis B ovu liniju zakomentarisati
		//patient.addDiagnosis(prevDiagnosis2); 
		
		Treatment oldTreatment = new Treatment();
		Therapy oldTherapy = new Therapy();
		oldTherapy.setTherapyType(TherapyType.ELECTRO_PHORESYS_NOVOCAINE);
		oldTherapy.setSessions(20);
		oldTreatment.addTherapy(oldTherapy);
		oldTreatment.setPatient(patient);
		oldTreatment.setDiagnosis(prevDiagnosis1);
		
		Treatment treatment = new Treatment();

		kieSession.insert(pain);
		kieSession.insert(diagnosis);
		kieSession.insert(patient);
		kieSession.insert(treatment);
		kieSession.fireAllRules();
		
		//System.out.println("Patient: \n" + patient);
		
		return treatment;
		
	}
	
	
	public ExerciseReplacement classifyExcercise(ExcerciseEvent e) {
		KieSession kieSession = kieContainer.newKieSession("physio-rules");
		kieContainer.getKieSessionConfiguration("physio-rules");
		//ExerciseReplacement er = new ExerciseReplacement();
		kieSession.insert(e);
		///kieSession.insert(er);
		kieSession.fireAllRules();
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));
		if(newEvents.size() > 0)
			return (ExerciseReplacement) newEvents.iterator().next();
		return null;
		
	}
	

}
