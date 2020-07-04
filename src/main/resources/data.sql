insert into currency_entity (id, code) values (1, 'USD');
insert into currency_entity (id, code) values (2, 'EUR');

insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp) values (1, 1.2, 2, 1, current_timestamp);
insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp) values (1, 1.3, 2, 2, current_timestamp);
insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp) values (1, 1.4, 2, 3, current_timestamp);
insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp) values (1, 1.5, 2, 4, current_timestamp);
