CREATE TABLE item (
    codigo INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    livro_codigo INTEGER NOT NULL,
    usuario_codigo INTEGER NOT NULL,
    status VARCHAR(50),
    dataLocacao VARCHAR(50),
    dataDevolucao VARCHAR(50),
    FOREIGN KEY (livro_codigo) REFERENCES livro(codigo),
    FOREIGN KEY (usuario_codigo) REFERENCES usuario(codigo)
)