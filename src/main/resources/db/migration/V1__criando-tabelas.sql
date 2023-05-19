CREATE TABLE contas (
  id SERIAL PRIMARY KEY NOT NULL,
  agencia VARCHAR(10) NOT NULL,
  numero VARCHAR(10) NOT NULL,
  saldo DECIMAL(10, 2)
);

CREATE TABLE titulares (
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  conta_id INT,
  FOREIGN KEY (conta_id) REFERENCES contas(id)
);

CREATE TABLE transferencias (
  id SERIAL PRIMARY KEY NOT NULL,
  conta_origem_id INT NOT NULL,
  conta_destino_id INT NOT NULL,
  valor DECIMAL(10, 2) NOT NULL,
  data_transferencia DATE NOT NULL,
  tipo VARCHAR(100) NOT NULL,
  FOREIGN KEY (conta_origem_id) REFERENCES contas(id),
  FOREIGN KEY (conta_destino_id) REFERENCES contas(id)
);

CREATE TABLE extrato (
  id SERIAL PRIMARY KEY NOT NULL,
  conta_id INT NOT NULL,
  data_movimentacao DATE NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  valor DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (conta_id) REFERENCES contas(id)
);