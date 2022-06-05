package com.sbnz.template;


import java.util.ArrayList;

import com.sbnz.physio.facts.Diagnosis;
import com.sbnz.physio.facts.Pain;

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
public class TemplateModel {
	private String type;
	private String localization;
	private ArrayList<String> sympthoms;
	private ArrayList<String> precursors;
	private String illness;
	@Override
	public String toString() {
		String sm = "";
		String pr = "";
		for (String s1 : sympthoms) {
			sm += s1;
		}
		for (String p1 : precursors) {
			pr += p1;
		}
		return "TemplateModel [type=" + type + ", localization=" + localization + ", sympthoms=" + sm
				+ ", precursors=" + pr + "]";
	}
	

}
