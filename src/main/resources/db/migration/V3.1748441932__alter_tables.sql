alter table users add column active boolean default true,
    add column mail varchar not null;

alter table news add column user_id_create bigint references users(id),
    add column user_id_updated bigint references users(id),
    add column status_deleted boolean default false;

alter table images_news add column user_id_create bigint references users(id),
    add column user_id_updated bigint references users(id),
    add column status_deleted boolean default false;

alter table videos_news add column user_id_create bigint references users(id),
    add column user_id_updated bigint references users(id),
    add column status_deleted boolean default false;

alter table workers rename column name to full_name;
alter table workers add column user_id bigint references users(id) not null,
                    add column status_deleted boolean default false;


alter table course add column date_created timestamp with time zone default now(),
    add column user_id bigint references users(id) not null,
    add column status_deleted boolean default false;

alter table avatars_workers add column date_upload timestamp with time zone default now(),
    add column date_update timestamp with time zone,
    add column user_id bigint references users(id) not null,
    add column status_deleted boolean default false;

alter table qualifications add column status_deleted boolean default false;

alter table m2m_qualifications_workers add column status_deleted boolean default false;
