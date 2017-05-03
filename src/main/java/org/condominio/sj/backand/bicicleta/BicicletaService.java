package org.condominio.sj.backand.bicicleta;

import java.util.Date;

import javax.transaction.Transactional;

import org.condominio.sj.backand.bicicletaex.BicicletaEx;
import org.condominio.sj.backand.bicicletaex.BicicletaExRepository;
import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.BICICLETA_PATH)
public class BicicletaService extends GenericService<Bicicleta,Long>{


	@Autowired
	private BicicletaExRepository bicicletaExRepository;


	@Override
	@Transactional
	public void delete(@RequestBody Bicicleta deletar){

		BicicletaEx bicicletaex = new BicicletaEx();
		//Captura a data de mudanca
		bicicletaex.setDatamudanca(new Date());
		bicicletaex.saveBicicletaEx(deletar.getModelo(), deletar.getCor(), deletar.getDono(), deletar.getApb());
		bicicletaExRepository.save(bicicletaex);

		super.delete(deletar);
	}

}
