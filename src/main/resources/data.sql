INSERT INTO role(name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users(username,email,password)
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

INSERT INTO booking(booking_date, box_name, user_id)
VALUES
(TO_DATE('29/10/2021', 'DD/MM/YYYY'), 'meeting area', 1),
(TO_DATE('22/12/2021', 'DD/MM/YYYY'), 'meeting area', 3),
(TO_DATE('25/11/2021', 'DD/MM/YYYY'), 'cake box', 2),
(TO_DATE('03/07/2021', 'DD/MM/YYYY'), 'work space', 1),
(TO_DATE('08/08/2021', 'DD/MM/YYYY'), 'meeting area', 3);

alter sequence native restart with 4;
