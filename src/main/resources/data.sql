insert into currency_entity (id, code) values (1, 'USD');
insert into currency_entity (id, code) values (2, 'EUR');

insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp) values (1, 1.2, 2, 1, current_timestamp);
insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp) values (1, 1.3, 2, 2, current_timestamp);
insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp) values (1, 1.4, 2, 3, current_timestamp);
insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp) values (1, 1.5, 2, 4, current_timestamp);

-- Bcrypted password: Admin@123
insert into credential_entity(id, username, password) values (1, 'admin', '$2y$11$42lyG/fvVQLJKBGMwlWe1eUDhFtrU6d00aW1W.V1bUcQuCt8D/uw6');
insert into auth_group_entity(id, auth_group, user_fk) values (1, 'ROLE_ADMIN', 1);
insert into auth_group_entity(id, auth_group, user_fk) values (2, 'ROLE_USER', 1);
