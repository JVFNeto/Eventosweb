package com.eventoweb.repository;

import com.eventoweb.models.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//essa interface será intânciada e utilizada para metodos CRUD
@Repository
public interface EventoRepository extends CrudRepository<Evento, String>{
    Evento findByCodigo(long codigo);
}
