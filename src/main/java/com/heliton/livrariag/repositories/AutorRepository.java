package com.heliton.livrariag.repositories;

import com.heliton.livrariag.model.Autor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    @EntityGraph(attributePaths = "livros")
    Optional<Autor> findWithLivrosById(Integer id);

    List<Autor> findAllByOrderByNomeAsc();
}
