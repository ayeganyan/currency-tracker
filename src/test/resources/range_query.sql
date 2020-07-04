insert into currency_entity (id, code) values (1, 'USD');
insert into currency_entity (id, code) values (2, 'EUR');

insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp)
values (1, 1.2, 2, 1, '2020-07-04 14:10:58.033');

insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp)
values (1, 1.3, 2, 2, '2020-07-04 14:20:58.033');

insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp)
values (1, 1.4, 2, 3, '2020-07-04 14:30:58.033');

insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp)
values (1, 1.5, 2, 4, '2020-07-04 14:40:58.033');

insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp)
values (1, 1.5, 2, 5, '2020-07-04 14:50:58.033');

insert into currency_rate_entity (from_currency_fk, rate, to_currency_fk, id, timestamp)
values (1, 1.5, 2, 6, '2020-07-04 14:55:58.033');