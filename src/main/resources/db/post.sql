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
)
