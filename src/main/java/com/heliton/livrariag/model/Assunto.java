package com.heliton.livrariag.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assunto")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codas")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "descricao", nullable = false, length = 20)
    private String descricao;


    @ManyToMany(mappedBy = "assuntos")
    private Set<Livro> livros = new HashSet<>();


    public Assunto(String descricao) {
        this.descricao = descricao;
    }

}

