package com.sbnz.physio.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import com.sbnz.physio.events.ExcerciseEvent.PainType;
import com.sbnz.physio.facts.Excercise;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Role(Role.Type.EVENT)
@Expires("30m")
public class InjuryEvent {
	public enum InjurySeverity { MILD, SEVERE }
	private InjurySeverity severity;
	private Excercise.Type type;
	private String name;
	private boolean isProcessed;
	
	public InjuryEvent() {
		this.isProcessed = false;
	}
	
}
