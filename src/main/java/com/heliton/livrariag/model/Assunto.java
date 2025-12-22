package com.heliton.livrariag.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "descricao", nullable = false, length = 40)
    private String descricao;

    public Assunto(String descricao) {
        this.descricao = descricao;
    }


}

