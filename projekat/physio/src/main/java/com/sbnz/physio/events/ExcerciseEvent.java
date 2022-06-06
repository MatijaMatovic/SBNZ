package com.sbnz.physio.events;


import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import com.sbnz.physio.facts.Excercise;
import com.sbnz.physio.facts.Excercise.Type;

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
@Expires("30m")
public class ExcerciseEvent {
	public enum PainType { STIFFNESS, PAIN }
	private PainType painType;
	private Excercise.Type type;
	private String name;
}
