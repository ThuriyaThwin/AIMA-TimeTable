package br.edu.ifma.csp.timetable.constraint;

import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class AllDifferentConstraint implements Constraint {
	
	private List<Variable> scope;
	private String prefix;
	
	public AllDifferentConstraint(List<Variable> vars) {
		this.scope = vars;
	}
	
	public AllDifferentConstraint(List<Variable> vars, String prefix) {
		this.scope = vars;
		this.prefix = prefix;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
			
		boolean valido = true;
		
		for (Variable v : scope) {
			
			if (v.getName().startsWith(prefix)) {
			
				Object value = assignment.getAssignment(v);
				
				if (value != null) {
					
					for (Variable x : scope) {
						
						Object valueX = assignment.getAssignment(x);
						
						if (valueX != null) {
							
							if (v.getName() != x.getName() && value.equals(valueX)) {
								
								valido = false;
							}
						}
					}
				}
			}
		}
		
		return valido;
	}
}