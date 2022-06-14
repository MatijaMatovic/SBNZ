package com.sbnz.physio.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.physio.events.ExcerciseEvent;
import com.sbnz.physio.events.ExhaustionEvent;
import com.sbnz.physio.events.InjuryEvent;
import com.sbnz.physio.facts.ExerciseReplacement;

@Service
public class CEPService {
	@Autowired
	KieSession kieSession;
	
	public ExerciseReplacement registerInjury(InjuryEvent injuryEvent) {
		ExerciseReplacement replacement = new ExerciseReplacement();
		
		kieSession.insert(replacement);
		kieSession.insert(injuryEvent);
		kieSession.fireAllRules();
		
		return replacement;
	}
	
	public ExerciseReplacement registerExcerciseEvent(ExcerciseEvent excerciseEvent) {
		ExerciseReplacement replacement = new ExerciseReplacement();
		
		kieSession.insert(replacement);
		kieSession.insert(excerciseEvent);
		kieSession.fireAllRules();
		
		return replacement;
	}
	
	public void registerExhaustion() {
		kieSession.insert(new ExhaustionEvent());
		kieSession.fireAllRules();
	}
}
