package org.condominio.sj.backand.veiculo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.condominio.sj.backand.veiculoex.VeiculoEx;
import org.condominio.sj.backand.veiculoex.VeiculoExRepository;
import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.VEICULO_PATH)
public class VeiculoService extends GenericService<Veiculo,Long>{

	@Autowired
	private VeiculoRepository v;

	@Autowired
	private VeiculoExRepository veiculoExRepository;


	@Override
	@RequestMapping(value = "/ordenarveiculos")
	public List<Veiculo> OrdenarVeiculos(){

		return this.v.findAllApv();

	}

	@Override
	@Transactional
	public void delete(@RequestBody Veiculo deletar){

		VeiculoEx veiculoex = new VeiculoEx();
		// Captura a data da mudanca
		veiculoex.setDatamudanca(new Date());
		veiculoex.saveVeiculoEx(deletar.getModelo(), deletar.getCor(), deletar.getPlaca(), deletar.getMotorista(), deletar.getCnh(), deletar.getApv());
		veiculoExRepository.save(veiculoex);

		super.delete(deletar);
	}



}
