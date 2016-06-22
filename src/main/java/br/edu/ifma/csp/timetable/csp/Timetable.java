package br.edu.ifma.csp.timetable.csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import br.edu.ifma.csp.timetable.constraint.AllDifferentConstraint;
import br.edu.ifma.csp.timetable.constraint.LocaisIguaisConstraint;
import br.edu.ifma.csp.timetable.constraint.PreferenciaDisciplinaProfessorConstraint;
import br.edu.ifma.csp.timetable.constraint.TimeslotDiasDiferentesConstraint;
import br.edu.ifma.csp.timetable.constraint.TimeslotDiasIguasConstraint;
import br.edu.ifma.csp.timetable.constraint.TimeslotLocaisDiferentesConstraint;
import br.edu.ifma.csp.timetable.constraint.TimeslotProfessorConstraint;

public class Timetable extends CSP {
	
	/** Valores para o domínio: Professor **/
	
	String  [] valuesProfessor = {"Josenildo", "Carla", "Omar", "Karla", "Eva", "Raimundo", "João", "Rayane", "Mauro", 
			                      "Márcio", "Eveline", "Valdir", "Gentil", "Luis Carlos", "Angela"};
	
	/** Valores para o domínio: Disciplina **/
	
	String  [] valuesDisciplina = {"Cálculo I", "Cálculo Vetorial", "Filosofia", "ICC", "Metodologia Científica", // 4
								   "Fundamentos de S.I", "Inglês", "LP I", "Matemática Discreta", "Org. Arq. Computadores", "Prob. Estatística", // 10
								   "Álgebra Linear", "AED I", "LP II", "SO I", "Sociologia", // 15
								   "AED II", "BD I", "ES I", "IHC", "Redes I", // 20
								   "Análise I", "BD II", "Gestão e organização", "Lab. BD", "LP III", "Redes II", // 26
								   "Análise II", "Ger. Projetos", "IA", "LP Web", // 30
								   "Comp. Ética e Sociedade", "IPO", "Monografia I", // 33
								   "Empreendedorismo"}; // 34
	
	/** Valores para o domínio: Local **/
	
	String  [] valuesLocal = {"Lab. 24", "Lab. 25", "Lab. 26", "Lab. 27", "Sala 17", "Sala 20", "Sala 25", "Sala 26", 
			                  "Sala 15", "Sala 16", "Lab. Mult", "Sala 32", "Sala 28", "Sala 27", "Sala 18", "Sala 19", "Sala 21"}; 
	
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
	
	Integer [] valuesAula = {6, 4, 4, 6, 4, 
            4, 4, 4, 4, 4, 4,
            4, 6, 6, 4, 4,
            4, 4, 4, 4, 4,
            4, 4, 4, 4, 4, 4,
            6, 4, 4, 6,
            4, 6, 4,
            4};
	
	Integer [] valuesPeriodo = {6, 4, 7, 2, 3, 1, 4, 3, 4, 5, 2};
	
	/** Preferências por disciplinas para cada professor: utilizada em 'ProfessorDisciplinaConstraint' **/

	HashMap<String, Integer[]> preferencias = new HashMap<String, Integer[]>();
	
	HashMap<Integer, String[]> periodos = new HashMap<Integer, String[]>();
	
	public Timetable() {
		
		preferencias.put("Josenildo", new Integer[]{12, 24, 29, 32});
		preferencias.put("Carla", new Integer[]{17, 21, 27});
		preferencias.put("Omar", new Integer[]{13, 16, 29, 32});
		preferencias.put("Karla", new Integer[]{3, 7, 18, 21, 27});
		preferencias.put("Eva", new Integer[]{3, 5, 7, 12});
		preferencias.put("Raimundo", new Integer[]{3, 7, 8, 13, 32});
		preferencias.put("João", new Integer[]{22, 24, 25, 30});
		preferencias.put("Rayane", new Integer[]{0, 1, 10, 11});
		preferencias.put("Mauro", new Integer[]{13, 25, 28, 30});
		preferencias.put("Márcio", new Integer[]{9, 14, 20, 26});
		preferencias.put("Eveline", new Integer[]{3, 7, 19, 21, 27});
		preferencias.put("Valdir", new Integer[]{2, 4, 15});
		preferencias.put("Gentil", new Integer[]{12, 15, 31});
		preferencias.put("Luis Carlos", new Integer[]{6, 33});
		preferencias.put("Angela", new Integer[]{23, 34});
		
		periodos.put(1, new String[]{"Cálculo I", "Cálculo Vetorial", "Filosofia", "ICC", "Metodologia Científica"});
		periodos.put(2, new String[]{"Fundamentos de S.I", "Inglês", "LP I", "Matemática Discreta", "Org. Arq. Computadores", "Prob. Estatística"});
		periodos.put(3, new String[]{"Álgebra Linear", "AED I", "LP II", "SO I", "Sociologia"});
		periodos.put(4, new String[]{"AED II", "BD I", "ES I", "IHC", "Redes I"});
		periodos.put(5, new String[]{"Análise I", "BD II", "Gestão e organização", "Lab. BD", "LP III", "Redes II"});
		periodos.put(6, new String[]{"Análise II", "Ger. Projetos", "IA", "LP Web"});
		periodos.put(7, new String[]{"Comp. Ética e Sociedade", "IPO", "Monografia I"});
		periodos.put(8, new String[]{"Empreendedorismo", "Monografia II"});
		
		/** Coleção de timeslots (aula) = {1 professor, 1 disciplina, n horarios}: utilizada em 'TimeslotConstraint' **/
		
		List<Timeslot> timeslots = new ArrayList<Timeslot>();
		
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
			
			Variable professor = new Variable("P_" + disciplina.getName());
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
				
				slot.addLocal(local);
				slot.addTimeslot(horario);
			}
			
