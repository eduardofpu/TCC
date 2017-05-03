package org.condominio.sj.backand.veiculoex;

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
@Table(name="tb_veiculo_ex")
@AttributeOverride(name="id", column = @Column(name="pk_id"))
public class VeiculoEx extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;


	//Captura  data e hora
	@Temporal(TemporalType.TIMESTAMP)
	private Date datamudanca;
	private String modelo;
	private String cor;
	private String placa;
	private String motorista;
	private String cnh;

	@ManyToOne
	@JoinColumn(name = "ap_id_v")
	private Apartamento apv;

	public VeiculoEx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VeiculoEx(Date datamudanca, String modelo, String cor, String placa, String motorista, String cnh,
			Apartamento apv) {
		super();
		this.datamudanca = datamudanca;
		this.modelo = modelo;
		this.cor = cor;
		this.placa = placa;
		this.motorista = motorista;
		this.cnh = cnh;
		this.apv = apv;
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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMotorista() {
		return motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public Apartamento getApv() {
		return apv;
	}

	public void setApv(Apartamento apv) {
		this.apv = apv;
	}

	public void saveVeiculoEx( String modelo, String cor, String placa, String motorista, String cnh,Apartamento apv) {

		this.modelo = modelo;
		this.cor = cor;
		this.placa = placa;
		this.motorista = motorista;
		this.cnh = cnh;
		this.apv = apv;
	}


}
