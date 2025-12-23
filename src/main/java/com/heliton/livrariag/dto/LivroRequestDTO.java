package com.heliton.livrariag.dto;

import java.math.BigDecimal;
import java.util.Set;

public record LivroRequestDTO(
        String titulo,
        String editora,
        Integer edicao,
        Integer anoPublicacao,
        BigDecimal valor,
        Set<Integer> autoresIds
) {

}
