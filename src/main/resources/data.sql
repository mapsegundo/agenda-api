-- Inserir usuários (senha: 123456)
INSERT INTO usuario (nome, email, senha) VALUES 
('Admin', 'admin@example.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.'),
('Usuário Teste', 'usuario@example.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');

-- Inserir contatos para o usuário Admin
INSERT INTO contato (nome, email, telefone, usuario_id) VALUES 
('João Silva', 'joao@example.com', '(11) 98765-4321', 1),
('Maria Oliveira', 'maria@example.com', '(11) 91234-5678', 1),
('Pedro Santos', 'pedro@example.com', '(11) 99876-5432', 1);

-- Inserir contatos para o usuário Teste
INSERT INTO contato (nome, email, telefone, usuario_id) VALUES
('Ana Souza', 'ana@example.com', '(21) 98765-4321', 2),
('Carlos Ferreira', 'carlos@example.com', '(21) 91234-5678', 2);