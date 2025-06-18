create table if not exists users(
    id bigserial primary key,
    username varchar not null unique,
    password varchar not null,
    active varchar unique,
    email varchar not null unique
);

create table if not exists roles(
    id bigserial primary key,
    role_name varchar not null unique
);

create table if not exists m2m_users_roles(
    user_id bigint references users(id),
    role_id bigint references roles(id)
);

insert into roles(role_name)
values ('ADMIN');
