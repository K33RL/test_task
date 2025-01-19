create table "user"
(
    id            bigint primary key unique,
    name          varchar(500),
    date_of_birth date,
    password      varchar
);

create table account
(
    id      bigint primary key unique,
    user_id bigint unique references "user" (id),
    balance decimal not null
);

create table email_data
(
    id      bigint primary key unique,
    user_id bigint unique references "user" (id),
    email   varchar(100)
);

create table phone_data
(
    id      bigint primary key unique,
    user_id bigint unique references "user" (id),
    email   varchar(100)
);

alter table "user"
    add constraint password_length_min check (length(password)>=8);
alter table "user"
    add constraint password_length_max check (length(password)<=500);