alter table users add column active boolean default true,
    add column mail varchar unique not null;

alter table news add column date_updated timestamp with time zone default now(),
    add column user_id_create bigint references users(id),
    add column user_id_updated bigint references users(id),
    add column status_deleted boolean default false;

alter table images_news add column date_create timestamp with time zone default now(),
    add column date_updated timestamp with time zone default now(),
    add column user_id_create bigint references users(id),
    add column user_id_updated bigint references users(id),
    add column status_deleted boolean default false;

alter table videos_news add column date_create timestamp with time zone default now(),
    add column date_updated timestamp with time zone default now(),
    add column user_id_create bigint references users(id),
    add column user_id_updated bigint references users(id),
    add column status_deleted boolean default false;

alter table workers rename column name to full_name;
alter table workers rename column age to date;
alter table workers alter column date set data type timestamp with time zone using to_timestamp(date),
    alter column date set default now(),
    add column date_create timestamp with time zone default now(),
    add column date_updated timestamp with time zone default now(),
    add column user_id bigint references users(id) not null,
    add column status_deleted boolean default false;


alter table course add column date_create timestamp with time zone default now(),
    add column date_updated timestamp with time zone default now(),
    add column user_id bigint references users(id) not null,
    add column status_deleted boolean default false;

alter table avatars_workers add column date_upload timestamp with time zone default now(),
    add column date_update timestamp with time zone default now(),
    add column user_id bigint references users(id) not null,
    add column status_deleted boolean default false;


alter table qualifications add column status_deleted boolean default false;