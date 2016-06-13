package br.edu.ifma.csp.timetable.model;

public class Local {
	
	private int id;
	private String nome;
	private int capacidade;
	
	public Local() {

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

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public String toString() {
		return this.getNome();
	}
}