insert into room (id,room_number) values (1,1);
insert into room (id,room_number) values (2,2);
insert into room (id,room_number) values (3,3);

insert into presentation (id, presentation_name, presenter_id)
    values (1, 'My Presentation', 0);
insert into schedule (id, date, start_time, presentation_id, room_id)
    values (1, '2021-05-05', '11:00:00', 1, 1);

