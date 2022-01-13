package com.eventoweb.repository;

import com.eventoweb.models.Convidado;
import com.eventoweb.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
    Iterable<Convidado> findByEvento(Evento evento);
    Convidado findByRg(String rg);
}
