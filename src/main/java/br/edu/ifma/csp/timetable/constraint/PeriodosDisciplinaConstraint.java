package br.edu.ifma.csp.timetable.constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class PeriodosDisciplinaConstraint implements Constraint {
	
	Variable disciplina1;
	Variable horario1;
	Variable disciplina2;
	Variable horario2;
	
	private List<Variable> scope;
	
	Map<Integer, String[]> periodos;
	
	public PeriodosDisciplinaConstraint(Variable disciplina1, Variable horario1, Variable disciplina2, Variable horario2, Map<Integer, String[]> periodos) {
		
		this.scope = new ArrayList<Variable>();
		
		this.disciplina1 = disciplina1;
		this.horario1 = horario1;
		this.disciplina2 = disciplina2;
		this.horario2 = horario2;
		
		this.scope.add(disciplina1);
		this.scope.add(horario1);
		this.scope.add(disciplina2);
		this.scope.add(horario2);
		
		this.periodos = periodos;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public String[] getDisciplinasPorPeriodo(Integer periodo) {
		return periodos.get(periodo);
	}
	
	public Integer getPeriodoDisciplina(String disciplina) {
		
		for (Integer key : periodos.keySet()) {
			
			for (String value : periodos.get(key)) {
				
				if (value.equals(disciplina))
					return key;
			}
		}
		
		return 0;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		String valueDisciplina1 = (String) assignment.getAssignment(disciplina1);
		String valueDisciplina2 = (String) assignment.getAssignment(disciplina2);
		
		if (valueDisciplina1 != null && valueDisciplina2 != null) {
			
			Integer periodoDisciplina1 = getPeriodoDisciplina(valueDisciplina1);
			Integer periodoDisciplina2 = getPeriodoDisciplina(valueDisciplina2);
			
			if (periodoDisciplina1 == periodoDisciplina2) {
				
				String valueHorario1 = (String) assignment.getAssignment(horario1);
				String valueHorario2 = (String) assignment.getAssignment(horario2);
				
				if (valueHorario1 != null && valueHorario2 != null) {
					
					if (valueHorario1.equals(valueHorario2))
						return false;
				}
			}
		}
		
		return true;
	}

}
