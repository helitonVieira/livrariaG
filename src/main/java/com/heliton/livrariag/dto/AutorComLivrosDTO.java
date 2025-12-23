package com.heliton.livrariag.dto;

import java.util.Set;

public record AutorComLivrosDTO(
        Integer id,
        String nome,
        Set<LivroResponseDTO> livros
) {
}
