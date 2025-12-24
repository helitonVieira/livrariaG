CREATE OR REPLACE VIEW autor_livro_view AS
SELECT
    a.codau AS autor_id,
    a.nome AS autor_nome,
    l.codl AS livro_id,
    l.titulo AS livro_titulo,
    l.editora AS livro_editora,
    l.edicao AS livro_edicao,
    l.anopublicacao AS livro_ano,
    l.valor AS livro_valor,
    asu.codas AS assunto_id,
    asu.descricao AS assunto_descricao
FROM autor a
         LEFT JOIN livro_autor la ON la.autor_codau = a.codau
         LEFT JOIN livro l ON l.codl = la.livro_codl
         LEFT JOIN livro_assunto las ON las.livro_codl = l.codl
         LEFT JOIN assunto asu ON asu.codas = las.assunto_codas;