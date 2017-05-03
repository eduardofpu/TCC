package org.condominio.sj.backand.fotos.aluguel.apartamento;

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
@RequestMapping(path = ServicePath.FOTOSALUGARAPE_PATH)
public class FotosAluguelApartamentoService extends GenericService<FotosAluguelApartamento,Long>{

	@Autowired
	private CurrentUser currentUser;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FotosAluguelApartamentoRepository fotoRepository;



	@Override
	public List<FotosAluguelApartamento> findAll() {
		UserEntity userFotoApe = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		return this.fotoRepository.findByUserFotoApe(userFotoApe);
	}

	@Override
	public FotosAluguelApartamento insert(@RequestBody FotosAluguelApartamento foto) {

		foto.setUserFotoApe(this.userRepository.findByEmail(currentUser.getActiveUser().getEmail()));

		return super.insert(foto);
	}

	@Override
	public void update(@RequestBody FotosAluguelApartamento foto) {
		validateUserRequest( foto);



		super.update( foto);
	}

	@Override
	public void delete(@RequestBody FotosAluguelApartamento foto) {
		validateUserRequest( foto);

		super.delete( foto);
	}

	private UserEntity validateUserRequest(FotosAluguelApartamento foto) {
		UserEntity userFotoApe = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		if ( foto.getUserFotoApe().getEmail().compareToIgnoreCase(userFotoApe.getEmail()) != 0) {
			throw new SecurityException();
		}

		return userFotoApe;
	}




}
