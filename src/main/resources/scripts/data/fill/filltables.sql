-- insert test data into table users
INSERT INTO users (id, login, password, firstname, middlename, lastname, "enabled") VALUES
  (nextval('users_id_seq'), 'l1', 'p1', 'fn1', 'mn1', 'ln1', TRUE),
  (nextval('users_id_seq'), 'l2', 'p2', 'fn2', 'mn2', 'ln2', TRUE ),
  (nextval('users_id_seq'), 'l3', 'p3', 'fn3', 'mn3', 'ln3', TRUE ),
  (nextval('users_id_seq'), 'l4', 'p4', 'fn4', 'mn4', 'ln4', TRUE),
  (nextval('users_id_seq'), 'l5', 'p5', 'fn5', 'mn5', 'ln5', TRUE),
  (nextval('users_id_seq'), 'l6', 'p6', 'fn6', 'mn6', 'ln6', TRUE),
  (nextval('users_id_seq'), 'l7', 'p7', 'fn7', 'mn7', 'ln7', TRUE),
  (nextval('users_id_seq'), 'l8', 'p8', 'fn8', 'mn8', 'ln8', TRUE),
  (nextval('users_id_seq'), 'l9', 'p9', 'fn9', 'mn9', 'ln9', TRUE),
  (nextval('users_id_seq'), 'l10', 'p10', 'fn10', 'mn10', 'ln10', TRUE);

-- insert test data into table user_roles
INSERT INTO user_roles (id, user_id, role) VALUES
  (nextval('users_id_seq'), 1, 'ROLE_USER'),
  (nextval('users_id_seq'), 2, 'ROLE_USER'),
  (nextval('users_id_seq'), 3, 'ROLE_USER'),
  (nextval('users_id_seq'), 4, 'ROLE_USER'),
  (nextval('users_id_seq'), 5, 'ROLE_USER'),
  (nextval('users_id_seq'), 6, 'ROLE_USER'),
  (nextval('users_id_seq'), 7, 'ROLE_USER'),
  (nextval('users_id_seq'), 8, 'ROLE_USER'),
  (nextval('users_id_seq'), 9, 'ROLE_USER'),
  (nextval('users_id_seq'), 10, 'ROLE_USER');

-- insert test data into table groups
INSERT INTO groups (id, name, notes, owner) VALUES
  (nextval('groups_id_seq'), 'g1', 'g1', 1),
  (nextval('groups_id_seq'), 'g2', 'g2', 2),
  (nextval('groups_id_seq'), 'g3', 'g3', 3),
  (nextval('groups_id_seq'), 'g4', 'g4', 4),
  (nextval('groups_id_seq'), 'g5', 'g5', 5),
  (nextval('groups_id_seq'), 'g6', 'g6', 6),
  (nextval('groups_id_seq'), 'g7', 'g7', 7),
  (nextval('groups_id_seq'), 'g8', 'g8', 8),
  (nextval('groups_id_seq'), 'g9', 'g9', 9),
  (nextval('groups_id_seq'), 'g10', 'g10', 10),
  (nextval('groups_id_seq'), 'g1', 'g1', 2),
  (nextval('groups_id_seq'), 'g1', 'g1', 3),
  (nextval('groups_id_seq'), 'g3', 'g3', 4),
  (nextval('groups_id_seq'), 'g3', 'g3', 7),
  (nextval('groups_id_seq'), 'g7', 'g7', 1),
  (nextval('groups_id_seq'), 'g7', 'g7', 10),
  (nextval('groups_id_seq'), 'g10', 'g10', 1),
  (nextval('groups_id_seq'), 'g10', 'g10', 2),
  (nextval('groups_id_seq'), 'g10', 'g10', 3),
  (nextval('groups_id_seq'), 'g10', 'g10', 4),
  (nextval('groups_id_seq'), 'g10', 'g10', 5),
  (nextval('groups_id_seq'), 'g10', 'g10', 6),
  (nextval('groups_id_seq'), 'g10', 'g10', 7),
  (nextval('groups_id_seq'), 'g10', 'g10', 9),
  (nextval('groups_id_seq'), 'g10', 'g10', 10);

