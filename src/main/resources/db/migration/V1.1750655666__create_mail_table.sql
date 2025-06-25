create table mails(
    id bigserial primary key,
    title varchar not null,
    content varchar not null,
    email_to varchar not null,
    status varchar default 'CONSIDER',
    date_created DATE default now()
);