package br.edu.ifma.csp.timetable.model;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Variable;

public class Timeslot {
	
	private Variable professor;
	private Variable disciplina;
	private Variable local;
	private List<Variable> horarios;
	
	public Timeslot(Variable professor, Variable disciplina) {
		this.professor = professor;
		this.disciplina = disciplina;
		this.horarios = new ArrayList<Variable>();
	}
	
	public void addLocal(Variable local) {
		this.local = local;
	}
	
	public void addTimeslot(Variable horario) {
		this.horarios.add(horario);
	}
	
	public List<Variable> getHorarios() {
		return horarios;
	}
	
	public Variable getProfessor() {
		return professor;
	}
	
	public Variable getLocal() {
		return local;
	}
	
	public Variable getDisciplina() {
		return disciplina;
	}
}
