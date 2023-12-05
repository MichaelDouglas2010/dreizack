use dreizack;



EXEC ListarTabelas;

CREATE TABLE usuario (
    cpf BIGINT PRIMARY KEY,
    nome_usuario VARCHAR(40),
    login_usuario VARCHAR(40),
    senha VARCHAR(50),
    cargo VARCHAR(50)
);
 
INSERT INTO usuario (cpf, nome_usuario, login_usuario, senha, cargo)
VALUES 
  ( 11122233344, 'Michael Douglas', 'md_10', '123', 'Engenheiro'),
  (22233344455, 'Diego Cruz', 'dc_10', '123', 'Engenheiro');
 
select * from Usuario;
 
INSERT INTO Usuario (cpf, nome_usuario, login_usuario, senha, cargo)
VALUES 
  (33344455566, 'Maria Silva', 'maria_123', '456', 'Analista'),
  (44455566677, 'João Oliveira', 'joao_456', '789', 'Gerente'),
  (55566677788, 'Ana Souza', 'ana_789', 'abc', 'Desenvolvedor');
 
select * from Usuario;
 
DROP TABLE Tarefas;
 
-- Tabela Tarefas
CREATE TABLE tarefa (
    id_tarefa INT PRIMARY KEY IDENTITY(1,1),
    nome_tarefa VARCHAR(40),
    descricao_tarefa VARCHAR(40),
    status_tarefa DECIMAL(10, 2),
	responsavel_tarefa VARCHAR(40),
	data_inicio DATE,
    data_conclusao DATE
);
 
 
INSERT INTO tarefa (nome_tarefa, descricao_tarefa, status_tarefa, responsavel_tarefa, data_inicio, data_conclusao)
VALUES 
  ('Tarefa 1', 'Alvenaria', 50.00, 'Responsável 1', '2023-11-28', '2023-12-05'),
  ('Tarefa 2', 'Fundação', 75.50, 'Responsável 2', '2023-11-30', '2023-12-10'),
  ('Tarefa 3', 'Acabamento', 100.00, 'Responsável 3', '2023-12-02', '2023-12-15');
 
 
  select * from tarefa;
 
  DROP TABLE Projeto;
 
  -- Tabela Projeto
CREATE TABLE projeto (
    id_projeto INT PRIMARY KEY IDENTITY(1,1),
    nome_projeto VARCHAR(40),
    descricao_projeto VARCHAR(100),
    orcamento_projeto DECIMAL(10, 2),
	status_projeto DECIMAL(10, 2),
	data_inicio DATE,
    data_conclusao DATE
);
 
INSERT INTO projeto (nome_projeto, descricao_projeto, orcamento_projeto, status_projeto, data_inicio, data_conclusao)
VALUES 
  ('Projeto A', 'Descrição do Projeto A', 5000.00, 50.00, '2023-11-28', '2023-12-15'),
  ('Projeto B', 'Descrição do Projeto B', 8000.00, 75.50, '2023-11-30', '2023-12-20'),
  ('Projeto C', 'Descrição do Projeto C', 10000.00, 100.00, '2023-12-02', '2023-12-25');
 
  select * from projeto;
 
  drop table tarefa;
 
  CREATE TABLE tarefa (
    id_tarefa INT PRIMARY KEY IDENTITY(1,1),
    nome_tarefa VARCHAR(40),
    descricao_tarefa VARCHAR(40),
    status_tarefa DECIMAL(10, 2),
	responsavel_tarefa VARCHAR(40),
	data_inicio DATE,
    data_conclusao DATE,
    id_projeto INT, -- Chave Estrangeira referenciando Projeto
    FOREIGN KEY (id_projeto) REFERENCES projeto(id_projeto) -- Referência à tabela Projeto
);
 
-- Inserindo tarefas associadas aos projetos
INSERT INTO tarefa (nome_tarefa, descricao_tarefa, status_tarefa, responsavel_tarefa, data_inicio, data_conclusao, id_projeto)
VALUES 
  ('Tarefa 1', 'Descrição da Tarefa 1', 50.00, 'Responsável 1', '2023-11-28', '2023-12-05', 1), -- Associada ao projeto com id_projeto = 1
  ('Tarefa 2', 'Descrição da Tarefa 2', 75.50, 'Responsável 2', '2023-11-30', '2023-12-10', 2), -- Associada ao projeto com id_projeto = 2
  ('Tarefa 3', 'Descrição da Tarefa 3', 100.00, 'Responsável 3', '2023-12-02', '2023-12-15', 3); -- Associada ao projeto com id_projeto = 3
 
  select * from tarefa;
 
  INSERT INTO tarefa (nome_tarefa, descricao_tarefa, status_tarefa, responsavel_tarefa, data_inicio, data_conclusao, id_projeto)
VALUES 
  ('Tarefa 1', 'Descrição da Tarefa 1', 50.00, 'Responsável 1', '2023-11-28', '2023-12-05', 3), -- Associada ao projeto com id_projeto = 3
  ('Tarefa 2', 'Descrição da Tarefa 2', 75.50, 'Responsável 2', '2023-11-30', '2023-12-10', 3), -- Associada ao projeto com id_projeto = 3
  ('Tarefa 3', 'Descrição da Tarefa 3', 100.00, 'Responsável 3', '2023-12-02', '2023-12-15', 2); -- Associada ao projeto com id_projeto = 2
 
    select * from tarefa where id_projeto like 1;
 
	select * from tarefa where id_projeto like 2;
 
	select * from tarefa where id_projeto like 3;
 
 
	CREATE TABLE UsuarioProjeto (
    id_UsuarioProjeto INT PRIMARY KEY IDENTITY(1,1),
    cpf BIGINT,
    id_projeto INT,
    FOREIGN KEY (cpf) REFERENCES Usuario(cpf),
    FOREIGN KEY (id_projeto) REFERENCES Projeto(id_projeto)
);
 
-- Associando usuários aos projetos
INSERT INTO UsuarioProjeto (cpf, id_projeto)
VALUES 
  (33344455566, 1), -- Associando o usuário com CPF = 33344455566  ao projeto com id_projeto = 1
  (33344455566, 2), -- Associando o mesmo usuário a outro projeto com id_projeto = 2
  (44455566677, 2), -- Associando outro usuário (CPF = 44455566677) ao projeto com id_projeto = 2
  (55566677788, 3); -- E assim por diante...
 
  SELECT * FROM UsuarioProjeto;