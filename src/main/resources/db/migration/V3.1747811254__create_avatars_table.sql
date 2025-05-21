create table if not exists avatars_workers(
    id bigserial primary key,
    worker_id bigint references workers(id) on delete cascade unique not null
);