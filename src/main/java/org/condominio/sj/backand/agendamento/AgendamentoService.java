package org.condominio.sj.backand.agendamento;

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
@RequestMapping(path=ServicePath.AGENDAMENTO_PATH)
public class AgendamentoService extends GenericService<Agendamento,Long>{



	@Autowired
	private CurrentUser currentUser;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AgendamentoRepository agendamentoRepository;



	@Override
	public List<Agendamento> findAll() {
		UserEntity userAgenda = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		return this.agendamentoRepository.findByUserAgenda(userAgenda);
	}

	@Override
	public  Agendamento insert(@RequestBody Agendamento agenda) {

		agenda.setUserAgenda(this.userRepository.findByEmail(currentUser.getActiveUser().getEmail()));


		return super.insert(agenda);
	}

	@Override
	public void update(@RequestBody Agendamento  agenda) {
		validateUserRequest(agenda);



		super.update( agenda);
	}



	@Override
	public void delete(@RequestBody Agendamento agenda) {
		validateUserRequest(agenda);

		super.delete(agenda);
	}

	private UserEntity validateUserRequest(Agendamento  agenda) {
		UserEntity userAgenda = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

		if ( agenda.getUserAgenda().getEmail().compareToIgnoreCase(userAgenda.getEmail()) != 0) {
			throw new SecurityException();
		}

		return userAgenda;
	}






	// Mostra tabela ordenada
	@Override
	@RequestMapping(value = "/mostrar")
	public List<Agendamento> MostrarAgenda(){

		return this.agendamentoRepository.findAllData();

	}
	// Realiza serviço separado para os funcionarios editar entrada e saida do agendamento
	@Override
	@RequestMapping(value = "/update")
	public void updateMostraAgendamento(@RequestBody Agendamento  agenda) {


		super.update( agenda);
	}





}
