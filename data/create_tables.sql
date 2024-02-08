
CREATE TABLE item (
    codigo INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    livro_codigo INTEGER,
    usuario_codigo INTEGER,
    status VARCHAR(50),
    dataLocacao VARCHAR(10),
    dataDevolucao VARCHAR(10),
    FOREIGN KEY (livro_codigo) REFERENCES livro(codigo),
    FOREIGN KEY (usuario_codigo) REFERENCES usuario(codigo)
)