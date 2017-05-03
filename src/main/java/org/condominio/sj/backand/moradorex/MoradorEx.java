package org.condominio.sj.backand.moradorex;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.condominio.sj.backand.apartamento.Apartamento;
import org.condominio.sj.backand.z.utils.BaseEntity;

@Entity
@Table(name="tb_morador_ex")
@AttributeOverride(name="id" , column=@Column(name="pk_id"))
public class MoradorEx extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;

	private String nome;

	private String fone;

	private String email;

	//Captura  data e hora
	@Temporal(TemporalType.TIMESTAMP)
	private Date datamudanca;

	@ManyToOne
	@JoinColumn(name = "ap_id")
	private Apartamento ap;

	public MoradorEx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MoradorEx(String nome, String fone, String email, Date datamudanca, Apartamento ap) {
		super();
		this.nome = nome;
		this.fone = fone;
		this.email = email;
		this.datamudanca = datamudanca;
		this.ap = ap;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public Date getDatamudanca() {
		return datamudanca;
	}

	public void setDatamudanca(Date datamudanca) {
		this.datamudanca = datamudanca;
	}

	public Apartamento getAp() {
		return ap;
	}

	public void setAp(Apartamento ap) {
		this.ap = ap;
	}

	//Metodo para salvar os dados dos ex-Moradores  da tabela MoradorService para a tabela MoradorExService
	public void saveMoradorEx(String nome, String fone, String email ,Apartamento ap){
		this.nome =nome;
		this.fone = fone;
		this.email = email;
		this.ap = ap;

	}

}
