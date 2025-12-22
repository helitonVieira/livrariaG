package com.heliton.livrariag.dto;


import jakarta.validation.constraints.NotBlank;

public record AssuntoRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String descricao
) {}