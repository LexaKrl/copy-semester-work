create sequence team_sequence
    start with 1000
    increment by 1
    cache 50;

create table team (
    id bigint not null default nextval('team_sequence'),
    name varchar(40) not null,
    owner_id bigint not null,
    -------------------------------------------
    constraint team_id_pk primary key (id),
    constraint team_name_uq unique (name),
    constraint team_owner_id foreign key (owner_id) references users(id) on delete set null
);
