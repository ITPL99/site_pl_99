create table mails(
    id bigserial primary key,
    mail_name varchar not null,
    date_created timestamp with time zone default now(),
    date_updated timestamp with time zone default now()
);

create table messages(
    id bigserial primary key,
    full_name varchar default 'anonim',
    title varchar not null,
    message varchar not null,
    date_created timestamp with time zone default now(),
    date_updated timestamp with time zone default now(),
    mail_id bigint references mails(id) on delete set null
);