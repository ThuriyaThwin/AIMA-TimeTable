package br.edu.ifma.csp.timetable.model;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="DISCIPLINA")
public class Disciplina extends Entidade {

	private static final long serialVersionUID = -359725241949986880L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_DISCIPLINA")
	private int id;
	
	@NotNull
	@Column(name="CODIGO")
	private String codigo;
	
	@NotNull
	@Column(name="DESCRICAO")
	private String descricao;
	
	public Disciplina() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}