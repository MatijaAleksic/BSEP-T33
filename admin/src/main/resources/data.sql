INSERT INTO certificate_requests(id,common_name,country,email,given_name,organization, organization_unit,surname,uid) VALUES (1,'Name1', 'Serbia', 'user1@gmail.com', 'User1', 'USR', 'USR_U', 'Surname1',1);
INSERT INTO certificate_requests(id,common_name,country,email,given_name,organization, organization_unit,surname,uid) VALUES (2,'Name2', 'Serbia', 'user2@gmail.com', 'User2', 'SUR', 'USR_U1', 'Surname2',2);
INSERT INTO certificate_requests(id,common_name,country,email,given_name,organization, organization_unit,surname,uid) VALUES (3,'Name3', 'Serbia', 'user3@gmail.com', 'User3', 'USR', 'USR_U', 'Surname3',3);
INSERT INTO certificate_requests(id,common_name,country,email,given_name,organization, organization_unit,surname,uid) VALUES (4,'Name4', 'Serbia', 'user4@gmail.com', 'User4', 'SUR', 'USR_U', 'Surname4',4);
INSERT INTO certificate_requests(id,common_name,country,email,given_name,organization, organization_unit,surname,uid) VALUES (5,'Name5', 'Serbia', 'user5@gmail.com', 'User5', 'USR', 'USR_U1', 'Surname5',5);
INSERT INTO certificate_requests(id,common_name,country,email,given_name,organization, organization_unit,surname,uid) VALUES (6,'Name6', 'Serbia', 'user6@gmail.com', 'User6', 'SUR', 'USR_U1', 'Surname6',6);


-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('user', '$2a$10$DOon.uKy0/qYYiAXQ8J13.ZxEZANkRX3k.S.xL7xW/9ooMH9j2tv.', 'Marko', 'Markovic', 'user@example.com', true, '2017-10-01');
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('admin', '$2a$10$30zRXGItx0OOWWk9BT6kLOPrfHR71hl279f/t6ujwe7kdk9qohSuu', 'Matija', 'Aleksic', 'admin@example.com', true, '2017-10-01');
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('user1', '$2a$12$ucA5LW8WQ9SNPYmeL1g6zOA8Gqf6kjqpXqHZaW0ADOb4NxKJ0Ybxu', 'Pavle', 'Pavlovic', 'user1@example.com', true, '2017-10-01');
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('user2', '$2a$12$ferJwaIVBgYBWOaEv4Bo7eIrG/72GnTeTZPA3gcbVevi4tDijqrS.', 'Dusan', 'Dusanovic', 'user2@example.com', true, '2017-10-01');

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');

INSERT INTO PRIVILEGE (name) VALUES ('READ_USER');
INSERT INTO PRIVILEGE (name) VALUES ('READ_USERS');
INSERT INTO PRIVILEGE (name) VALUES ('FIND_USER');
INSERT INTO PRIVILEGE (name) VALUES ('CREATE_ADMIN');
INSERT INTO PRIVILEGE (name) VALUES ('CREATE_USER');
INSERT INTO PRIVILEGE (name) VALUES ('DELETE_USER');

INSERT INTO PRIVILEGE (name) VALUES ('READ_DEVICE');
INSERT INTO PRIVILEGE (name) VALUES ('READ_DEVICES');
INSERT INTO PRIVILEGE (name) VALUES ('READ_USER_DEVICES');


INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- user-u dodeljujemo rolu ADMIN

INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (1, 3);

INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 1);
INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 2);
INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 3);
INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 4);
INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 5);
INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 6);

INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 7);
INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 8);
INSERT INTO ROLE_PRIVILEGE (role_id, privilege_id) VALUES (2, 9);

INSERT INTO DEVICES (name) VALUES ('DOOR');
INSERT INTO DEVICES (name) VALUES ('WINDOW');
INSERT INTO DEVICES (name) VALUES ('CAMERA1');
INSERT INTO DEVICES (name) VALUES ('CAMERA2');

INSERT INTO HOMES (name) VALUES ('HOME1');
INSERT INTO HOMES (name) VALUES ('HOME2');
INSERT INTO HOMES (name) VALUES ('HOME3');

INSERT INTO HOME_USERS (home_id, user_id) VALUES (1, 1);
INSERT INTO HOME_USERS (home_id, user_id) VALUES (1, 2);
INSERT INTO HOME_USERS (home_id, user_id) VALUES (2, 2);
INSERT INTO HOME_USERS (home_id, user_id) VALUES (3, 3);

INSERT INTO HOME_DEVICES (home_id, device_id) VALUES (1, 1);
INSERT INTO HOME_DEVICES (home_id, device_id) VALUES (1, 2);
INSERT INTO HOME_DEVICES (home_id, device_id) VALUES (1, 3);
INSERT INTO HOME_DEVICES (home_id, device_id) VALUES (2, 3);
INSERT INTO HOME_DEVICES (home_id, device_id) VALUES (2, 4);
INSERT INTO HOME_DEVICES (home_id, device_id) VALUES (3, 2);
INSERT INTO HOME_DEVICES (home_id, device_id) VALUES (3, 4);



--INSERT INTO DEVICE_PRIVILEGE (user_id, device_id) VALUES (1,1);
--INSERT INTO DEVICE_PRIVILEGE (user_id, device_id) VALUES (1,2);
--INSERT INTO DEVICE_PRIVILEGE (user_id, device_id) VALUES (1,3);
