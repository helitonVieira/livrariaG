package com.heliton.livrariag.services;

import com.heliton.livrariag.dto.AutorComLivrosDTO;
import com.heliton.livrariag.dto.AutorRequestDTO;
import com.heliton.livrariag.dto.AutorResponseDTO;
import com.heliton.livrariag.dto.LivroResponseDTO;
import com.heliton.livrariag.model.Autor;
import com.heliton.livrariag.repositories.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AutorResponseDTO criar(AutorRequestDTO dto) {
        Autor autor = new Autor(dto.nome());
        Autor salvo = repository.save(autor);

        return new AutorResponseDTO(
                salvo.getId(),
                salvo.getNome()
        );
    }

    @Transactional(readOnly = true)
    public List<AutorResponseDTO> listar() {
        return repository.findAllByOrderByNomeAsc()
                .stream()
                .map(a -> new AutorResponseDTO(a.getId(), a.getNome()))
                .toList();
    }

    @Transactional(readOnly = true)
    public AutorResponseDTO buscarPorId(Integer id) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        return new AutorResponseDTO(autor.getId(), autor.getNome());
    }

    public AutorResponseDTO atualizar(Integer id, AutorRequestDTO dto) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        autor.setNome(dto.nome());
        return toResponse(repository.save(autor));
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public AutorComLivrosDTO buscarAutorComLivros(Integer id) {
        Autor autor = repository.findWithLivrosById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        var livros = autor.getLivros().stream()
                .map(l -> new LivroResponseDTO(l.getCodl(),
                        l.getTitulo(),
                        l.getEditora(),
                        l.getEdicao(),
                        l.getAnoPublicacao(),
                        l.getValor()))
                .collect(Collectors.toSet());

        return new AutorComLivrosDTO(autor.getId(), autor.getNome(), livros);
    }

    private AutorResponseDTO toResponse(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getNome()
        );
    }
}
