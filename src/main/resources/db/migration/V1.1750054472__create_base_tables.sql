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

create table if not exists masters(
    id bigserial primary key,
    full_name varchar not null unique,
    date_birth timestamp with time zone,
    profession varchar not null,
    active varchar not null default 'WORKING',
    date_started timestamp with time zone default now(),
    date_fired timestamp with time zone
);

create table if not exists images_masters(
    id bigserial primary key,
    file_name varchar not null unique,
    master_id bigint references masters(id) unique
);

create table if not exists teachers(
    id bigserial primary key,
    full_name varchar not null unique,
    date_birth timestamp with time zone,
    link_portfolio varchar not null,
    active varchar not null default 'WORKING',
    date_started timestamp with time zone default now(),
    date_fired timestamp with time zone
);

create table if not exists images_teachers(
    id bigserial primary key,
    file_name varchar not null unique,
    teacher_id bigint references teachers(id) unique
);

create table if not exists employees(
    id bigserial primary key,
    full_name varchar not null unique,
    date_birth timestamp with time zone,
    department varchar not null,
    active varchar not null default 'WORKING',
    date_started timestamp with time zone default now(),
    date_fired timestamp with time zone
);

create table if not exists images_employees(
    id bigserial primary key,
    file_name varchar not null unique,
    employee_id bigint references employees(id) unique
);

create table if not exists news(
    id bigserial primary key,
    title_ru varchar not null unique,
    title_kg varchar not null unique,
    subtitle_ru varchar not null unique,
    subtitle_kg varchar not null unique,
    description_ru varchar not null,
    description_kg varchar not null,
    date_created timestamp with time zone default now(),
    active varchar not null default 'NEW'
);

create table if not exists images_news_small(
    id bigserial primary key,
    file_name varchar not null unique,
    news_id bigint references news(id) unique
);

create table if not exists images_news_full(
    id bigserial primary key,
    file_name varchar not null unique,
    news_id bigint references news(id) unique
);

create table if not exists images_news(
    id bigserial primary key,
    file_name varchar not null unique,
    news_id bigint references news(id)
);

create table if not exists videos_news(
    id bigserial primary key,
    file_name varchar not null unique,
    news_id bigint references news(id) unique
);

create table if not exists course(
    id bigserial primary key,
    type varchar not null,
    title_ru varchar not null,
    title_kg varchar not null,
    description_ru varchar not null,
    description_kg varchar not null,
    price integer not null,
    date_started timestamp with time zone not null,
    date_end timestamp with time zone not null
);

create table if not exists images_courses(
    id bigserial primary key,
    file_name varchar not null unique,
    course_id bigint references course(id) unique
);

create table if not exists messages(
    id bigserial primary key,
    full_name varchar not null default 'anonim',
    title varchar not null,
    message varchar not null,
    date_created timestamp with time zone default now(),
    email varchar not null,
    current_status varchar not null
);