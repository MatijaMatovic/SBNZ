package com.sbnz.physio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sbnz.physio.facts.Pain;

@SpringBootApplication
public class PhysioApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(PhysioApplication.class, args);
		
		List<String> symptomsString = Arrays.asList(Pain.Symptom.class.getEnumConstants())
				.stream().map(Pain.Symptom::toString).collect(Collectors.toList());
		
		for (String symptom : symptomsString)
			System.out.println(symptom);
	}

}
