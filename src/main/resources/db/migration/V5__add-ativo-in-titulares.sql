ALTER TABLE titulares ADD COLUMN ativo boolean;

-- Atualizar todos os registros para ter o valor "true" (ativo)
UPDATE titulares SET ativo = true;