create table post(
    id serial primary key ,
    title varchar,
    body varchar,
    user_id int
);

create table auth_user(
    id serial primary key,
    username varchar unique,
    password varchar,
    is_activate bool
);