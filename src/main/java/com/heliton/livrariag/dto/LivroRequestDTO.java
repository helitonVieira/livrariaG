package com.heliton.livrariag.dto;

import java.math.BigDecimal;

public record LivroRequestDTO(
        String titulo,
        String editora,
        Integer edicao,
        Integer anoPublicacao,
        BigDecimal valor
) {}
