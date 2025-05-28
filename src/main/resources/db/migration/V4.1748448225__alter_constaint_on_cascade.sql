alter table avatars_workers alter column worker_id drop not null,
    drop constraint avatars_workers_worker_id_fkey cascade,
    add constraint avatars_workers_worker_id_fkey foreign key (worker_id) references workers(id) on delete set null;


alter table images_news alter column news_id drop not null,
    drop constraint images_news_news_id_fkey cascade,
    add constraint images_news_news_id_fkey foreign key (news_id) references news(id) on delete set null;

alter table videos_news alter column news_id drop not null,
    drop constraint videos_news_news_id_fkey cascade,
    add constraint videos_news_news_id_fkey foreign key (news_id) references news(id) on delete set null;