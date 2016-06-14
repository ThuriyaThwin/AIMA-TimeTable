package br.edu.ifma.csp.timetable.run;

import java.util.Iterator;

import br.edu.ifma.csp.timetable.csp.Timetable;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CSPStateListener;
import aima.core.search.csp.Variable;

public class Main {
	
	public static void main(String[] args) {
		
		Timetable timetable = new Timetable();
		
		BacktrackingStrategy bts = new BacktrackingStrategy();
		bts.addCSPStateListener(new CSPStateListener() {
		    @Override
		    public void stateChanged(Assignment assignment, CSP csp) {
		        System.out.println("Assignment evolved : " + assignment);
		    }

		    @Override
		    public void stateChanged(CSP csp) {
		        System.out.println("CSP evolved : " + csp);
		    }
		});
		
		double start = System.currentTimeMillis();
		
		Assignment sol = null;

		sol = bts.solve(timetable);
		
		double end = System.currentTimeMillis();
		
		System.out.println();
		
		for (int i = 0; i < sol.getVariables().size(); i++) {
			
			Variable disciplina = new Variable("D" + (i+1));
			
			if (sol.getVariables().contains(disciplina)) {
				
				Iterator<Variable> it = sol.getVariables().iterator();
				
				while (it.hasNext()) {
				
					Variable temp = it.next();
					
					if (temp.getName().endsWith(disciplina.getName())) {
						
						System.out.print(temp + " = " + sol.getAssignment(temp));
						
						if (it.hasNext()) {
							System.out.print(", ");
						}
					}
				}
								
			} else {
				
				break;
			}
			
			System.out.println("\n");
		}
		
		System.out.println("\nTime to solve = " + (end - start));
	}
}
