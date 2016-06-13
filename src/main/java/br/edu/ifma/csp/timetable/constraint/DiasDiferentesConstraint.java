package br.edu.ifma.csp.timetable.constraint;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class DiasDiferentesConstraint implements Constraint {
	
	private List<Variable> scope;
	private List<Variable> dias;
	private String[] diasStr;
	
	public DiasDiferentesConstraint(List<Variable> vars) {
		this.scope = vars;
	}
	
	public DiasDiferentesConstraint(Variable professor, Variable disciplina, List<Variable> dias, String [] diasStr) {
		this.scope = new ArrayList<Variable>();
		
		this.scope.add(professor);
		this.scope.add(disciplina);
		this.scope.addAll(dias);
		
		this.dias = dias;
		this.diasStr = diasStr;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public int getAulasPorDia(String dia, List<Variable> dias, Assignment assignment) {
		
		int cont = 0;
		
		for (Variable var : dias) {
			
			String value = (String) assignment.getAssignment(var);
			
			if (value != null) {
				
				if (value.equals(dia))
					cont += 1;
			}
		}
		
		return cont;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
			
		switch (dias.size()) {
		
		  case 2: {
			  
			  String val1 = (String) assignment.getAssignment(dias.get(0));
			  String val2 = (String) assignment.getAssignment(dias.get(1));
			  
			  if (val1 != null && val2 != null) {
					
				  //if (!val1.equals(val2))
					//return false;
			  }
		  }
		  
		  break;
		  
		  case 4: {
			  
			  String val1 = (String) assignment.getAssignment(dias.get(0));
			  String val2 = (String) assignment.getAssignment(dias.get(1));
			  String val3 = (String) assignment.getAssignment(dias.get(2));
			  String val4 = (String) assignment.getAssignment(dias.get(3));
			  
			  if (val1 != null && val2 != null && val3 != null && val4 != null) {
				  
				  if (val1.equals(diasStr[0]))
					  if (val3.equals(diasStr[1]))
						  return false;
				  
				  if (val1.equals(diasStr[1]))
					  if (val3.equals(diasStr[2]))
						  return false;
				  
				  if (val1.equals(diasStr[3]))
					  if (val3.equals(diasStr[4]))
						  return false;
				  
				  if (!val1.equals(val2))
					  return false;
				  
				  if (!val3.equals(val4))
					  return false;
				  
				  if (val1.equals(val3))
					  return false;
				  
				  if (val2.equals(val4))
					  return false;
			  }
		  }
		  
		  break;
		  
		
		
		  case 6: {
				
			  String val1 = (String) assignment.getAssignment(dias.get(0));
			  String val2 = (String) assignment.getAssignment(dias.get(1));
			  String val3 = (String) assignment.getAssignment(dias.get(2));
			  String val4 = (String) assignment.getAssignment(dias.get(3));
			  String val5 = (String) assignment.getAssignment(dias.get(4));
			  String val6 = (String) assignment.getAssignment(dias.get(5));
			  
			  if (val1 != null && val2 != null && val3 != null && val4 != null && val5 != null && val6 != null) {
				  
				  if (val1.equals(diasStr[0]))
					  if (val3.equals(diasStr[1]))
						  return false;
				  
				  if (val1.equals(diasStr[1]))
					  if (val3.equals(diasStr[2]))
						  return false;
				  
				  if (val1.equals(diasStr[3]))
					  if (val3.equals(diasStr[4]))
						  return false;
				  
				  if (!val1.equals(val2))
					  return false;
				  
				  if (!val3.equals(val4))
					  return false;
				  
				  if (!val5.equals(val6))
					  return false;
				  
				  if (val1.equals(val3))
					  return false;
				  
				  if (val2.equals(val4))
					  return false;
				  
				  if (val1.equals(val5))
					  return false;
				  
				  if (val3.equals(val5))
					  return false;
			  }
		  }
			
		  break;
		
		}
		
		return true;
	}
}