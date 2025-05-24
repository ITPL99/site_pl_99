alter table images_news add column file_name varchar not null unique;
alter table videos_news add column file_name varchar not null unique;