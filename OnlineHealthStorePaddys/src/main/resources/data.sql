INSERT INTO role (id, role) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO user (user_id, address, email, password, payment, username) VALUES 
(1, 'Leitrim','admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Visa', 'adminName'),
(3, 'Sligo','user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'Credit', 'userName');


insert into user_roles(users_user_id, roles_id) values
(1,1),
(1,2),
(3,2);
