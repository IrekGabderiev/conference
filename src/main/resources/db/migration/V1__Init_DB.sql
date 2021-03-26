create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );

create table presentation (
    id integer not null,
    presentation_name varchar(255),
    presenter_id integer,
    primary key (id)) engine=InnoDB;

create table room (
    id integer not null,
    room_number integer,
    primary key (id)) engine=InnoDB;

create table schedule (
    id integer not null,
    date date,
    start_time time,
    presentation_id integer,
    room_id integer,
    primary key (id)) engine=InnoDB;

create table user (
    id integer not null,
    enabled bit,
    login varchar(255) not null,
    password varchar(255) not null,
    role varchar(255),
    primary key (id)) engine=InnoDB;

alter table user
    add constraint UK_ew1hvam8uwaknuaellwhqchhb unique (login);
alter table presentation
    add constraint FKhtiiw7dke6siwrw2gr4uypf2i foreign key (presenter_id) references user (id);
alter table schedule
    add constraint FK8g12spt0h7ux2vwmckw8u58dl foreign key (presentation_id) references presentation (id);
alter table schedule
    add constraint FKh2hdhbss2x31ns719hka6enma foreign key (room_id) references room (id);