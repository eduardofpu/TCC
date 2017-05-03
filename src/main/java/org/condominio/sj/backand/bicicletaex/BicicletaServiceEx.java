package org.condominio.sj.backand.bicicletaex;

import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.EXBICICLETA_PATH)
public class BicicletaServiceEx extends GenericService<BicicletaEx,Long>{

}
