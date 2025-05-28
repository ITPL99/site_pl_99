alter table qualifications add column date_create timestamp with time zone default now(),
    add column date_updated timestamp with time zone default now();