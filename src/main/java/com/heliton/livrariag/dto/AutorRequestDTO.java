package com.heliton.livrariag.dto;


import jakarta.validation.constraints.NotBlank;

public record AutorRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome
) {}