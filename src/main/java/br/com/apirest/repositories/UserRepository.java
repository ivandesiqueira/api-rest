package br.com.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apirest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
