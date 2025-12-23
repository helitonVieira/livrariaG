package com.heliton.livrariag.services;

import com.heliton.livrariag.dto.AutorResponseDTO;
import com.heliton.livrariag.dto.LivroDetalheResponseDTO;
import com.heliton.livrariag.dto.LivroRequestDTO;
import com.heliton.livrariag.dto.LivroResponseDTO;
import com.heliton.livrariag.model.Autor;
import com.heliton.livrariag.model.Livro;
import com.heliton.livrariag.repositories.AutorRepository;
import com.heliton.livrariag.repositories.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    /*public LivroResponseDTO criarOld(LivroRequestDTO dto) {
        Livro livro = toEntity(dto);
        return toResponse(repository.save(livro));
    }*/

    @Transactional
    public LivroDetalheResponseDTO criar(LivroRequestDTO dto) {

        Livro livro = new Livro(dto);

        List<Autor> autores = autorRepository.findAllById(dto.autoresIds());
        autores.forEach(livro::adicionarAutor);

        Livro salvo = repository.save(livro);

        return new LivroDetalheResponseDTO(
                salvo.getCodl(),
                salvo.getTitulo(),
                salvo.getEditora(),
                salvo.getEdicao(),
                salvo.getAnoPublicacao(),
                salvo.getValor(),
                salvo.getAutores().stream()
                        .map(a -> new AutorResponseDTO(a.getId(), a.getNome()))
                        .collect(Collectors.toSet())
        );
    }


    @Transactional(readOnly = true)
    public List<LivroDetalheResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public LivroDetalheResponseDTO buscarPorId(Integer id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return toResponse(livro);
    }

    @Transactional
    public LivroDetalheResponseDTO atualizar(Integer id, LivroRequestDTO dto) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setTitulo(dto.titulo());
        livro.setEditora(dto.editora());
        livro.setEdicao(dto.edicao());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setValor(dto.valor());

        livro.getAutores().clear();

        if (dto.autoresIds() != null && !dto.autoresIds().isEmpty()) {
            List<Autor> autores = autorRepository.findAllById(dto.autoresIds());

            if (autores.size() != dto.autoresIds().size()) {
                throw new RuntimeException("Um ou mais autores não existem");
            }
            autores.forEach(livro::adicionarAutor);
        }
        Livro atualizado = repository.save(livro);

        return toResponse(atualizado);
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

    private LivroDetalheResponseDTO toResponse(Livro livro) {
        return new LivroDetalheResponseDTO(
                livro.getCodl(),
                livro.getTitulo(),
                livro.getEditora(),
                livro.getEdicao(),
                livro.getAnoPublicacao(),
                livro.getValor(),
                livro.getAutores().stream()
                        .map(a -> new AutorResponseDTO(a.getId(), a.getNome()))
                        .collect(Collectors.toSet())
        );
    }
}
