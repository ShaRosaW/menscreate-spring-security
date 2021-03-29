INSERT INTO role(name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO booking_type(name)
VALUES
('SPACE'),
('BOX');

INSERT INTO day_part(name)
VALUES
('MORNING'),
('AFTERNOON'),
('WHOLE_DAY');

INSERT INTO app_user(username,email,password)
VALUES
( 'sharonr', 'sharon@gmail.com', '$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq' ),
( 'user','user@mail.nl','$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq' ),
( 'admin','admin@mail.nl','$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq' );
INSERT INTO user_role(user_id,role_id)
VALUES ( 1,1 ),( 2,1 ),( 3,2 );

INSERT INTO user_profile(first_name,last_name,phone_number, user_id)
VALUES
( 'Sharon', 'Wijnberg', '0612345678', 1),
( 'User1', 'Onelast', '0612345677', 2),
( 'Admin', 'Adminlast', '0612345676', 3);
-- INSERT INTO app_user(id, user_id)
-- VALUES
-- (1,1), (2,2), (3,3);

-- INSERT INTO app_user(file)
-- VALUES
-- ( )

INSERT INTO booking(booking_date, day_part, bookingtype_id, user_id)
VALUES
(TO_DATE('29/03/2021', 'DD/MM/YYYY'), 'MORNING', '2', 1),
(TO_DATE('29/03/2021', 'DD/MM/YYYY'), 'MORNING', '1', 1),
(TO_DATE('29/03/2021', 'DD/MM/YYYY'), 'AFTERNOON', '2', 3);
-- INSERT INTO bookings(booking_id, user_id)
-- VALUES
-- (1,1), (2,1), (3,3);

-- INSERT INTO user(user_id)
--
-- INSERT INTO booking(booking_date, day_part, booking_type)
--
--
-- INSERT INTO box(ingredients, allergy_info, amount_people, extra_info, boxtype_id, bookingtype)
--
--
-- INSERT INTO booking_date(booking_date, day_part, bookingtype_id)
-- VALUES
-- (TO_DATE('29-03-2021', 'DD/MM/YYYY'))