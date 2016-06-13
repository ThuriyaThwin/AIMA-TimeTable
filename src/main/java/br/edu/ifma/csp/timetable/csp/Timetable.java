package br.edu.ifma.csp.timetable.csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ifma.csp.timetable.constraint.AllDifferentConstraint;
import br.edu.ifma.csp.timetable.constraint.ProfessorDisciplinaConstraint;
import br.edu.ifma.csp.timetable.constraint.TimeslotConstraint;
import br.edu.ifma.csp.timetable.constraint.TimeslotDisciplinaConstraint;
import br.edu.ifma.csp.timetable.model.Timeslot;
import br.edu.ifma.csp.timetable.model.TimeslotProfessor;
import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;


public class Timetable extends CSP {
	
	String  [] professores = {"Josenildo", "Carla", "Omar", "Karla", "Eva"};
	String  [] disciplinas = {"Inteligência Artificial", "BD", "IPO", "LPI", "LPII", "ICC", "ES", "AEDI", "AEDII", "ANPL"};
	String  [] dias = {"SEG", "TER", "QUA", "QUI", "SEX"};
	String  [] locais = {"Lab. 24", "Lab. 25", "Lab. 26", "Lab. 27"}; 
	
	Integer [] horas = {1650, 1740, 1830, 1920, 2010, 2100, 2150};
	Integer [] aulas = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
	
	String [] horarios = {"SEG_1650", "SEG_1740", "SEG_1830", "SEG_1920", "SEG_2010", "SEG_2100", "SEG_2150",
			  			  "TER_1650", "TER_1740", "TER_1830", "TER_1920", "TER_2010", "TER_2100", "TER_2150",
			  			  "QUA_1650", "QUA_1740", "QUA_1830", "QUA_1920", "QUA_2010", "QUA_2100", "QUA_2150",
			  			  "QUI_1650", "QUI_1740", "QUI_1830", "QUI_1920", "QUI_2010", "QUI_2100", "QUI_2150",
			  			  "SEX_1650", "SEX_1740", "SEX_1830", "SEX_1920", "SEX_2010", "SEX_2100", "SEX_2150"};
	
	HashMap<String, Integer[]> preferencias = new HashMap<String, Integer[]>();
	
	public Timetable() {
		
		/**
		 * Preferências de cada professor para lecionar determinadas disciplinas.
		 */
		
		preferencias.put("Josenildo", new Integer[]{0, 2, 7});
		preferencias.put("Carla", new Integer[]{1, 9});
		preferencias.put("Omar", new Integer[]{0, 2, 4, 8});
		preferencias.put("Karla", new Integer[]{3, 6, 9});
		preferencias.put("Eva", new Integer[]{2, 3, 5, 7});
		
		List<TimeslotProfessor> timeslots = new ArrayList<TimeslotProfessor>();
		List<Timeslot> slots = new ArrayList<Timeslot>();
		
		for (int i = 0; i < disciplinas.length; i++) {
			
			/**
			 * Para cada Disciplina, adicionar nova variável, a qual tem por domínio todas as 
			 * disciplinas a serem ofertadas.
			 */
			
			Variable disciplina = new Variable("D_" + (i+1));
			addVariable(disciplina);
			setDomain(disciplina, new Domain(disciplinas));
			
			/**
			 * Cada Professor, representado como uma variável, a qual tem por domínio todos os 
			 * professores que ministram ao menos 1 disciplina compreendida na matriz curricular.
			 */
			
			Variable professor = new Variable("PF_" + disciplina.getName());
			addVariable(professor);
			setDomain(professor, new Domain(professores));
			
			addConstraint(new ProfessorDisciplinaConstraint(professor, disciplina, preferencias, disciplinas));
			
			/**
			 * Relação entre os N dias semanais necessários para distribuir cada disciplina.
			 */
		
			List<Variable> diasPorDisciplina = new ArrayList<Variable>();
			
			/**
			 * Relação entre as N horas semanais necessárias para distribuir os horários de cada disciplina.
			 */
			
			List<Variable> horasPorDisciplina = new ArrayList<Variable>();
			
			TimeslotProfessor timeslot = new TimeslotProfessor(professor);
			Timeslot slot = new Timeslot(professor, disciplina);
			
			for (int j = 0; j < aulas[i]; j++) {
				
				/**
				 * Dias da semana
				 */
				
				Variable horario = new Variable("H" + (j+1) + "_" + disciplina.getName());
				addVariable(horario);
				setDomain(horario, new Domain(horarios));
				
			/*	Variable dia = new Variable("DIA" + (j+1) + "_" + disciplina.getName());
				addVariable(dia);
				setDomain(dia, new Domain(dias));
				
				diasPorDisciplina.add(dia);*/
				
				/**
				 * Horários de aula da instituição: {16h50, 17h40, 18h30, 19h20, 20h10, 21h00, 21h50}
				 */
				
				/*Variable hora = new Variable("HORA" + (j+1) + "_" + disciplina.getName());
				addVariable(hora);
				setDomain(hora, new Domain(horas));
				
				horasPorDisciplina.add(hora);*/
				
				Variable local = new Variable("L_" + (j+1) + "_" + disciplina.getName());
				addVariable(local);
				setDomain(local, new Domain(locais));
				
				// timeslot.addTimeslot(dia, hora);
				
				slot.addTimeslot(horario);
			}
			
			// timeslots.add(timeslot);
			slots.add(slot);
			
			 addConstraint(new TimeslotDisciplinaConstraint(slot, disciplinas, dias));
			
			//Dias de cada aula devem ser diferentes
			
			//addConstraint(new DiasDiferentesConstraint(professor, disciplina, diasPorDisciplina, dias));
			//addConstraint(new HorasDiferentesConstraint(professor, diasPorDisciplina, horasPorDisciplina));
		}
		
		//addConstraint(new HorariosProfessorConstraint(timeslots));
		 addConstraint(new TimeslotConstraint(slots, professores));
		 
		
		///Disciplinas devem ser diferentes
		
		addConstraint(new AllDifferentConstraint(getVariables(), "D_"));
		
		/*for (Variable disciplina : getVariables()) {
			if (disciplina.getName().startsWith("D_")) {
			
				for (Variable professor : getVariables()) {
					if (professor.getName().startsWith("PF_")) {
						
						///Só leciona uma disciplina o professor que tem preferência pela mesma
						
						
					}
				}
			}
		}*/
	}
}
