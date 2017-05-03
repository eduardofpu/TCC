package org.condominio.sj.backand.prestador.servico;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.condominio.sj.backand.z.utils.BaseEntity;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_prestador_servico")
@AttributeOverride(name = "id", column = @Column(name = "id_prestador_servico") )
public class PrestadorServico extends BaseEntity<Long> {

	//private static final long serialVersionUID = 1L;
	private static final long serialVersionUID = 201505091506L;




	@Column(name = "nome_ps", length = 120, nullable = false)
	private String prestador;

	@Column(name = "email_ps", length = 255, nullable = false, unique = true)
	private String email;

	@Column(name = "fone_ps", length = 120, nullable = false)
	private String fone;
	@CPF(message="CPF inválido.")
	@Column(name = "cpf_ps", length = 255, nullable = false, unique = true)
	private String cpf;

	@Column(name = "rg_ps", length = 255, nullable = false, unique = true)
	private String rg;

	@Column(name = "empresa_ps", length = 120, nullable = false)
	private String 	empresa;

	@Column(name = "funcao_ps", length = 120, nullable = false)
	private String funcao;

	@Column(name = "cep_ps", nullable = true)
	private String cep;

	@Column(name = "logradouro_ps",length = 120, nullable = true)
	private String logradouro;

	@Column(name = "numero_ps",length = 4, nullable = true)
	private String numero;


	@Column(name = "bairro_ps",length = 120, nullable = true)
	private String bairro;

	@Column(name = "localidade_ps", length = 120,nullable = true)
	private String localidade;

	@Column(name = "uf_ps", length = 2,nullable = true)
	private String  uf;

	@Column(name = "complemento_ps", length = 255,nullable = true)
	private String  complemento;



	@Lob
	private byte[] file;

	@Column(name = "mime_type", nullable = true)
	private String mimeType;


	public PrestadorServico() {

	}


	public PrestadorServico(String prestador, String email, String fone, String cpf, String rg, String empresa,
			String funcao, String cep, String logradouro, String numero, String bairro, String localidade, String uf,
			String complemento, byte[] file, String mimeType) {
		super();
		this.prestador = prestador;
		this.email = email;
		this.fone = fone;
		this.cpf = cpf;
		this.rg = rg;
		this.empresa = empresa;
		this.funcao = funcao;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.complemento = complemento;
		this.file = file;
		this.mimeType = mimeType;
	}


	public String getPrestador() {
		return prestador;
	}


	public void setPrestador(String prestador) {
		this.prestador = prestador;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFone() {
		return fone;
	}


	public void setFone(String fone) {
		this.fone = fone;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getLocalidade() {
		return localidade;
	}


	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public byte[] getFile() {
		return file;
	}


	public void setFile(byte[] file) {
		this.file = file;
	}


	public String getMimeType() {
		return mimeType;
	}


	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}


}
