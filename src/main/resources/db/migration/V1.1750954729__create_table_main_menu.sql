create table if not exists main_menu(
    id bigserial primary key,
    title_ru varchar not null,
    title_kg varchar not null,
    subtitle_ru varchar not null,
    subtitle_kg varchar not null,
    amount_students bigint default 0,
    amount_graduated bigint default 0,
    amount_partners bigint default 0,
    image_id bigint references images(id)
);