-- insert test data into table contacts
INSERT INTO contacts (id, firstname, middlename, lastname, firstphonenumber,
                      firstphonenumbertype, secondphonenumber, secondphonenumbertype,
                      email, notes, owner) VALUES
  (nextval('contacts_id_seq'), 'fn1', 'mn1', 'ln1', '1', 'MOBILE', '1', 'MOBILE', 'e1', 'n1', 1),
  (nextval('contacts_id_seq'), 'fn2', 'mn2', 'ln2', '2', 'MOBILE', '2', 'MOBILE', 'e2', 'n2', 2),
  (nextval('contacts_id_seq'), 'fn3', 'mn3', 'ln3', '3', 'MOBILE', '3', 'MOBILE', 'e3', 'n3', 3),
  (nextval('contacts_id_seq'), 'fn4', 'mn4', 'ln4', '4', 'MOBILE', '4', 'MOBILE', 'e4', 'n4', 4),
  (nextval('contacts_id_seq'), 'fn5', 'mn5', 'ln5', '5', 'MOBILE', '5', 'MOBILE', 'e5', 'n5', 5),
  (nextval('contacts_id_seq'), 'fn6', 'mn6', 'ln6', '6', 'MOBILE', '6', 'MOBILE', 'e6', 'n6', 6),
  (nextval('contacts_id_seq'), 'fn7', 'mn7', 'ln7', '7', 'MOBILE', '7', 'MOBILE', 'e7', 'n7', 7),
  (nextval('contacts_id_seq'), 'fn8', 'mn8', 'ln8', '8', 'MOBILE', '8', 'MOBILE', 'e8', 'n8', 8),
  (nextval('contacts_id_seq'), 'fn9', 'mn9', 'ln9', '9', 'MOBILE', '9', 'MOBILE', 'e9', 'n9', 9),
  (nextval('contacts_id_seq'), 'fn10', 'mn10', 'ln10', '10', 'MOBILE', '10', 'MOBILE', 'e10', 'n10', 10),
  (nextval('contacts_id_seq'), 'fn3', 'mn3', 'ln3', '3', 'MOBILE', '3', 'MOBILE', 'e3', 'n3', 4),
  (nextval('contacts_id_seq'), 'fn3', 'mn3', 'ln3', '3', 'MOBILE', '3', 'MOBILE', 'e3', 'n3', 7),
  (nextval('contacts_id_seq'), 'fn6', 'mn6', 'ln6', '6', 'MOBILE', '6', 'MOBILE', 'e6', 'n6', 1),
  (nextval('contacts_id_seq'), 'fn6', 'mn6', 'ln6', '6', 'MOBILE', '6', 'MOBILE', 'e6', 'n6', 9),
  (nextval('contacts_id_seq'), 'fn8', 'mn8', 'ln8', '8', 'MOBILE', '8', 'MOBILE', 'e8', 'n8', 2),
  (nextval('contacts_id_seq'), 'fn8', 'mn8', 'ln8', '8', 'MOBILE', '8', 'MOBILE', 'e8', 'n8', 5),
  (nextval('contacts_id_seq'), 'fn4', 'mn4', 'ln4', '4', 'MOBILE', '4', 'MOBILE', 'e4', 'n4', 3),
  (nextval('contacts_id_seq'), 'fn4', 'mn4', 'ln4', '4', 'MOBILE', '4', 'MOBILE', 'e4', 'n4', 10),
  (nextval('contacts_id_seq'), 'fn1', 'mn1', 'ln1', '1', 'MOBILE', '1', 'MOBILE', 'e1', 'n1', 10),
  (nextval('contacts_id_seq'), 'fn2', 'mn2', 'ln2', '2', 'MOBILE', '2', 'MOBILE', 'e2', 'n2', 10),
  (nextval('contacts_id_seq'), 'fn3', 'mn3', 'ln3', '3', 'MOBILE', '3', 'MOBILE', 'e3', 'n3', 10),
  (nextval('contacts_id_seq'), 'fn5', 'mn5', 'ln5', '5', 'MOBILE', '5', 'MOBILE', 'e5', 'n5', 10),
  (nextval('contacts_id_seq'), 'fn6', 'mn6', 'ln6', '6', 'MOBILE', '6', 'MOBILE', 'e6', 'n6', 10),
  (nextval('contacts_id_seq'), 'fn7', 'mn7', 'ln7', '7', 'MOBILE', '7', 'MOBILE', 'e7', 'n7', 10),
  (nextval('contacts_id_seq'), 'fn8', 'mn8', 'ln8', '8', 'MOBILE', '8', 'MOBILE', 'e8', 'n8', 10),
  (nextval('contacts_id_seq'), 'fn9', 'mn9', 'ln9', '9', 'MOBILE', '9', 'MOBILE', 'e9', 'n9', 10);

-- insert test data into table contacts_groups
INSERT INTO contacts_groups (contact_id, group_id) VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5),
  (6, 6),
  (7, 7),
  (8, 8),
  (9, 9),
  (10, 10);