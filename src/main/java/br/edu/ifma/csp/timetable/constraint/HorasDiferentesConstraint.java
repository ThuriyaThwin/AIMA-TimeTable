package br.edu.ifma.csp.timetable.constraint;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifma.csp.timetable.model.Horario;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class HorasDiferentesConstraint implements Constraint {
	
	private List<Variable> dias;
	private List<Variable> horas;
	private List<Variable> scope;
	
	public HorasDiferentesConstraint(Variable professor, List<Variable> dias, List<Variable> horas) {
		
		this.dias = dias;
		this.horas = horas;
		
		this.scope = new ArrayList<Variable>();
		this.scope.add(professor);
		this.scope.addAll(dias);
		this.scope.addAll(horas);
		
		new ArrayList<Horario>();
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public int contarOcorrenciaMesmoHorario(String valueDia, Integer valueHora, Assignment assignment) {
		
		int cont = 0;
		
		List<Horario> horarios = new ArrayList<Horario>();
		
		for (Variable dia : dias) {
			
			for (Variable hora : horas) {
				
				String valueX = (String) assignment.getAssignment(dia);
				Integer valueY = (Integer) assignment.getAssignment(hora);
				
				if (valueX != null && valueY != null) {
					
					Horario horario = new Horario(valueX, valueY);
					horarios.add(horario);
				}
			}
		}
		
		for (Horario horario : horarios) {
			
			if (horario.getDia().equals(valueDia) && horario.getHora().equals(valueHora))
				cont += 1;
		}
		
		return cont;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		for (int i = 0; i < dias.size(); i++) {
			
			String dia1 = (String) assignment.getAssignment(dias.get(i));
			Integer hora1 = (Integer) assignment.getAssignment(horas.get(i));
			String dia2 = null;
			Integer hora2 = null;
			
			if (contarOcorrenciaMesmoHorario(dia1, hora1, assignment) > 1) {
				if (dias.size() > i + 1) {
					
					int j = ++i;
					
					dia2 = (String) assignment.getAssignment(dias.get(j));
					hora2 = (Integer) assignment.getAssignment(horas.get(j));
					
					if (dia1 != null && dia2 != null && hora1 != null && hora2 != null) {
						
						if (dia1.equals(dia2) && hora1 == hora2)
							return false;
					}
				}
			}
			
			
		}
		
		return true;
	}

}
