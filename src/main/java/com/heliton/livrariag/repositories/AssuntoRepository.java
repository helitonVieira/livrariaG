package com.heliton.livrariag.repositories;

import com.heliton.livrariag.model.Assunto;
import com.heliton.livrariag.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {

    List<Assunto> findAllByOrderByDescricaoAsc();
}
