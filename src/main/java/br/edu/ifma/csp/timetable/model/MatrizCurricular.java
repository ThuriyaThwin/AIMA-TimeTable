package br.edu.ifma.csp.timetable.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

public class MatrizCurricular extends Entidade {

	private static final long serialVersionUID = -932527619399754046L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_MATRIZ")
	private int id;
	
	@NotNull
	@Column(name="ANO")
	private int ano;
	
	@NotNull
	@Column(name="TURNO")
	private String turno;
	
	@NotNull
	@Column(name="SEMESTRES")
	private int semestres;
	
	@NotNull
	@JoinColumn(name="ID_CURSO")
	private Curso curso;
	
	public MatrizCurricular() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getSemestres() {
		return semestres;
	}

	public void setSemestres(int semestres) {
		this.semestres = semestres;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}