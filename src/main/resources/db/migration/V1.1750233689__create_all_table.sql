create table if not exists images
(
    id        bigserial primary key,
    file_name varchar unique
);

create table if not exists videos
(
    id        bigserial primary key,
    file_name varchar unique
);

create table if not exists employees
(
    id              bigserial primary key,
    full_name       varchar                              not null,
    date_berth      date default now()                   not null,
    image_id        bigint references images (id) unique not null,
    department_ru      varchar,
    department_kg      varchar,
    active          varchar,
    date_dismissal  date                                 not null,
    date_employment date                                 not null
);

create table if not exists masters
(
    id              bigserial primary key,
    full_name       varchar                              not null,
    date_berth      date                                 not null default now(),
    profession_ru      varchar,
    profession_kg      varchar,
    image_id        bigint references images (id) unique not null,
    active          varchar,
    date_dismissal  date                                 not null,
    date_employment date                                 not null
);

create table if not exists teachers
(
    id              bigserial primary key,
    full_name       varchar                              not null,
    date_berth      date                                 not null default now(),
    link_portfolio      varchar,
    image_id        bigint references images (id) unique not null,
    active          varchar,
    date_dismissal  date                                 not null,
    date_employment date                                 not null
);

create table if not exists news(
    id bigserial primary key,
    title_ru varchar,
    title_kg varchar,
    subtitle_ru varchar,
    subtitle_kg varchar,
    description_ru varchar,
    description_kg varchar,
    date_create timestamp with time zone not null default now(),
    image_small bigint references images(id) unique ,
    image_full bigint references images(id) unique ,
    image_content bigint references images(id),
    video_content bigint references videos(id) unique,
    active varchar
);

create table if not exists course(
    id bigserial primary key,
    type varchar,
    title_ru varchar,
    title_kg varchar,
    description_ru varchar,
    description_kg varchar,
    price double precision,
    date_start date,
    date_end date,
    image_id bigint references images(id)
);


