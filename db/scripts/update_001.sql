create table if not exists rabbit (
    id serial primary key,
    created_date timestamp
);

create table if not exists post (
    id serial primary key,
    name text,
    link text unique,
    text text,
    created_date timestamp
);