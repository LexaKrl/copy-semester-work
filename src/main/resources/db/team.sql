create sequence team_sequence
    start with 1000
    increment by 1
    cache 50;

create table team (
    id bigint not null default nextval('team_sequence'),
    name varchar(40) not null,
    owner_id bigint not null,
    password varchar(255) not null,
    -------------------------------------------
    constraint team_id_name_pk primary key (id, name),
    constraint team_owner_id foreign key (owner_id) references users(id) on delete cascade
);

create table team_user (
    user_id bigint not null,
    team_id bigint not null,
    ------------------------------
    constraint user_id_fk foreign key (user_id) references users(id) on delete cascade,
    constraint team_id_fk foreign key (team_id) references team(id) on delete cascade,
    constraint team_user_pk primary key (user_id, team_id)
);
