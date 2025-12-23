package com.heliton.livrariag.dto;

import java.math.BigDecimal;
import java.util.Set;

public record LivroDetalheResponseDTO(
        Integer codl,
        String titulo,
        String editora,
        Integer edicao,
        Integer anoPublicacao,
        BigDecimal valor,
        Set<AutorResponseDTO> autores
) {}

