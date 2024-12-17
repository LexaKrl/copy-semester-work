create sequence post_sequence
    start with 1000
    increment by 1
    cache 50;

create table post (
                      id bigint not null default nextval('post_sequence'),
                      name varchar(40) not null,
                      project_id bigint,
                      author_id bigint,
                      assignee_id bigint,
                      description text,
    ---------------------------------------
                      constraint post_id_pk primary key (id),
                      constraint post_name_uq unique (name),
                      constraint post_project_id_fk foreign key (project_id) references project(id) on delete cascade,
                      constraint post_author_id_fk foreign key (author_id) references users(id) on delete set null,
                      constraint post_assignee_id_fk foreign key (assignee_id) references users(id) on delete set null
);

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

