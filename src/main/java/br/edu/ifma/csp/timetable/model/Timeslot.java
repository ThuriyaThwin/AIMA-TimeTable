package br.edu.ifma.csp.timetable.model;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Variable;

public class Timeslot {
	
	private Variable professor;
	private Variable disciplina;
	private List<Variable> locais;
	private List<Variable> horarios;
	
	public Timeslot(Variable professor, Variable disciplina) {
		this.professor = professor;
		this.disciplina = disciplina;
		this.locais = new ArrayList<Variable>();
		this.horarios = new ArrayList<Variable>();
	}
	
	public void addLocal(Variable local) {
		this.locais.add(local);
	}
	
	public void addTimeslot(Variable horario) {
		this.horarios.add(horario);
	}
	
	public List<Variable> getHorarios() {
		return horarios;
	}
	
	public List<Variable> getLocais() {
		return locais;
	}
	
	public Variable getProfessor() {
		return professor;
	}
	
	public Variable getDisciplina() {
		return disciplina;
	}
}
