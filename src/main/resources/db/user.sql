create sequence users_sequence
    start with 1000
    increment by 1
    cache 50;

create table users (
    id bigint not null default nextval('users_sequence'),
    name varchar(40) not null,
    lastname varchar(40) not null,
    login varchar(40) not null,
    password varchar(255) not null,
    description text,
    photo_url varchar(255),
    ---------------------------------------
    constraint user_id_pk primary key (id),
    constraint user_login_uq unique (login)
);

