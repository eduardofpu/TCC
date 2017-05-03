package org.condominio.sj.backand.alugar.garagem;

import java.util.List;

import org.condominio.sj.backand.z.utils.ServiceMap;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = ServicePath.BGP_PATH)
public class BuscarAlugarGaragemPlacaService implements ServiceMap{


	@Autowired
	private AlugarGaragemRepository g;

	@RequestMapping(method = {RequestMethod.POST})
	@ResponseBody
	public List<AlugarGaragem> BuscarAlugarGaragemPlacaService(@RequestBody BuscarAlugarGaragemPlaca placa ){


		return this.g.findByPlacaContaining(placa.getPlaca());




	}


}
