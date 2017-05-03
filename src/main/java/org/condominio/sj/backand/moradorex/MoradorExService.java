package org.condominio.sj.backand.moradorex;


import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = ServicePath.EXMORADOR_PATH)
public class MoradorExService extends GenericService<MoradorEx, Long>{



	@Override
	public MoradorEx insert(@RequestBody MoradorEx obj){


		return super.insert(obj);
	}

}
