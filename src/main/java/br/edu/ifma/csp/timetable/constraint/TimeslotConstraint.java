package br.edu.ifma.csp.timetable.constraint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.edu.ifma.csp.timetable.model.Timeslot;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class TimeslotConstraint implements Constraint {
	
	private List<Variable> scope;
	private List<Timeslot> timeslots;
	String[] professores;
	
	public TimeslotConstraint(List<Timeslot> timeslots, String[] professores) {
		
		this.scope = new ArrayList<Variable>();
		this.timeslots = timeslots;
		this.professores = professores;
		
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

	/*@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		for (Timeslot slotA : timeslots) {
			
			for (Timeslot slotB : timeslots) {
				
				Variable profA = slotA.getProfessor();
				Variable profB = slotB.getProfessor();
				
				if (profA.getName() != profB.getName()) {
				
					String valueA = (String) assignment.getAssignment(profA);
					String valueB = (String) assignment.getAssignment(profB);
					
					if (valueA != null && valueB != null) {
						
						if (valueA.equals(valueB)) {
							
							for (Variable horarioA : slotA.getHorarios()) {
								
								for (Variable horarioB : slotB.getHorarios()) {
									
									String valHoraA = (String) assignment.getAssignment(horarioA);
									String valHoraB = (String) assignment.getAssignment(horarioB);
									
									if (valHoraA != null && valHoraB != null) {
										
										String diaA = valHoraA.split("_")[0];
										String horaA = valHoraA.split("_")[1];
										
										String diaB = valHoraB.split("_")[0];
										String horaB = valHoraA.split("_")[1];
										
										if (diaA.equals(diaB) && horaA.equals(horaB))
											return false;
										
										if (valHoraA.equals(valHoraB))
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
	}*/
	
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
		
		for (int i = 0; i < professores.length; i++) {
			
			List<Variable> horarios = getHorariosByProfessor(professores[i], assignment);
			
			if (horarios.size() > 0) {
				
				List<String> list = new ArrayList<String>();
				
				for (Variable varX : horarios) {
					
					String valueX = (String) assignment.getAssignment(varX);
					
					if (valueX != null) {
						list.add(valueX);
					}
					
					/*for (Variable varY : horarios) {
						
						if (!varX.getName().equals(varY.getName())) {
						
							String valueX = (String) assignment.getAssignment(varX);
							String valueY = (String) assignment.getAssignment(varY);
							
							if (valueX != null && valueY != null) {
								
								if (valueX == valueY)
									return false;
							}
						}
					}*/
				}
				
				Set<String> set = new HashSet<String>(list);
				
				if (set.size() < list.size())
					return false;
			}
		}
			
		return true;
	}

}
