package br.edu.ifma.csp.timetable.constraint;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifma.csp.timetable.model.TimeslotProfessor;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;
import aima.core.util.datastructure.Pair;

public class HorariosProfessorConstraint implements Constraint {
	
	private List<Variable> scope;
	private List<TimeslotProfessor> timeslots;
	
	public HorariosProfessorConstraint(List<TimeslotProfessor> timeslots) {
		
		this.scope = new ArrayList<Variable>();
		this.timeslots = timeslots;
		
		for (TimeslotProfessor timeslot : timeslots) {
			
			this.scope.add(timeslot.getProfessor());
			
			for (Pair<Variable, Variable> pair : timeslot.getTimeslots()) {
				
				this.scope.add(pair.getFirst());
				this.scope.add(pair.getSecond());
			}
		}
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public List<TimeslotProfessor> getTimeslots() {
		return timeslots;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		for (TimeslotProfessor slotA : timeslots) {
			
			for (TimeslotProfessor slotB : timeslots) {
				
				Variable profA = slotA.getProfessor();
				Variable profB = slotB.getProfessor();
				
				if (profA.getName() != profB.getName()) {
				
					String valueA = (String) assignment.getAssignment(profA);
					String valueB = (String) assignment.getAssignment(profB);
					
					if (valueA != null && valueB != null) {
						
						if (valueA.equals(valueB)) {
							
							for (Pair<Variable, Variable> varA : slotA.getTimeslots()) {
								
								for (Pair<Variable, Variable> varB : slotB.getTimeslots()) {
									
									Variable diaA = varA.getFirst();
									Variable horaA = varA.getSecond();
									
									Variable diaB = varB.getFirst();
									Variable horaB = varB.getSecond();
									
									String valDiaA = (String) assignment.getAssignment(diaA);
									Integer valHoraA = (Integer) assignment.getAssignment(horaA);
									
									String valDiaB = (String) assignment.getAssignment(diaB);
									Integer valHoraB = (Integer) assignment.getAssignment(horaB);
									
									if (valDiaA != null && valHoraA != null && valDiaB != null && valHoraB != null) {
										
										if (valDiaA.equals(valDiaB) && valHoraA.equals(valHoraB))
											return false;
									}
									
								}
							}
						}
					}
				}
			}
		}
		
		return true;
	}

}
