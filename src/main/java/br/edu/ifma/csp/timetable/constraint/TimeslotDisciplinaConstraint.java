package br.edu.ifma.csp.timetable.constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ifma.csp.timetable.model.Timeslot;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class TimeslotDisciplinaConstraint implements Constraint {
	
	private List<Variable> scope;
	private Timeslot timeslot;
	String[] disciplinas;
	String[] dias;
	
	public TimeslotDisciplinaConstraint(Timeslot timeslot, String [] disciplinas, String [] dias) {
		
		this.scope = new ArrayList<Variable>();
		this.timeslot = timeslot;
		this.disciplinas = disciplinas;
		this.dias = dias;
		
			
		this.scope.add(timeslot.getDisciplina());
			
		for (Variable horario : timeslot.getHorarios()) {
			this.scope.add(horario);
		}
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public Timeslot getTimeslot() {
		return timeslot;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		// for (Timeslot slot : timeslots) {
			
			switch (timeslot.getHorarios().size()) {
			
				case 2: {
					
				} break;
				
				case 4: {
					
					String val1 = (String) assignment.getAssignment(timeslot.getHorarios().get(0));
					String val2 = (String) assignment.getAssignment(timeslot.getHorarios().get(1));
					String val3 = (String) assignment.getAssignment(timeslot.getHorarios().get(2));
					String val4 = (String) assignment.getAssignment(timeslot.getHorarios().get(3));
					
					if (val1 != null && val2 != null && val3 != null && val4 != null) {
						
						String dia1 = val1.split("_")[0];
						String dia2 = val2.split("_")[0];
						String dia3 = val3.split("_")[0];
						String dia4 = val4.split("_")[0];
						  
						  if (dia1.equals(dias[0]))
							  if (dia3.equals(dias[1]))
								  return false;
						  
						  if (dia1.equals(dias[1]))
							  if (dia3.equals(dias[2]))
								  return false;
						  
						  if (dia1.equals(dias[3]))
							  if (dia3.equals(dias[4]))
								  return false;
						  
						 /* if (!val1.equals(val2))
							  return false;
						  
						  if (!val3.equals(val4))
							  return false;
						  
						  if (val1.equals(val3))
							  return false;
						  
						  if (val2.equals(val4))
							  return false;*/
						  
						  if (!dia1.equals(dia2))
							  return false;
						  
						  if (!dia3.equals(dia4))
							  return false;
						  
						  if (dia1.equals(dia3))
							  return false;
						  
						  if (dia2.equals(dia4))
							  return false;
					  }
					
				} break;
				
				case 6: {
					
				  String val1 = (String) assignment.getAssignment(timeslot.getHorarios().get(0));
				  String val2 = (String) assignment.getAssignment(timeslot.getHorarios().get(1));
				  String val3 = (String) assignment.getAssignment(timeslot.getHorarios().get(2));
				  String val4 = (String) assignment.getAssignment(timeslot.getHorarios().get(3));
				  String val5 = (String) assignment.getAssignment(timeslot.getHorarios().get(4));
				  String val6 = (String) assignment.getAssignment(timeslot.getHorarios().get(5));
				  
				  int r = new Random().nextInt(2 - 1 + 1) + 1;
				  
				  if (val1 != null && val2 != null && val3 != null && val4 != null && val5 != null && val6 != null) {
					  
					  String dia1 = val1.split("_")[0];
					  String dia2 = val2.split("_")[0];
					  String dia3 = val3.split("_")[0];
					  String dia4 = val4.split("_")[0];
					  String dia5 = val5.split("_")[0];
					  String dia6 = val6.split("_")[0];
					  
					  if (dia1.equals(dias[0]))
						  if (dia3.equals(dias[1]))
							  return false;
					  
					  if (dia1.equals(dias[1]))
						  if (dia3.equals(dias[2]))
							  return false;
					  
					  if (dia1.equals(dias[3]))
						  if (dia3.equals(dias[4]))
							  return false;					  
					  
					 /* if (r == 1) {
						  
						  if (!dia1.equals(dia2))
							  return false;
						  
						  if (!dia3.equals(dia4))
							  return false;
						  
						  if (!dia5.equals(dia6))
							  return false;
						  
						  if (dia1.equals(dia3))
							  return false;
						  
						  if (dia2.equals(dia4))
							  return false;
						  
					  } else {*/
						  
						  if (!dia1.equals(dia2) && (!dia1.equals(dia3)))
							  return false;
						  
						  if (!dia4.equals(dia5) && (!dia4.equals(dia6)))
							  return false;
						  
						  if (dia1.equals(dia4))
							  return false;
//					  }
					  
					  /*if (!dia1.equals(dia2))
						  return false;
					  
					  if (!dia3.equals(dia4))
						  return false;*/
					  
					 /* if (!dia5.equals(dia6))
						  return false;*/
					  
					 /* if (dia1.equals(dia3))
						  return false;*/
					  
					  /*if (dia1.equals(dia5))
						  return false;*/
					  
					 /* if (dia2.equals(dia4))
						  return false;*/
					  
					 /* if (dia2.equals(dia6))
						  return false;*/
					  
					  /*if (dia3.equals(dia5) || dia3.equals(dia6))
						  return false;*/
				  }
					
				} break;
			}
			
		//}
		
		return true;
	}

}
