INSERT INTO tb_category (name) VALUES ('Compras');
INSERT INTO tb_category (name) VALUES ('Vendas');
INSERT INTO tb_category (name) VALUES ('Estudos');

INSERT INTO tb_task (title, description, category_id) VALUES ('Estudar SpringBoot', 'Estudar SpringBoot todos os dias pelo menos 30min', 3);
INSERT INTO tb_task (title, description, category_id) VALUES ('Criar projetos SpringBoot', 'Criar e aplicar conhecimentos em projetos SpringBoot sempre que tiver conteúdo novo', 3);
INSERT INTO tb_task (title, description, category_id) VALUES ('Estudar Spring Security', 'Aprender JWT e OAuth2', 3);
INSERT INTO tb_task (title, description, category_id) VALUES ('Venda de Carro', 'Vender o carro Onix 2018 1.0 Flex', 2);
INSERT INTO tb_task (title, description, category_id) VALUES ('Comprar Casa', 'Comprar uma casa grande e bem dividida', 1);

INSERT INTO tb_user (name, email, password) VALUES ('Fabricio Bezerra', 'fabricio@email.com', '$2a$10$0PQsSZrifPjgh1sYoQMNsO8Fg7b65iAi/Uo1696gIpAXZsa7osq7O');
INSERT INTO tb_user (name, email, password) VALUES ('Fernando Bezerra', 'fernando@email.com', '$2a$10$0PQsSZrifPjgh1sYoQMNsO8Fg7b65iAi/Uo1696gIpAXZsa7osq7O');

INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);