package org.condominio.sj.backand.fotos.venda.apartamento;

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
@RequestMapping(path = ServicePath.FOTOSVENDAPE_PATH)
public class FotosVendaApartamentoService extends GenericService<FotosVendaApartamento,Long>{

	@Autowired
	private CurrentUser currentUser;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FotosVendaApartamentoRepository fotoRepository;


	@Override
	public List<FotosVendaApartamento> findAll() {
		UserEntity userFotoVendaApe = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		return this.fotoRepository.findByUserFotoVendaApe(userFotoVendaApe);
	}

	@Override
	public FotosVendaApartamento insert(@RequestBody FotosVendaApartamento foto) {

		foto.setUserFotoVendaApe(this.userRepository.findByEmail(currentUser.getActiveUser().getEmail()));

		return super.insert(foto);
	}

	@Override
	public void update(@RequestBody FotosVendaApartamento foto) {
		validateUserRequest( foto);



		super.update( foto);
	}

	@Override
	public void delete(@RequestBody FotosVendaApartamento foto) {
		validateUserRequest( foto);

		super.delete( foto);
	}

	private UserEntity validateUserRequest(FotosVendaApartamento foto) {
		UserEntity userFotoVendaApe = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		if ( foto.getUserFotoVendaApe().getEmail().compareToIgnoreCase(userFotoVendaApe.getEmail()) != 0) {
			throw new SecurityException();
		}

		return userFotoVendaApe;
	}



}
