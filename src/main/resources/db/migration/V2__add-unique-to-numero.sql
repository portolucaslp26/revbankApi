-- Adiciona a restrição UNIQUE ao campo "numero" na tabela "contas"
ALTER TABLE contas
ADD CONSTRAINT uk_contas_numero UNIQUE (numero);