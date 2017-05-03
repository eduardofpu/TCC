package org.condominio.sj.backand.z.utils;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.condominio.sj.backand.agendamento.Agendamento;
import org.condominio.sj.backand.agendamento.AgendamentoRepository;
import org.condominio.sj.backand.alugar.garagem.AlugarGaragem;
import org.condominio.sj.backand.alugar.garagem.AlugarGaragemRepository;
import org.condominio.sj.backand.apartamento.Apartamento;
import org.condominio.sj.backand.apartamento.ApartamentoRepository;
import org.condominio.sj.backand.funcionario.FuncionarioRepository;
import org.condominio.sj.backand.morador.Morador;
import org.condominio.sj.backand.morador.MoradorRepository;
import org.condominio.sj.backand.veiculo.Veiculo;
import org.condominio.sj.backand.veiculo.VeiculoRepository;
import org.condominio.sj.backand.z.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



public abstract class GenericService<T extends BaseEntity<ID>, ID extends Serializable> implements ServiceMap {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	protected JpaRepository<T, ID> genericRepository;

	@Autowired
	AgendamentoRepository agendamentoRepository;

	@Autowired
	FuncionarioRepository repository;

	@Autowired
	private ApartamentoRepository a;

	@Autowired
	private VeiculoRepository v;

	@Autowired
	private MoradorRepository m;

	@Autowired
	private AlugarGaragemRepository alugargaragem;



	@RequestMapping(method = RequestMethod.GET)
	public List<T> findAll() {
		this.LOGGER.debug("Requesting all records.");

		return this.genericRepository.findAll();
	}


	@RequestMapping(method = RequestMethod.POST)
	public T insert(@RequestBody T entityObject) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Saving the entity [%s].", entityObject));
		}

		return this.genericRepository.save(entityObject);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody T entityObject) {
		this.LOGGER.debug(String.format("Request to update the record [%s].", entityObject));

		if (entityObject.getId() == null) {
			String errorMessage = String.format("ID of entity [%s] cannot be null.", entityObject.getClass());
			this.LOGGER.error(errorMessage);
			throw new ServerException(errorMessage);
		}

		this.genericRepository.save(entityObject);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestBody T entityObject) {
		this.LOGGER.debug(String.format("Request to delete the record [%s].", entityObject));

		this.genericRepository.delete(entityObject);
	}


	// serviço separado para ordenar a tabela agendamento com parametro desc
	public List<Agendamento> MostrarAgenda() {
		this.LOGGER.debug("Requesting all records.");
		return  this.agendamentoRepository.findAllData();
	}


	// serviço separado para os funcionarios fazerem update no agendamento de entrada e saida

	public void updateMostraAgendamento(@RequestBody @Validated T entityObject) {
		this.LOGGER.debug(String.format("Request to update the record [%s].", entityObject));

		this.LOGGER.debug(String.format("Request to update the record [%s].", entityObject));

		if (entityObject.getId() == null) {
			String errorMessage = String.format("ID of entity [%s] cannot be null.", entityObject.getClass());
			this.LOGGER.error(errorMessage);
			throw new ServerException(errorMessage);
		}

		this.genericRepository.save(entityObject);
	}



	public List<Apartamento> OrdenarApartamentos() {
		this.LOGGER.debug("Requesting all records.");
		return this.a.findAllDescricao();
	}

	public List<Veiculo> OrdenarVeiculos() {
		this.LOGGER.debug("Requesting all records.");
		return this.v.findAllApv();
	}


	public List<Morador> OrdenarMoradores() {
		this.LOGGER.debug("Requesting all records.");
		return this.m.findAllAp();
	}



	public List<AlugarGaragem> MostrarAlugarGaragem(){
		this.LOGGER.debug("Requesting all records.");
		return this.alugargaragem.findAllModelo();
	}


}
