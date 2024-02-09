INSERT INTO livro (titulo, genero, paginas, sinopse) VALUES
('Dom Casmurro', 'Romance', 256, 'Dom Casmurro é um romance escrito por Machado de Assis.'),
('A Metamorfose', 'Ficção Científica', 150, 'A Metamorfose é uma novela escrita por Franz Kafka.'),
('O Pequeno Príncipe', 'Fantasia', 96, 'O Pequeno Príncipe é uma obra do escritor francês Antoine de Saint-Exupéry.'),
('1984', 'Distopia', 328, '1984 é um romance distópico escrito por George Orwell.'),
('Orgulho e Preconceito', 'Romance', 279, 'Orgulho e Preconceito é uma obra da escritora britânica Jane Austen.');

INSERT INTO autor (nome, nacionalidade) VALUES
('Machado de Assis', 'Brasileira'),
('Franz Kafka', 'Austríaca'),
('Antoine de Saint-Exupéry', 'Francesa'),
('George Orwell', 'Britânica'),
('Jane Austen', 'Britânica');

INSERT INTO livro_autor (livro_codigo, autor_codigo) VALUES
(1, 1), -- Dom Casmurro por Machado de Assis
(2, 2), -- A Metamorfose por Franz Kafka
(3, 3), -- O Pequeno Príncipe por Antoine de Saint-Exupéry
(4, 4), -- 1984 por George Orwell
(5, 5); -- Orgulho e Preconceito por Jane Austen

INSERT INTO usuario (nome, cpf, email, endereco, dataNascimento) VALUES
('João Silva', '123.456.789-00', 'joao@example.com', 'Rua A, 123', '1990-01-01'),
('Maria Santos', '987.654.321-00', 'maria@example.com', 'Rua B, 456', '1985-05-15'),
('Pedro Souza', '456.789.123-00', 'pedro@example.com', 'Av. C, 789', '1995-10-20');


INSERT INTO item (livro_codigo, usuario_codigo, status, dataLocacao, dataDevolucao) VALUES
(1, 1, 'Emprestado', '2023-10-10', '2023-10-17'), 
(2, 2, 'Emprestado', '2023-09-20', '2023-09-27'),
(3, 3, 'Disponível', NULL, NULL); 
