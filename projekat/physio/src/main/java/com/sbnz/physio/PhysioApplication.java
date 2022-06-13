package com.sbnz.physio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.sbnz.physio.facts.Pain;

@SpringBootApplication
public class PhysioApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(PhysioApplication.class, args);
		
		List<String> symptomsString = Arrays.asList(Pain.Symptoms.class.getEnumConstants())
				.stream().map(Pain.Symptoms::toString).collect(Collectors.toList());
		
		for (String symptom : symptomsString)
			System.out.println(symptom);
		
		System.out.println("\n========================================================\n\n");
	}
	
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "physio-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}
	
	
	@Bean
	@DependsOn({"kieContainer"})
	public KieSession cepSession(@Autowired KieContainer kieContainer) {
		return kieContainer.newKieSession("physio-cep");
	}

}
