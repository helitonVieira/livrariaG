package com.heliton.livrariag.services;

import com.heliton.livrariag.dto.AssuntoRequestDTO;
import com.heliton.livrariag.dto.AssuntoResponseDTO;
import com.heliton.livrariag.model.Assunto;
import com.heliton.livrariag.repositories.AssuntoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssuntoService {

    private final AssuntoRepository repository;

    public AssuntoService(AssuntoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AssuntoResponseDTO criar(AssuntoRequestDTO dto) {
        Assunto assunto = new Assunto(dto.descricao());
        Assunto salvo = repository.save(assunto);

        return new AssuntoResponseDTO(
                salvo.getId(),
                salvo.getDescricao()
        );
    }

    @Transactional(readOnly = true)
    public List<AssuntoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(a -> new AssuntoResponseDTO(a.getId(), a.getDescricao()))
                .toList();
    }

    @Transactional(readOnly = true)
    public AssuntoResponseDTO buscarPorId(Integer id) {
        Assunto assunto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assunto não encontrado"));

        return new AssuntoResponseDTO(assunto.getId(), assunto.getDescricao());
    }

    public AssuntoResponseDTO atualizar(Integer id, AssuntoRequestDTO dto) {
        Assunto assunto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assunto não encontrado"));

        assunto.setDescricao(dto.descricao());
        return toResponse(repository.save(assunto));
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    private AssuntoResponseDTO toResponse(Assunto assunto) {
        return new AssuntoResponseDTO(
                assunto.getId(),
                assunto.getDescricao()
        );
    }
}
