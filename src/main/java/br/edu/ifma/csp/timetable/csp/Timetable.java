package br.edu.ifma.csp.timetable.csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import br.edu.ifma.csp.timetable.constraint.AllDifferentConstraint;
import br.edu.ifma.csp.timetable.constraint.PreferenciaDisciplinaProfessorConstraint;
import br.edu.ifma.csp.timetable.constraint.TimeslotConstraint;
import br.edu.ifma.csp.timetable.constraint.TimeslotDisciplinaConstraint;
import br.edu.ifma.csp.timetable.model.Timeslot;

public class Timetable extends CSP {
	
	/** Valores para o domínio: Professor **/
	
	String  [] valuesProfessor = {"Josenildo", "Carla", "Omar", "Karla", "Eva"};
	
	/** Valores para o domínio: Disciplina **/
	
	String  [] valuesDisciplina = {"IA", "BD", "IPO", "LPI", "LPII", "ICC", "ES", "AEDI", "AEDII", "ANPL"};
	
	/** Valores para o domínio: Local **/
	
	String  [] valuesLocal = {"Lab. 24", "Lab. 25", "Lab. 26", "Lab. 27"}; 
	
	/** Valores para o domínio: Horario **/
	
	String  [] valuesHorario = {"SEG_1650", "SEG_1740", "SEG_1830", "SEG_1920", "SEG_2010", "SEG_2100", "SEG_2150",
			   			  		"TER_1650", "TER_1740", "TER_1830", "TER_1920", "TER_2010", "TER_2100", "TER_2150",
			  			  		"QUA_1650", "QUA_1740", "QUA_1830", "QUA_1920", "QUA_2010", "QUA_2100", "QUA_2150",
			  			  		"QUI_1650", "QUI_1740", "QUI_1830", "QUI_1920", "QUI_2010", "QUI_2100", "QUI_2150",
			  			  		"SEX_1650", "SEX_1740", "SEX_1830", "SEX_1920", "SEX_2010", "SEX_2100", "SEX_2150"};
	
	/** Domínio de dias letivos: utilizada em 'TimeslotDisciplinaConstraint' **/
	
	String  [] valuesDia = {"SEG", "TER", "QUA", "QUI", "SEX"};
	
	/** Domínio de horários de aula: utilizada em '' **/
	
	Integer [] valuesHora = {1650, 1740, 1830, 1920, 2010, 2100, 2150};
	
	/** Valores para criar os horários proporcionais à carga horária de uma disciplina **/
	
	Integer [] valuesAula = {6, 4, 4, 4, 4, 4, 4, 4, 4, 4};
	
	/** Preferências por disciplinas para cada professor: utilizada em 'ProfessorDisciplinaConstraint' **/

	HashMap<String, Integer[]> preferencias = new HashMap<String, Integer[]>();
	
	public Timetable() {
		
		preferencias.put("Josenildo", new Integer[]{0, 2, 7});
		preferencias.put("Carla", new Integer[]{1, 9});
		preferencias.put("Omar", new Integer[]{0, 2, 4, 8});
		preferencias.put("Karla", new Integer[]{3, 6, 9});
		preferencias.put("Eva", new Integer[]{2, 3, 5, 7});
		
		/** Coleção de timeslots (aula) = {1 professor, 1 disciplina, n horarios}: utilizada em 'TimeslotConstraint' **/
		
		List<Timeslot> slots = new ArrayList<Timeslot>();
		
		/** Coleção de disciplinas: utilizada na constraint 'AllDifferent' **/
		
		List<Variable> disciplinas = new ArrayList<Variable>();
		
		/** Coleção de horários: **/
		
		List<Variable> horarios = new ArrayList<Variable>();
		
		for (int i = 0; i < valuesDisciplina.length; i++) {
			
			/**
			 * Para cada Disciplina, adicionar nova variável, a qual tem por domínio todas as 
			 * disciplinas a serem ofertadas.
			 */
			
			Variable disciplina = new Variable("D" + (i+1));
			addVariable(disciplina);
			setDomain(disciplina, new Domain(valuesDisciplina));
			
			disciplinas.add(disciplina);
			
			/**
			 * Cada Professor, representado como uma variável, a qual tem por domínio todos os 
			 * professores que ministram ao menos 1 disciplina compreendida na matriz curricular.
			 */
			
			Variable professor = new Variable("PF_" + disciplina.getName());
			addVariable(professor);
			setDomain(professor, new Domain(valuesProfessor));
			
			/** Um professor só poderá ser associado a uma disciplina a qual tem preferência **/
			
			addConstraint(new PreferenciaDisciplinaProfessorConstraint(professor, disciplina, preferencias, valuesDisciplina));
			
			/** Slot de tempo (aula): {1 professor, 1 disciplina, n horarios} **/
			
			Timeslot slot = new Timeslot(professor, disciplina);
			
			for (int j = 0; j < valuesAula[i]; j++) {
				
				/** Horário para cada aula **/
				
				Variable horario = new Variable("H" + (j+1) + "_" + disciplina.getName());
				addVariable(horario);
				setDomain(horario, new Domain(valuesHorario));
				
				horarios.add(horario);
				
				/** Local para cada aula **/
				
				Variable local = new Variable("L" + (j+1) + "_" + disciplina.getName());
				addVariable(local);
				setDomain(local, new Domain(valuesLocal));
				
				slot.addTimeslot(horario);
			}
			
			slots.add(slot);
			
			System.out.println(slot.getDisciplina());
			
			for (Variable horario : slot.getHorarios()) {
				System.out.println(horario + ", ");
			}
			
			/** Uma disciplina deve ter um intervalo mínimo de aulas consecutivas dada a carga horária (30, 60, 75, 90) **/
			
			addConstraint(new TimeslotDisciplinaConstraint(slot, valuesDisciplina, valuesDia, valuesHorario));
			
			break;
		}
		
		/** Cada disciplina só poderá ter uma única oferta por semestre na grade curricular **/
		
		addConstraint(new AllDifferentConstraint(disciplinas));
		
		/** Os horários de um professor não podem ser repetidos **/
		
		for (int i = 0; i < valuesProfessor.length; i++) {
			addConstraint(new TimeslotConstraint(slots, valuesProfessor[i]));
		}
		
	}
}
