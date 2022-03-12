INSERT INTO localizacao(id_localizacao, nr_latitude, nr_longitude) VALUES (998, 10.0, -22.2);

INSERT INTO restaurante(id_restaurante, ds_nome, id_localizacao) VALUES (998, 'Restaurante do Zé', 998);

INSERT INTO prato(id_prato, ds_nome, ds_descricao, nr_preco, id_restaurante) VALUES (9998, 'Bife acebolado', 'Bife com cebola', 19.99, 998),
                                                                                             (9999, 'Pure de batata', 'Pure de batatas', 15.99, 998);