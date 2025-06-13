create table status(
    id bigserial primary key,
    status_name varchar not null
);

insert into status(status_name)
values ('COMPLETE'),
       ('REJECT'),
       ('CONSIDER');

alter table messages add column current_status bigint references status(id) default 3;