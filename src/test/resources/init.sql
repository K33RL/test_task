create table users
(
    id            bigint primary key unique,
    name          varchar(500),
    date_of_birth date,
    password      varchar
);

create table accounts
(
    id      bigint primary key unique,
    user_id bigint unique references users (id),
    balance decimal not null
);

create table email_data
(
    id      bigint primary key unique,
    user_id bigint unique references users (id),
    email   varchar(100)
);

create table phone_data
(
    id      bigint primary key unique,
    user_id bigint unique references users (id),
    phone   varchar(100)
);

alter table users
    add constraint password_length_min check (length(password)>=8);
alter table users
    add constraint password_length_max check (length(password)<=500);

insert into users (id, name, password) VALUES
    (1,'Bob', 'password');