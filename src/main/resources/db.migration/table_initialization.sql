drop table if exists team cascade;
drop table if exists player cascade;

create table if not exists team
(
    id         integer      not null,
    name       varchar(255) not null,
    captain_id integer      not null,
    constraint team_pk primary key (id)
);

create table if not exists player
(
    id         integer      not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    position   varchar(100) not null,
    birthday   timestamp    not null,
    team_id    integer      null,
    constraint player_pk primary key (id),
    constraint player_fk foreign key (team_id) references team (id)
);

alter table team add constraint team_fk foreign key (captain_id) references player (id);