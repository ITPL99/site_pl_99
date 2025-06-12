create table messages(
    id bigserial primary key,
    full_name varchar default 'anonim',
    title varchar not null,
    message varchar not null,
    date_created timestamp with time zone default now(),
    date_updated timestamp with time zone default now(),
    mail varchar not null
);



