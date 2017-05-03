package org.condominio.sj.backand.ocorrencia;




import java.util.Date;

import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.OCORRENCIA_PATH)
public class OcorrenciaService extends GenericService<Ocorrencia,Long>{

	@Override
	public Ocorrencia  insert(@RequestBody Ocorrencia ocorrencia){

		ocorrencia.setRegisterDate(new Date());

		return super.insert(ocorrencia);


	}

}