			timeslots.add(slot);
		}
		
		/** Cada disciplina só poderá ter uma única oferta por semestre na grade curricular **/
		
		addConstraint(new AllDifferentConstraint(disciplinas));
		
		/** Os horários de um professor não podem ser repetidos **/
		
		for (int i = 0; i < valuesProfessor.length; i++) {
			addConstraint(new TimeslotProfessorConstraint(timeslots, valuesProfessor[i]));
		}
		
		for (Timeslot timeslot1 : timeslots) {
			
			for (Timeslot timeslot2 : timeslots) {
				
				if (!timeslot1.getDisciplina().getName().equals(timeslot2.getDisciplina().getName())) {
					
					for (int i = 0; i < timeslot1.getHorarios().size(); i++) {
						
						for (int j = 0; j < timeslot2.getHorarios().size(); j++) {
							
							/** O mesmo local não pode ser alocado para duas ofertas diferentes no mesmo horário **/
							
							addConstraint(new TimeslotLocaisDiferentesConstraint(timeslot1.getLocais().get(i), timeslot1.getHorarios().get(i), timeslot2.getLocais().get(j), timeslot2.getHorarios().get(j)));
							
							/** Disciplinas de mesmo período não podem ter as aulas ofertadas no mesmo horário **/
							
//							addConstraint(new PeriodosDisciplinaConstraint(timeslot1.getDisciplina(), timeslot1.getHorarios().get(i), timeslot2.getDisciplina(), timeslot2.getHorarios().get(j), periodos));
						}
					}
				}
			}
		}
		
		for (Timeslot timeslot : timeslots) {
			
			for (int i = 0; i < timeslot.getHorarios().size(); i++) {
				
				Variable timeslot1 = timeslot.getHorarios().get(i);
				Variable timeslot2 = null;
				
				if ((i+1) < timeslot.getHorarios().size()) {
					
					timeslot2 = timeslot.getHorarios().get(++i);
					
					/** Deve haver um número mínimo de ofertas de aula consecutivas. Para este problema, o mínimo é dois. **/
					
					addConstraint(new TimeslotDiasIguasConstraint(timeslot1, timeslot2));
				}
			}
			
			for (int i = 0; i < timeslot.getLocais().size(); i++) {
				
				Variable local1 = timeslot.getLocais().get(i);
				Variable local2 = null;
				
				if ((i+1) < timeslot.getLocais().size()) {
					
					local2 = timeslot.getLocais().get(++i);
					
					/** A oferta mínima de uma mesma disciplina deve ocorrer no mesmo local. **/
					
					addConstraint(new LocaisIguaisConstraint(local1, local2));
				}
			}
			
			for (int i = 0; i < timeslot.getHorarios().size(); i++) {
				
				Variable timeslot1 = timeslot.getHorarios().get(i);
				Variable timeslot2 = null;
				
				if (i == timeslot.getHorarios().size() - 1)
					break;
				
				if (i == timeslot.getHorarios().size() - 2) {	
					timeslot2 = timeslot.getHorarios().get(0);
					
				} else if ((i+2) < timeslot.getHorarios().size() && (i != timeslot.getHorarios().size() - 2)) {
					timeslot2 = timeslot.getHorarios().get(2+i);
				}
				
				/** As ofertas de aula no mesmo dia não podem ultrapassar o mínimo definido para o problema **/
				
				addConstraint(new TimeslotDiasDiferentesConstraint(timeslot1, timeslot2, valuesDia));
			}
		}
	}
}
