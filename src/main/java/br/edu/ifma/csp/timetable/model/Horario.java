package br.edu.ifma.csp.timetable.model;

public class Horario {
	
	private int id;
	private String horario;
	private String dia;
	private Integer hora;
	
	public Horario() {
		
	}
	
	public Horario(String dia, Integer hora) {
		this.dia = dia;
		this.hora = hora;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public String getDia() {
		return dia;
	}
	
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	public Integer getHora() {
		return hora;
	}
	
	public void setHora(Integer hora) {
		this.hora = hora;
	}
	
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	@Override
	public String toString() {
		return this.dia + "-" + this.hora;
	}
	
	public boolean equals(Horario outro) {	
		return (this.getDia().equals(outro.getDia()) && this.getHora().equals(outro.getHora()));
	}
}
