package com.heliton.livrariag.resources;

import com.heliton.livrariag.dto.AssuntoRequestDTO;
import com.heliton.livrariag.dto.AssuntoResponseDTO;
import com.heliton.livrariag.services.AssuntoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assunto")
public class AssuntoController {

    private final AssuntoService service;

    public AssuntoController(AssuntoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AssuntoResponseDTO> criar(
            @Valid @RequestBody AssuntoRequestDTO dto) {

        AssuntoResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AssuntoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssuntoResponseDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public AssuntoResponseDTO atualizar(
            @PathVariable Integer id,
            @RequestBody AssuntoRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
