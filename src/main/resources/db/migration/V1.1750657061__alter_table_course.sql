alter table course alter column title_ru set not null;
alter table course alter column title_kg set not null;
alter table course alter column description_ru set not null;
alter table course alter column description_kg set not null;
alter table course alter column price set not null;
alter table course add column active varchar default 'NEW'