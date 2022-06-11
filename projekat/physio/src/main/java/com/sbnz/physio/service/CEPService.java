package com.sbnz.physio.service;

import java.util.Collection;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
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
	KieContainer kieContainer;
	
	public ExerciseReplacement registerInjury(InjuryEvent injuryEvent) {
		KieSession kieSession = kieContainer.newKieSession("physio-cep");
		kieContainer.getKieSessionConfiguration("physio-cep");
		
		kieSession.insert(injuryEvent);
		kieSession.fireAllRules();
		
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));
		if(newEvents.size() > 0)
			return (ExerciseReplacement) newEvents.iterator().next();
		return null;
	}
	
	public ExerciseReplacement registerExcerciseEvent(ExcerciseEvent excerciseEvent) {
		KieSession kieSession = kieContainer.newKieSession("physio-cep");
		kieContainer.getKieSessionConfiguration("physio-cep");
		
		kieSession.insert(excerciseEvent);
		kieSession.fireAllRules();
		
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));
		if(newEvents.size() > 0)
			return (ExerciseReplacement) newEvents.iterator().next();
		return null;
	}
	
	public void registerExhaustion() {
		KieSession kieSession = kieContainer.newKieSession("physio-cep");
		kieContainer.getKieSessionConfiguration("physio-cep");
		
		kieSession.insert(new ExhaustionEvent());
	}
}
