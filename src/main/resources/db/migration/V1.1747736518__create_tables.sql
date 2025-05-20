create table if not exists users(
    id bigserial primary key,
    login varchar unique not null,
    password varchar not null,
    email varchar not null,
    date_created timestamp with time zone default now(),
    date_updated timestamp with time zone default now()
);

create table if not exists roles(
    id bigserial primary key,
    role varchar unique not null
);

create table if not exists m2m_users_roles(
    user_id bigint references users(id) on delete cascade,
    role_id bigint references roles(id)
);

create table if not exists workers(
    id bigserial primary key,
    name varchar not null,
    age int not null,
    bio varchar not null,
    profession varchar not null
);

create table if not exists news(
    id bigserial primary key,
    title varchar not null,
    description varchar not null,
    date_created timestamp with time zone default now(),
    user_id bigint references users(id)
);

create table if not exists images_news(
    id bigserial primary key,
    news_id bigint references news(id) on delete cascade
);

create table if not exists videos_news(
    id bigserial primary key,
    news_id bigint references news(id) on delete cascade
);

create table if not exists course(
    id bigserial primary key,
    title varchar not null,
    description varchar not null,
    price bigint not null,
    date_started timestamp not null,
    date_end timestamp not null,
    worker_id bigint references workers(id)
);

create table if not exists qualifications(
    id bigserial primary key,
    title varchar not null,
    description varchar not null
);

create table if not exists m2m_qualifications_workers(
    qualification_id bigint references qualifications(id),
    worker_id bigint references workers(id) unique
);