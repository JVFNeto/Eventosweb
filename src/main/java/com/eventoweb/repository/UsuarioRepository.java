package com.eventoweb.repository;

import com.eventoweb.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findByLogin(String login);
}
