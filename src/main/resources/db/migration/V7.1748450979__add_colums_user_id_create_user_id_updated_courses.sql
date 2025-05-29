alter table course add column user_id_create bigint references users(id) not null,
    add column user_id_updated bigint references users(id) not null;