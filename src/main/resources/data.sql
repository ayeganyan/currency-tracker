insert into currency_entity (id, code) values (1, 'USD');
insert into currency_entity (id, code) values (2, 'EUR');

-- Bcrypted password: Admin@123
insert into credential_entity(id, username, password) values (1, 'admin', '$2y$11$42lyG/fvVQLJKBGMwlWe1eUDhFtrU6d00aW1W.V1bUcQuCt8D/uw6');
insert into auth_group_entity(id, auth_group, user_fk) values (1, 'ROLE_ADMIN', 1);
insert into auth_group_entity(id, auth_group, user_fk) values (2, 'ROLE_USER', 1);
