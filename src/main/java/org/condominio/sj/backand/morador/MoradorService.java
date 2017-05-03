package org.condominio.sj.backand.morador;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.condominio.sj.backand.moradorex.MoradorEx;
import org.condominio.sj.backand.moradorex.MoradorExRepository;
import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.MORADOR_PATH)
public class MoradorService extends GenericService<Morador,Long>{


	@Autowired
	private MoradorRepository m;


	@Autowired
	private MoradorExRepository moradorEx;



	@Override
	@RequestMapping(value = "/ordenarapemoradores")
	public List<Morador> OrdenarMoradores() {

		return this.m.findAllAp();
	}

	@Override
	@Transactional
	public void delete(@RequestBody Morador deletar){


		MoradorEx moradorex = new MoradorEx();

		//Data da mudanca e capturada automaticamente
		moradorex.setDatamudanca(new Date());

		moradorex.saveMoradorEx(deletar.getNome(), deletar.getFone(),deletar.getEmail(), deletar.getAp());

		moradorEx.save(moradorex);

		super.delete(deletar);
	}



}
