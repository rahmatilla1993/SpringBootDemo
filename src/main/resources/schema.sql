create table store(
    id serial primary key,
    name varchar not null,
    description varchar
);

create table item(
    id serial primary key ,
    name varchar not null,
    description varchar,
    price double precision,
    path varchar,
    store_id int references store(id)
);

create table upload(
    id serial primary key ,
    original_name varchar,
    generated_name varchar,
    mime_type varchar,
    size bigint,
    item_id int references item(id)
);