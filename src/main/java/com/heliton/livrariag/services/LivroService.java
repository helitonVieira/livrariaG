package com.heliton.livrariag.services;

import com.heliton.livrariag.dto.AssuntoResponseDTO;
import com.heliton.livrariag.dto.AutorResponseDTO;
import com.heliton.livrariag.dto.LivroDetalheResponseDTO;
import com.heliton.livrariag.dto.LivroRequestDTO;
import com.heliton.livrariag.model.Assunto;
import com.heliton.livrariag.model.Autor;
import com.heliton.livrariag.model.Livro;
import com.heliton.livrariag.repositories.AssuntoRepository;
import com.heliton.livrariag.repositories.AutorRepository;
import com.heliton.livrariag.repositories.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final AutorRepository autorRepository;
    private final AssuntoRepository assuntoRepository;

    public LivroService(LivroRepository repository, AutorRepository autorRepository, AssuntoRepository assuntoRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
        this.assuntoRepository = assuntoRepository;
    }

    /*public LivroResponseDTO criarOld(LivroRequestDTO dto) {
        Livro livro = toEntity(dto);
        return toResponse(repository.save(livro));
    }*/

    @Transactional
    public LivroDetalheResponseDTO criar(LivroRequestDTO dto) {

        Livro livro = new Livro(dto);

        if (dto.autoresIds() != null && !dto.autoresIds().isEmpty()) {
            List<Autor> autores = autorRepository.findAllById(dto.autoresIds());
            if (autores.size() != dto.autoresIds().size()) {
                throw new RuntimeException("Um ou mais autores não existem");
            }
            autores.forEach(livro::adicionarAutor);
        }

        if (dto.assuntosIds() != null && !dto.assuntosIds().isEmpty()) {
            List<Assunto> assuntos = assuntoRepository.findAllById(dto.assuntosIds());
            if (assuntos.size() != dto.assuntosIds().size()) {
                throw new RuntimeException("Um ou mais assuntos não existem");
            }
            assuntos.forEach(livro::adicionarAssunto);
        }

        Livro salvo = repository.save(livro);
        return mapToDetalheResponse(salvo);
    }

    private LivroDetalheResponseDTO mapToDetalheResponse(Livro livro) {
        Set<AutorResponseDTO> autores = livro.getAutores().stream()
                .map(a -> new AutorResponseDTO(a.getId(), a.getNome()))
                .collect(Collectors.toSet());

        Set<AssuntoResponseDTO> assuntos = livro.getAssuntos().stream()
                .map(a -> new AssuntoResponseDTO(a.getId(), a.getDescricao()))
                .collect(Collectors.toSet());

        return new LivroDetalheResponseDTO(
                livro.getCodl(),
                livro.getTitulo(),
                livro.getEditora(),
                livro.getEdicao(),
                livro.getAnoPublicacao(),
                livro.getValor(),
                autores,
                assuntos
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

        //autor
        livro.getAutores().clear();
        if (dto.autoresIds() != null && !dto.autoresIds().isEmpty()) {
            List<Autor> autores = autorRepository.findAllById(dto.autoresIds());

            if (autores.size() != dto.autoresIds().size()) {
                throw new RuntimeException("Um ou mais autores não existem");
            }
            autores.forEach(livro::adicionarAutor);
        }

        //assunto
        livro.getAssuntos().clear();
        if (dto.assuntosIds() != null && !dto.assuntosIds().isEmpty()) {
            List<Assunto> assuntos = assuntoRepository.findAllById(dto.assuntosIds());

            if (assuntos.size() != dto.assuntosIds().size()) {
                throw new RuntimeException("Um ou mais autores não existem");
            }
            assuntos.forEach(livro::adicionarAssunto);
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
                        .collect(Collectors.toSet()),
                livro.getAssuntos().stream()
                        .map(a -> new AssuntoResponseDTO(a.getId(), a.getDescricao()))
                        .collect(Collectors.toSet())

        );
    }
}
