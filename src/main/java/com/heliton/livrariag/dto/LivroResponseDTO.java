package com.heliton.livrariag.dto;

import java.math.BigDecimal;


public record LivroResponseDTO(
        Integer codl,
        String titulo,
        String editora,
        Integer edicao,
        Integer anoPublicacao,
        BigDecimal valor
) {}

