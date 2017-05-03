package org.condominio.sj.backand.veiculoex;

import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.EXVEICULO_PATH)
public class VeiculoExService extends GenericService<VeiculoEx,Long>{

}
