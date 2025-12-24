CREATE TABLE IF NOT EXISTS livro_assunto (
                               livro_codl  INTEGER NOT NULL,
                               assunto_codas INTEGER NOT NULL,

                               CONSTRAINT pk_livro_assunto
                                   PRIMARY KEY (livro_codl , assunto_codas),
                               CONSTRAINT fk_livro_assunto_livro
                                   FOREIGN KEY (livro_codl )
                                       REFERENCES livro (codl )
                                       ON DELETE CASCADE
                                       ON UPDATE CASCADE,

                               CONSTRAINT fk_livro_assunto_assunto
                                   FOREIGN KEY (assunto_codas)
                                       REFERENCES assunto (codas )
                                       ON DELETE CASCADE
                                       ON UPDATE CASCADE

);