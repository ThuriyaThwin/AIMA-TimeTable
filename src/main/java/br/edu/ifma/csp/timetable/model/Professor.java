package br.edu.ifma.csp.timetable.model;

public class Professor {
	
	private int id;
	private String nome;
	
	public Professor() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return this.getNome();
	}
}
