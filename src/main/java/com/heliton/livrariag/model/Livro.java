package com.heliton.livrariag.model;

import com.heliton.livrariag.dto.LivroRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@FieldNameConstants
@NoArgsConstructor
@Getter
@Setter
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codl")
    private Integer codl;

    @Column(nullable = false, length = 40)
    private String titulo;

    @Column(length = 40)
    private String editora;

    private Integer edicao;

    @Column(name = "anopublicacao")
    private Integer anoPublicacao;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_codl"),
            inverseJoinColumns = @JoinColumn(name = "autor_codau")
    )
    private Set<Autor> autores = new HashSet<>();

    public Livro(LivroRequestDTO dto){
        this.titulo = dto.titulo();
        this.editora = dto.editora();
        this.edicao = dto.edicao();
        this.anoPublicacao = dto.anoPublicacao();
        this.valor = dto.valor();
    }

    public void adicionarAutor(Autor autor) {
        this.autores.add(autor);
        autor.getLivros().add(this);
    }

    public void removerAutor(Autor autor) {
        this.autores.remove(autor);
        autor.getLivros().remove(this);
    }
}

