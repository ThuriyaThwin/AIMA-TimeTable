package br.edu.ifma.csp.timetable.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Entidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name="USUARIO_ULT_ALTERACAO")
	private String usuarioUltimaAlteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column(name="DATA_ULT_ALTERACAO")
	private Date dataUltimaAlteracao;
	
	@Version
	@NotNull
	@Column(name="VERSAO")
	private int versao;
	
	public abstract int getId();

	public String getUsuarioUltimaAlteracao() {
		return usuarioUltimaAlteracao;
	}

	public void setUsuarioUltimaAlteracao(String usuarioUltimaAlteracao) {
		this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}
}