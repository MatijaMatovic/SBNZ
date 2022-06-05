package com.sbnz.physio.facts;

import org.kie.api.definition.type.Role;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Role(Role.Type.EVENT)
public class ExerciseReplacement {
	private Excercise.Type type;
	private String name;
}
