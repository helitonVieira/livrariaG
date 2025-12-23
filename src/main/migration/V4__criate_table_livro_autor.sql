CREATE TABLE IF NOT EXISTS livro_autor (
                                           livro_codl   INTEGER NOT NULL,
                                           autor_codau  INTEGER NOT NULL,

                                           CONSTRAINT pk_livro_autor
                                           PRIMARY KEY (livro_codl, autor_codau),

    CONSTRAINT fk_livro_autor_livro
    FOREIGN KEY (livro_codl)
    REFERENCES livro(codl)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    CONSTRAINT fk_livro_autor_autor
    FOREIGN KEY (autor_codau)
    REFERENCES autor(codau)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );
