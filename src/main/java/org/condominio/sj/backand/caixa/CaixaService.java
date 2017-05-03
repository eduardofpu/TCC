
package org.condominio.sj.backand.caixa;

import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path=ServicePath.CAIXA_PATH)
public class CaixaService extends GenericService<Caixa,Long>{




	@Override
	public Caixa insert(@RequestBody Caixa caixa){

		return super.insert(caixa);
	}




}
