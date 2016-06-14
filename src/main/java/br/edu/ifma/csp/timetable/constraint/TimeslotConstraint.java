package br.edu.ifma.csp.timetable.constraint;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;
import br.edu.ifma.csp.timetable.model.Timeslot;

public class TimeslotConstraint implements Constraint {
	
	private List<Variable> scope;
	private List<Timeslot> timeslots;
	String professor;
	
	public TimeslotConstraint(List<Timeslot> timeslots, String professor) {
		
		this.scope = new ArrayList<Variable>();
		this.timeslots = timeslots;
		this.professor = professor;
		
		for (Timeslot slot : timeslots) {
			
			this.scope.add(slot.getProfessor());
			
			for (Variable horario : slot.getHorarios()) {
				this.scope.add(horario);
			}
		}
	}
	
	public List<Timeslot> getTimeslots() {
		return timeslots;
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public List<Variable> getHorariosByProfessor(String professor, Assignment assignment) {
		
		List<Variable> list = new ArrayList<Variable>();
		
		for (Timeslot timeslot : timeslots) {
			
			String prof = (String) assignment.getAssignment(timeslot.getProfessor());
			
			if (prof != null && prof.equals(professor)) {
				list.addAll(timeslot.getHorarios());
			}
		}
		
		return list;
	}
	
	public int getQuantidadeHorariosByDia(List<Variable> horarios, String dia, Assignment assignment) {
		
		int cont = 0;
		
		for (Variable horario : horarios) {
			
			String value = (String) assignment.getAssignment(horario);
			
			if (value != null) {
				
				String valueDia = value.split("_")[0];
				
				if (valueDia.equals(dia))
					cont += 1;
			}
		}
		
		return cont;
	}
	
	public int getQuantidadeHorariosByDisciplina() {
		return 0;
	}
	
	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
			
		List<Variable> horarios = getHorariosByProfessor(professor, assignment);
		
		return new AllDifferentConstraint(horarios).isSatisfiedWith(assignment);
	}
}
