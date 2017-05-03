package org.condominio.sj.backand.alugar.apartamento;

import java.util.List;

import org.condominio.sj.backand.user.UserEntity;
import org.condominio.sj.backand.user.UserRepository;
import org.condominio.sj.backand.z.security.CurrentUser;
import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = ServicePath.ALUGARAPE_PATH)
public class AlugarApartamentoService extends GenericService<AlugarApartamento,Long>{

	@Autowired
	private CurrentUser currentUser;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AlugarApartamentoRepository alugarApartamentoRepository;



	@Override
	public List<AlugarApartamento> findAll() {
		UserEntity userAlugarApe = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		return this.alugarApartamentoRepository.findByUserAlugarApe(userAlugarApe);
	}

	@Override
	public AlugarApartamento insert(@RequestBody AlugarApartamento alugar) {

		alugar.setUserAlugarApe(this.userRepository.findByEmail(currentUser.getActiveUser().getEmail()));

		return super.insert(alugar);
	}

	@Override
	public void update(@RequestBody AlugarApartamento alugar) {
		validateUserRequest( alugar);



		super.update( alugar);
	}

	@Override
	public void delete(@RequestBody AlugarApartamento alugar) {
		validateUserRequest( alugar);

		super.delete( alugar);
	}

	private UserEntity validateUserRequest(AlugarApartamento  alugar) {
		UserEntity userAlugarApe = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		if ( alugar.getUserAlugarApe().getEmail().compareToIgnoreCase(userAlugarApe.getEmail()) != 0) {
			throw new SecurityException();
		}

		return userAlugarApe;
	}



}
