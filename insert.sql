INSERT INTO IMOBI.Cliente (cpf, nome_cliente, telefone, email)
VALUES ('12345678901', 'João Silva', 123456789, 'joao@example.com');


INSERT INTO IMOBI.Imovel (endereco, situacao, cpf, valor_aluguel, valor_compra)
VALUES ('Rua A, 123', 'A', '12345678901', 1000, 200000);

INSERT INTO IMOBI.Funcionario (nome_funcionario, funcao)
VALUES ('Maria Oliveira', 'V');

INSERT INTO IMOBI.Agendamentos (data, cpf, id_funcionario, id_imovel)
VALUES ('2023-11-27', '12345678901', 1, 1);

INSERT INTO IMOBI.Endereco (cpf, rua, numero, bairro, cidade, estado)
VALUES ('12345678901', 'Rua A, 123', '101', 'Centro', 'Cidade A', 'UF');


INSERT INTO IMOBI.Situacao (a_venda, alugado, indisponivel)
VALUES ('1', '0', '0');


INSERT INTO IMOBI.Contrato (cpf, id_imovel, data_contrato, data_expiracao)
VALUES ('12345678901', 1, '2023-11-27', '2024-11-27');
