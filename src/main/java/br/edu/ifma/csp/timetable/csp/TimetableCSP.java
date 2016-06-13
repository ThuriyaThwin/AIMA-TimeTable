package br.edu.ifma.csp.timetable.csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import dummy.dao.DisciplinaDao;
import dummy.dao.HorarioDao;
import dummy.dao.LocalDao;
import dummy.dao.ProfessorDao;
import dummy.model.Disciplina;
import dummy.model.Horario;
import dummy.model.Local;
import dummy.model.Professor;

public class TimetableCSP extends CSP {
	
	ProfessorDao professorDao = new ProfessorDao();
	DisciplinaDao disciplinaDao = new DisciplinaDao();
	HorarioDao horarioDao = new HorarioDao();
	LocalDao localDao = new LocalDao();
	
	public TimetableCSP() {
		
		List<Professor> professores = professorDao.findAll();
		List<Local> locais = localDao.findAll();
		List<Disciplina> disciplinas = disciplinaDao.findAll();
		List<Horario> horarios = horarioDao.findAll();
		
		String dias[] = {"SEG", "TER", "QUA", "QUI", "SEX"};
		String horas[] = {"16:50", "17:40", "18:30", "19:20", "20:10", "21:00", "21:50"};
		
		String strHorarios[] = {"SEG 16:50", "SEG 17:40", "SEG 18:30", "SEG 19:20", "SEG 20:10", "SEG 21:00", "SEG 21:50",
								"TER 16:50", "TER 17:40", "TER 18:30", "TER 19:20", "TER 20:10", "TER 21:00", "TER 21:50",
								"QUA 16:50", "QUA 17:40", "QUA 18:30", "QUA 19:20", "QUA 20:10", "QUA 21:00", "QUA 21:50",
								"QUI 16:50", "QUI 17:40", "QUI 18:30", "QUI 19:20", "QUI 20:10", "QUI 21:00", "QUI 21:50",
								"SEX 16:50", "SEX 17:40", "SEX 18:30", "SEX 19:20", "SEX 20:10", "SEX 21:00", "SEX 21:50"};
		
		
		HashMap<Integer, List<Disciplina>> preferencias = new HashMap<Integer, List<Disciplina>>();
		
		int i = 0;
		
		//Disciplinas
		
		for (Disciplina disciplina : disciplinas) {
			
			Variable v = new Variable("D_" + (i+1));
			addVariable(v);
			setDomain(v, new Domain(disciplinas));
			
			//Horários
			
			for (int j = 0; j < disciplina.getCreditos(); j++) {
				
				Variable dia = new Variable(v.getName() + "-T_" + (j+1));
				addVariable(dia);
				setDomain(dia, new Domain(dias));
				
				/*Variable h = new Variable("H_D" + (i+1) + "_" + (j+1));
				addVariable(h);
				setDomain(h, new Domain(horas));*/
			}
			
			i += 1;
			
			if (i == 5)
				break;
		}
		
		//Professores
		
		for (int j = 0; j < professores.size(); j++) {
			
			//Variable v = new Variable("P_" + (j+1));
			//addVariable(v);
			//setDomain(v, new Domain(professores));
			preferencias.put(Integer.valueOf(j+1), disciplinaDao.findAllByProfessor(j+1));
		}
		
		ArrayList<String> dom = new ArrayList<String>();
		
		for (Professor p : professores) {
			
			List<Disciplina> list = preferencias.get(new Integer(p.getId()));
			
			if (list != null) {
			
				for (Disciplina d : list) {
					
					for (int j = 0; j < d.getCreditos(); j++) {
						
						String token = new String(p.getId() + "_" + d.getId() + "_" + (j+1));
						dom.add(token);
					}
				}
			}
		}
		
		System.out.println(dom);
		
		//Locais
		
		/*for (int j = 0; j < locais.size(); j++) {
			
			Variable v = new Variable("L_" + (j+1));
			addVariable(v);
			setDomain(v, new Domain(locais));
		}*/
		
//		addConstraint(new AllDifferentConstraint(getVariables(), "P"));
//		addConstraint(new AllDifferentConstraint(getVariables(), "D"));
//		addConstraint(new HorarioConstraint(getVariables()));
//		addConstraint(new DiasDiferentesConstraint(getVariables()));
		
		/*for (Variable v : getVariables()) {
			
			if (v.getName().startsWith("D_")) {
				
				for (Variable dia : getVariables()) {
					
					if (v.getName() != dia.getName()) {
					
						if (dia.getName().contains("-")) {
						
							String tokenDia = dia.getName().split("-")[0];
								
							if (v.getName().equals(tokenDia))
								System.out.println(dia.getName());
						}
					}
				}
			}
		}*/
	}
}