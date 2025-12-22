package com.heliton.livrariag.dto;


public record LivroRequestDTO(
        @NotBlank
        String nome,
        String nacionalidade
) {}