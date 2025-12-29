package com.heliton.livrariag.repositories;

import com.heliton.livrariag.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findAllByOrderByTituloAsc();
}
