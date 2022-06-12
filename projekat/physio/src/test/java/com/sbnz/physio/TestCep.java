package com.sbnz.physio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbnz.physio.events.ExcerciseEvent;
import com.sbnz.physio.events.ExhaustionEvent;
import com.sbnz.physio.events.ExcerciseEvent.PainType;
import com.sbnz.physio.events.InjuryEvent;
import com.sbnz.physio.events.InjuryEvent.InjurySeverity;
import com.sbnz.physio.facts.Excercise.Type;
import com.sbnz.physio.facts.ExerciseReplacement;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCep {
	
	private KieContainer kieContainer;
	
	@PostConstruct
	public void setup() {
		KieServices ks = KieServices.Factory.get();
		kieContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "physio-kjar", "0.0.1-SNAPSHOT"));
	}
	
	@Test
	public void testDynamicToStatic() {
		System.out.println("\nTEST: DYNAMIC TO STATIC");
		KieSession kieSession = kieContainer.newKieSession("physio-cep");
		
		ExcerciseEvent event = new ExcerciseEvent();
		event.setName("Trbusnjaci");
		event.setPainType(PainType.STIFFNESS);
		event.setType(Type.DYNAMIC);
		
		kieSession.insert(event);
		
		int ruleCount = kieSession.fireAllRules();
		
		assertEquals(1, ruleCount);
		
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));

		assertEquals(1, newEvents.size());
		
		ExerciseReplacement replacement = (ExerciseReplacement) newEvents.iterator().next();
		
		assertEquals(event.getName(), replacement.getName());
		assertEquals(Type.STATIC, replacement.getType());
		
		kieSession.dispose();
	}
	
	
	@Test
	public void testStaticToRest() {
		System.out.println("\nTEST: STATIC TO REST");
		KieSession kieSession = kieContainer.newKieSession("physio-cep");
		
		ExcerciseEvent event = new ExcerciseEvent();
		event.setName("Trbusnjaci");
		event.setPainType(PainType.PAIN);
		event.setType(Type.STATIC);
		
		kieSession.insert(event);
		
		int ruleCount = kieSession.fireAllRules();
		
		assertEquals(1, ruleCount);
		
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));

		assertEquals(1, newEvents.size());
		
		ExerciseReplacement replacement = (ExerciseReplacement) newEvents.iterator().next();
		
		assertEquals("Pacijent neka odmori malo", replacement.getName());
		assertEquals(Type.REST, replacement.getType());
		assertEquals(false, replacement.isStopWorkout());
		
		kieSession.dispose();
	
	}
	
	@Test
	public void testDynamicToRest() {
		System.out.println("\nTEST: DYNAMIC TO REST");

		KieSession kieSession = kieContainer.newKieSession("physio-cep-pseudoClock");
		SessionPseudoClock clock = kieSession.getSessionClock();
		
		
		ExcerciseEvent event1 = new ExcerciseEvent();
		event1.setName("Trbusnjaci1");
		event1.setPainType(PainType.PAIN);
		event1.setType(Type.DYNAMIC);
		
		kieSession.insert(event1);
		
		clock.advanceTime(5, TimeUnit.MINUTES);
		
		ExcerciseEvent event2 = new ExcerciseEvent();
		event2.setName("Trbusnjaci2");
		event2.setPainType(PainType.PAIN);
		event2.setType(Type.DYNAMIC);
		
		kieSession.insert(event2);
		
		
		int ruleCount = kieSession.fireAllRules();
		
		assertEquals(1, ruleCount);
		
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));
		
		assertEquals(1, newEvents.size());
		
		ExerciseReplacement replacement = (ExerciseReplacement) newEvents.iterator().next();
		
		assertEquals("Pacijent neka prekine vezbe", replacement.getName());
		assertEquals(Type.REST, replacement.getType());
		assertEquals(true, replacement.isStopWorkout());
		
		kieSession.dispose();

	}
	
	
	@Test
	public void testDynamicToRest_previousEventExpired() {
		System.out.println("\nTEST: DYNAMIC TO REST; PREVIOUS EVENT EXPIRED");

		KieSession kieSession = kieContainer.newKieSession("physio-cep-pseudoClock");
		SessionPseudoClock clock = kieSession.getSessionClock();
		
		
		ExcerciseEvent event1 = new ExcerciseEvent();
		event1.setName("Trbusnjaci1");
		event1.setPainType(PainType.PAIN);
		event1.setType(Type.DYNAMIC);
		
		kieSession.insert(event1);
		
		clock.advanceTime(15, TimeUnit.MINUTES);
		
		ExcerciseEvent event2 = new ExcerciseEvent();
		event2.setName("Trbusnjaci2");
		event2.setPainType(PainType.PAIN);
		event2.setType(Type.DYNAMIC);
		
		kieSession.insert(event2);
		
		
		int ruleCount = kieSession.fireAllRules();
		
		assertEquals(0, ruleCount);
		
		kieSession.dispose();

	}
	
	
	@Test
	public void testManyMildEvents() {
		System.out.println("\nTEST: MANY MILD EVENTS");
		KieSession kieSession = kieContainer.newKieSession("physio-cep");
		
		InjuryEvent event1 = new InjuryEvent();
		event1.setSeverity(InjurySeverity.MILD);
		
		ExhaustionEvent event2 = new ExhaustionEvent();
		
		InjuryEvent event3 = new InjuryEvent();
		event3.setSeverity(InjurySeverity.MILD);
		
		kieSession.insert(event1);
		kieSession.insert(event2);
		kieSession.insert(event3);
		
		int ruleCount = kieSession.fireAllRules();
		
		assertEquals(1, ruleCount);
		
		kieSession.dispose();
	}
	
	
	
	@Test
	public void testDynamicDifficulties() {
		System.out.println("\nTEST: DYNAMIC DIFFICULTIES");
		
		KieSession kieSession = kieContainer.newKieSession("physio-cep-pseudoClock");
		SessionPseudoClock clock = kieSession.getSessionClock();
		
		InjuryEvent event1 = new InjuryEvent();
		event1.setSeverity(InjurySeverity.MILD);
		
		ExhaustionEvent event2 = new ExhaustionEvent();
		
		InjuryEvent event3 = new InjuryEvent();
		event3.setSeverity(InjurySeverity.MILD);
		
		kieSession.insert(event1);
		clock.advanceTime(2, TimeUnit.MINUTES);
		
		kieSession.insert(event2);
		clock.advanceTime(2, TimeUnit.MINUTES);
		
		kieSession.insert(event3);
		clock.advanceTime(2, TimeUnit.MINUTES);
		
		int ruleCount = kieSession.fireAllRules();
		assertEquals(1, ruleCount);
		
		ExcerciseEvent event4 = new ExcerciseEvent();
		event4.setType(Type.DYNAMIC);
		event4.setPainType(PainType.PAIN);
		event4.setName("vezba1");
		
		kieSession.insert(event4);
		ruleCount = kieSession.fireAllRules();
		assertEquals(1, ruleCount);
		
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));
		assertEquals(1, newEvents.size());
		
		ExerciseReplacement replacement = (ExerciseReplacement) newEvents.iterator().next();
		assertEquals(event4.getName(), replacement.getName());
		assertEquals(Type.STATIC, replacement.getType());
		assertEquals(false, replacement.isStopWorkout());
		
		kieSession.dispose();
		
	}
	
	
	
	@Test
	public void testDynamicInjuryNoDifficulties() {
		System.out.println("\nTEST: DYNAMIC INJURY NO DIFFICULTIES");
		KieSession kieSession = kieContainer.newKieSession("physio-cep");
		
		InjuryEvent event = new InjuryEvent();
		event.setType(Type.DYNAMIC);
		event.setSeverity(InjurySeverity.SEVERE);
		event.setName("povreda1");
		
		kieSession.insert(event);
		
		int rulesFired = kieSession.fireAllRules();
		assertEquals(1, rulesFired);
		
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));
		assertEquals(1, newEvents.size());
		
		ExerciseReplacement replacement = (ExerciseReplacement) newEvents.iterator().next();
		assertEquals(event.getName(), replacement.getName());
		assertEquals(Type.STATIC, replacement.getType());
		assertEquals(false, replacement.isStopWorkout());
		
	}

	
	@Test
	public void testDynamicInjuryOtherDifficulties() {
		System.out.println("\nTEST: DYNAMIC INJURY OTHER DIFFICULTIES");
		
		KieSession kieSession = kieContainer.newKieSession("physio-cep-pseudoClock");
		SessionPseudoClock clock = kieSession.getSessionClock();
		
		InjuryEvent event1 = new InjuryEvent();
		event1.setSeverity(InjurySeverity.MILD);
		
		ExcerciseEvent event2 = new ExcerciseEvent();
		event2.setName("dog1");
		event2.setPainType(PainType.PAIN);
		event2.setType(Type.DYNAMIC);
		
		kieSession.insert(event1);
		clock.advanceTime(2, TimeUnit.MINUTES);
		
		kieSession.insert(event2);
		clock.advanceTime(2, TimeUnit.MINUTES);
		
		InjuryEvent injuryEvent = new InjuryEvent();
		injuryEvent.setType(Type.DYNAMIC);
		injuryEvent.setSeverity(InjurySeverity.SEVERE);
		injuryEvent.setName("povreda1");
		
		kieSession.insert(injuryEvent);
		
		int rulesFired = kieSession.fireAllRules();
		assertEquals(1, rulesFired);
		
		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(ExerciseReplacement.class));
		assertEquals(1, newEvents.size());
		
		ExerciseReplacement replacement = (ExerciseReplacement) newEvents.iterator().next();
		assertEquals("Pacijent neka hitno prekine sa vezbanjem", replacement.getName());
		assertEquals(Type.REST, replacement.getType());
		assertEquals(true, replacement.isStopWorkout());
	}
}
