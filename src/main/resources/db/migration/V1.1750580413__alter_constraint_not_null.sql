alter table employees alter column date_dismissal drop not null;
alter table employees alter column date_employment drop not null;

alter table masters alter column date_dismissal drop not null;
alter table masters alter column date_employment drop not null;

alter table teachers alter column date_dismissal drop not null;
alter table teachers alter column date_employment drop not null;