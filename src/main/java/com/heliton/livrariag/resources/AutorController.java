package com.heliton.livrariag.resources;

import com.heliton.livrariag.dto.AutorRequestDTO;
import com.heliton.livrariag.dto.AutorResponseDTO;
import com.heliton.livrariag.dto.LivroRequestDTO;
import com.heliton.livrariag.dto.LivroResponseDTO;
import com.heliton.livrariag.services.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> criar(
            @Valid @RequestBody AutorRequestDTO dto) {

        AutorResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public AutorResponseDTO atualizar(
            @PathVariable Integer id,
            @RequestBody AutorRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
