package com.sbnz.physio.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.sbnz.physio.facts.Pain;

@Service
public class PainService {
	private final KieContainer kieContainer;
	
	public PainService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public Pain classifyPain(Pain pain) {
		KieSession kieSession = kieContainer.newKieSession();
		
		kieSession.insert(pain);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return pain;
	}

}
