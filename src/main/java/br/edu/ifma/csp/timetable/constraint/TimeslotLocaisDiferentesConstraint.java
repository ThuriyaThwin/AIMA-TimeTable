package br.edu.ifma.csp.timetable.constraint;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class TimeslotLocaisDiferentesConstraint implements Constraint {
	
	private List<Variable> scope;
	
	private Variable local1;
	private Variable horario1;
	private Variable local2;
	private Variable horario2;
	
	public TimeslotLocaisDiferentesConstraint(Variable local1, Variable horario1, Variable local2, Variable horario2) {
		
		this.scope = new ArrayList<Variable>();
		
		this.local1 = local1;
		this.horario1 = horario1;
		this.local2 = local2;
		this.horario2 = horario2;
		
		this.scope.add(local1);
		this.scope.add(horario1);
		this.scope.add(local2);
		this.scope.add(horario2);
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		String valueLocal1 = (String) assignment.getAssignment(local1);
		String valueHorario1 = (String) assignment.getAssignment(horario1);
		
		String valueLocal2 = (String) assignment.getAssignment(local2);
		String valueHorario2 = (String) assignment.getAssignment(horario2);
		
		if (valueLocal1 != null && valueLocal2 != null) {
			
			if (valueHorario1 != null && valueHorario2 != null) {
			
				if (valueHorario1.equals(valueHorario2)) {
					
					if (valueLocal1.equals(valueLocal2))
						return false;
				}
			}
		}
		
		return true;
	}
}
