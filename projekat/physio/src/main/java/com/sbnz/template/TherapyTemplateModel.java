package com.sbnz.template;

import java.util.ArrayList;

import com.sbnz.physio.facts.Diagnosis;
import com.sbnz.physio.facts.Pain;
import com.sbnz.physio.facts.Therapy;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TherapyTemplateModel {
	private String therapy;
	private ArrayList<String> painIntensities;
	private String illness;
	@Override
	public String toString() {
		String sm = "";
		String pr = "";
		return "TemplateModel [illness=" + illness + ", painIntensities=" + sm
				+ ", therapy=" + therapy + "]";
	}
	
}
