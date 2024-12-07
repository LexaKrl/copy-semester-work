create sequence project_sequence
    start with 1000
    increment by 1
    cache 50;

create table project (
    id bigint not null default nextval('project_sequence'),
    name varchar(100) not null,
    owner_id bigint not null,
    team_id bigint not null,
    employee_num integer not null,
    description text,
    ---------------------------------------
    constraint project_id_pk primary key (id),
    constraint project_name_uq unique (name),
    constraint project_team_id_fk foreign key (team_id) references team(id) on delete cascade,
    constraint project_owner_id_fk foreign key (owner_id) references users(id) on delete set null
);
