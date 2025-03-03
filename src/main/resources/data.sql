-- Inserir usuários (senha: 123456)
INSERT INTO usuario (id, nome, email, senha) VALUES 
(1, 'Admin', 'admin@example.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.'),
(2, 'Usuário Teste', 'usuario@example.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');

-- Inserir contatos para o usuário Admin
INSERT INTO contato (id, nome, email, telefone, usuario_id) VALUES 
(1, 'João Silva', 'joao@example.com', '(11) 98765-4321', 1),
(2, 'Maria Oliveira', 'maria@example.com', '(11) 91234-5678', 1),
(3, 'Pedro Santos', 'pedro@example.com', '(11) 99876-5432', 1);

-- Inserir contatos para o usuário Teste
INSERT INTO contato (id, nome, email, telefone, usuario_id) VALUES 
(4, 'Ana Souza', 'ana@example.com', '(21) 98765-4321', 2),
(5, 'Carlos Ferreira', 'carlos@example.com', '(21) 91234-5678', 2);