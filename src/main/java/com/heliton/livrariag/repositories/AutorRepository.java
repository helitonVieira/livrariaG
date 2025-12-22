package com.heliton.livrariag.repositories;

import com.heliton.livrariag.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, long> {
}
