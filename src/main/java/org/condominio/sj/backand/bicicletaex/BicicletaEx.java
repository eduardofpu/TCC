package org.condominio.sj.backand.bicicletaex;

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
@Table(name="tb_bicicleta_ex")
@AttributeOverride(name="id", column = @Column(name="pk_id"))
public class BicicletaEx extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;

	//Captura  data e hora
	@Temporal(TemporalType.TIMESTAMP)
	private Date datamudanca;
	private String modelo;
	private String cor;
	private String dono;
	@ManyToOne
	@JoinColumn(name = "ap_id_b")
	private Apartamento apb;
	public BicicletaEx() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BicicletaEx(Date datamudanca, String modelo, String cor, String dono, Apartamento apb) {
		super();
		this.datamudanca = datamudanca;
		this.modelo = modelo;
		this.cor = cor;
		this.dono = dono;
		this.apb = apb;
	}





	public Date getDatamudanca() {
		return datamudanca;
	}
	public void setDatamudanca(Date datamudanca) {
		this.datamudanca = datamudanca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getDono() {
		return dono;
	}
	public void setDono(String dono) {
		this.dono = dono;
	}
	public Apartamento getApb() {
		return apb;
	}
	public void setApb(Apartamento apb) {
		this.apb = apb;
	}
	public void saveBicicletaEx( String modelo, String cor, String dono, Apartamento apb) {

		this.modelo = modelo;
		this.cor = cor;
		this.dono = dono;
		this.apb = apb;
	}






}
