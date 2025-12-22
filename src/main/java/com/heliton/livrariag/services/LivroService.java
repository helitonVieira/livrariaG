package com.heliton.livrariag.services;

import com.heliton.livrariag.model.Livro;
import com.heliton.livrariag.dto.LivroRequestDTO;
import com.heliton.livrariag.dto.LivroResponseDTO;
import com.heliton.livrariag.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public LivroResponseDTO criar(LivroRequestDTO dto) {
        Livro livro = toEntity(dto);
        return toResponse(repository.save(livro));
    }

    public List<LivroResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Integer id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return toResponse(livro);
    }

    public LivroResponseDTO atualizar(Integer id, LivroRequestDTO dto) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setTitulo(dto.titulo());
        livro.setEditora(dto.editora());
        livro.setEdicao(dto.edicao());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setValor(dto.valor());

        return toResponse(repository.save(livro));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    private Livro toEntity(LivroRequestDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setEditora(dto.editora());
        livro.setEdicao(dto.edicao());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setValor(dto.valor());
        return livro;
    }

    private LivroResponseDTO toResponse(Livro livro) {
        return new LivroResponseDTO(
                livro.getCodl(),
                livro.getTitulo(),
                livro.getEditora(),
                livro.getEdicao(),
                livro.getAnoPublicacao(),
                livro.getValor()
        );
    }
}
