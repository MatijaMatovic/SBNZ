package com.sbnz.physio.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.sbnz.physio.facts.Diagnosis;
import com.sbnz.physio.facts.Pain;

@Service
public class PainService {
	private final KieContainer kieContainer;
	
	public PainService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public Diagnosis classifyPain(Pain pain) {
		KieSession kieSession = kieContainer.newKieSession("physio-rules");
		
		Diagnosis diagnosis = new Diagnosis();

		kieSession.insert(pain);
		kieSession.insert(diagnosis);
		kieSession.fireAllRules();
		
		return diagnosis;
		
	}

}
