INSERT INTO users (id, seq_name, username, email, password, role)
VALUES (1, 'user_seq', 'admin', 'admin@mail.com', '$2a$10$adRO6vqLpCm25TDkqniz6eySSEmKBcXvN.ehGP6cyNvljfHfbuG6e', 'ADMIN');
ALTER SEQUENCE user_seq RESTART WITH 2;
