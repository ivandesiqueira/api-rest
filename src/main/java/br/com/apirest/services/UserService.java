package br.com.apirest.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.apirest.model.User;
import br.com.apirest.repositories.UserRepository;
import br.com.apirest.services.exeptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Não Encontrado!" + " Id:" + id + " Tipo:" + User.class.getName()));

	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User update(Long id, User obj) {
		User newObj = findById(id);
		newObj.setName(obj.getName());
		newObj.setLogin(obj.getLogin());
		newObj.setPassword(obj.getPassword());
		return userRepository.save(newObj);
	}

	public User create(User obj) {
		obj.setId(0);
		return userRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		userRepository.deleteById(id);
	}

}
