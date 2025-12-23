package com.heliton.livrariag.controller;

import com.heliton.livrariag.dto.LivroDetalheResponseDTO;
import org.springframework.web.bind.annotation.RestController;

import com.heliton.livrariag.dto.LivroRequestDTO;
import com.heliton.livrariag.dto.LivroResponseDTO;
import com.heliton.livrariag.services.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public LivroDetalheResponseDTO criar(@RequestBody LivroRequestDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<LivroDetalheResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public LivroDetalheResponseDTO buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LivroDetalheResponseDTO atualizar(
            @PathVariable Integer id,
            @RequestBody LivroRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
