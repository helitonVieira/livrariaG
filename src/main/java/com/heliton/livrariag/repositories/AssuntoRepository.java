package com.heliton.livrariag.repositories;

import com.heliton.livrariag.model.Assunto;
import com.heliton.livrariag.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {
}
