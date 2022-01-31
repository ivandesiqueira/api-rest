package br.com.apirest.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apirest.model.User;
import br.com.apirest.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserControler {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Long id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);

	}

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User obj) {
		User newobj = userService.update(id, obj);
		return ResponseEntity.ok().body(newobj);
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User obj) {
		User newObj = userService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/(id)").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();

	}

}
