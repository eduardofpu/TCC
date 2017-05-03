package org.condominio.sj.backand.vender.apartamento;

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
@RequestMapping(path=ServicePath.VENDERAPE_PATH)
public class VenderApartamentoService extends GenericService<VenderApartamento,Long>{



	@Autowired
	private CurrentUser currentUser;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VenderApartamentoRepository venderApartamentoRepository;



	@Override
	public List<VenderApartamento> findAll() {
		UserEntity userVenderApe = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		return this.venderApartamentoRepository.findByUserVenderApe(userVenderApe);
	}

	@Override
	public VenderApartamento insert(@RequestBody VenderApartamento vender) {

		vender.setUserVenderApe(this.userRepository.findByEmail(currentUser.getActiveUser().getEmail()));

		return super.insert(vender);
	}

	@Override
	public void update(@RequestBody VenderApartamento vender) {
		validateUserRequest( vender);



		super.update( vender);
	}

	@Override
	public void delete(@RequestBody VenderApartamento vender) {
		validateUserRequest( vender);

		super.delete( vender);
	}

	private UserEntity validateUserRequest(VenderApartamento  vender) {
		UserEntity userVenderApe = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		if ( vender.getUserVenderApe().getEmail().compareToIgnoreCase(userVenderApe.getEmail()) != 0) {
			throw new SecurityException();
		}

		return userVenderApe;
	}




}
