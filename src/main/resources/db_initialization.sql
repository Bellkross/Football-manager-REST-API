drop function if exists get_player_role_by_number(number bigint);
create function get_player_role_by_number(number bigint)
    returns text as
$body$
declare
    roles text[] = '{"GOALKEEPER", "DEFENDER", "MIDFIELDER", "FORWARD"}';
begin
    number = number % 11;
    if number = 0 then
        return (select roles[1]);
    elseif
                number > 0 and number <= 2 then
        return (select roles[2]);
    elseif number > 2 and number <= 6 then
        return (select roles[3]);
    elseif number > 6 and number <= 11 then
        return (select roles[4]);
    end if;
end;
$body$
    language 'plpgsql' VOLATILE
    COST 100;

truncate table team cascade;
truncate table player cascade;
do $$
    declare
        free_team_id bigint := 0;
        team_name_template text := 'teamName%s';

        free_player_id bigint := 0;
        player_first_name_template text := 'firstName%s';
        player_last_name_template text := 'lastName%s';
        current_birthday timestamp := to_timestamp(0);

        current_first_name text = '';
        current_last_name text = '';
        current_position text = '';
        current_team_name text = '';
        teams_count int := 10;
        players_per_team int := 11;
        current_captain_id int := 0;
        counter int := 0;
    begin
        while free_team_id < teams_count loop
            counter = 0;
            while counter < players_per_team loop
                current_first_name = (select format(player_first_name_template, free_player_id));
                current_last_name = (select format(player_last_name_template, free_player_id));
                current_position = (select get_player_role_by_number(free_player_id));

                insert into player (id, firstname, lastname, position, birthday, team_id)
                values (free_player_id, current_first_name, current_last_name, current_position, current_birthday, null);

                counter = counter + 1;
                free_player_id = free_player_id + 1;
            end loop;

            free_player_id = free_player_id - 11;
            current_captain_id = free_player_id;
            current_team_name = (select format(team_name_template, free_team_id));
            insert into team (id, name, captain_id)
            values (free_team_id, current_team_name, current_captain_id);

            counter = 0;
            while counter < players_per_team loop
                update player set team_id = free_team_id where id = free_player_id;
                counter = counter + 1;
                free_player_id = free_player_id + 1;
            end loop;

            free_team_id = free_team_id + 1;
        end loop;
        create sequence player_id_sequence start with 110;
        create sequence team_id_sequence start with 110;
    end $$;

drop function if exists get_player_role_by_number(number bigint);