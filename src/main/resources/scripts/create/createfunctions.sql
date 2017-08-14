-- create functions for table groups
CREATE OR REPLACE FUNCTION add_group(name varchar(30), notes varchar(300), owner int) RETURNS void AS $$
  INSERT INTO groups (id, name, notes, owner) VALUES
    (nextval('groups_id_seq'), name, notes, owner)
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION update_group(group_id int, group_name varchar(30), group_notes varchar(300), group_owner int) RETURNS void AS $$
  UPDATE groups
  SET name = group_name,
    notes = group_notes
  WHERE id = group_id
  AND owner = group_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_all_groups(g_owner int) RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT *
    FROM groups
    WHERE owner = g_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_group_by_id(group_id int, g_owner int) RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT *
  FROM groups
    WHERE id = group_id
    AND owner = g_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_groups_by_name(group_name varchar(30), g_owner int) RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT *
    FROM groups
    WHERE lower(name) = lower(group_name)
    AND owner = g_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_group(group_id int, g_owner int) RETURNS void AS $$
  DELETE FROM groups
    WHERE id = group_id
    AND owner = g_owner
$$ LANGUAGE SQL;

-- create functions for table contacts_groups
CREATE OR REPLACE FUNCTION add_group_to_contact(contact_id int, group_id int) RETURNS void AS $$
  INSERT INTO contacts_groups (contact_id, group_id) VALUES
    (contact_id, group_id)
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_group_from_contact(c_id int, g_id int) RETURNS void AS $$
  DELETE FROM contacts_groups
  WHERE contact_id = c_id
        AND group_id = g_id
$$ LANGUAGE SQL;

-- create functions for table contacts
CREATE OR REPLACE FUNCTION add_contact(firstname varchar(30), middlename varchar(30), lastname varchar(50),
                                       firstphonenumber varchar(11), firstphonenumbertype varchar(10), secondphonenumber varchar(11),
                                       secondphonenumbertype varchar(10),  email varchar(30), notes varchar(300), owner int) RETURNS void AS $$
  INSERT INTO contacts (id, firstname, middlename, lastname, firstphonenumber, firstphonenumbertype,
                        secondphonenumber, secondphonenumbertype, email, notes, owner) VALUES
    (nextval('contacts_id_seq'), firstname, middlename, lastname, firstphonenumber, firstphonenumbertype,
                                 secondphonenumber, secondphonenumbertype, email, notes, owner)
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION update_contact(c_id int, c_firstname varchar(30), c_middlename varchar(30), c_lastname varchar(50),
                                          c_firstphonenumber varchar(11), c_firstphonenumbertype varchar(10), c_secondphonenumber varchar(11),
                                          c_secondphonenumbertype varchar(10), c_email varchar(30), c_notes varchar(300), c_owner int) RETURNS void AS $$
  UPDATE contacts
    SET firstname = c_firstname,
      middlename = c_middlename,
      lastname = c_lastname,
      firstphonenumber = c_firstphonenumber,
      firstphonenumbertype = c_firstphonenumbertype,
      secondphonenumber = c_secondphonenumber,
      secondphonenumbertype = c_secondphonenumbertype,
      email = c_email,
      notes = c_notes,
      owner = c_owner
    WHERE id = c_id
      AND owner = c_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_contact(contact_id int, contact_owner int) RETURNS void AS $$
  DELETE FROM contacts
    WHERE id = contact_id
    AND owner = contact_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_all_contacts(contact_owner int)
  RETURNS TABLE (id int, firstname varchar(30), middlename varchar(30), lastname varchar(50),
                 firstphonenumber varchar(11), firstphonenumbertype varchar(10), secondphonenumber varchar(11),
                 secondphonenumbertype varchar(10), email varchar(30), notes varchar(300), owner int) AS $$
  SELECT * FROM contacts
    WHERE owner = contact_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_contact_by_id(contact_id int, contact_owner int)
  RETURNS TABLE (id int, firstname varchar(30), middlename varchar(30), lastname varchar(50),
                   firstphonenumber varchar(11), firstphonenumbertype varchar(10), secondphonenumber varchar(11),
                   secondphonenumbertype varchar(10), email varchar(30), notes varchar(300), owner int) AS $$
  SELECT * FROM contacts
    WHERE id = contact_id
    AND owner = contact_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_contacts_by_name(c_firstname varchar(30), c_owner int)
  RETURNS TABLE (id int, firstname varchar(30), middlename varchar(30), lastname varchar(50),
                 firstphonenumber varchar(11), firstphonenumbertype varchar(10), secondphonenumber varchar(11),
                 secondphonenumbertype varchar(10), email varchar(30), notes varchar(300), owner int)  AS $$
  SELECT * FROM contacts
    WHERE lower(firstname) = lower(c_firstname)
    AND owner = c_owner
$$ LANGUAGE SQL;

-- create analytic functions
CREATE FUNCTION get_all_users() RETURNS int AS $$
  DECLARE
    user_count int;
  BEGIN
    SELECT COUNT(*)
    INTO user_count
    FROM users;
    RETURN user_count;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_each_user_contact_count() RETURNS TABLE(user_id int, contact_count bigint) AS $$
  SELECT u.id as user_id, count(c.id) as contact_count
  FROM users u
    INNER JOIN contacts c ON u.id = c."owner"
  GROUP BY user_id
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_each_user_group_count() RETURNS TABLE(user_id int, group_count bigint) AS $$
  SELECT u.id as user_id, count(g.id) as group_count
  FROM users u
    INNER JOIN groups g ON u.id = g."owner"
  GROUP BY user_id
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION avg_user_count_in_groups() RETURNS TABLE(avg_count numeric) AS $$
  SELECT avg(s.user_count) as avg_count
  FROM(SELECT g."name" as group_name, count(u.id) as user_count
       FROM users u
         INNER JOIN groups g ON u.id = g."owner"
       GROUP BY group_name) s
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_inactive_user_count() RETURNS TABLE(user_id int, contact_count bigint) AS $$
  SELECT u.id as user_id, count(c.id) as contact_count
  FROM users u
    INNER JOIN contacts c ON u.id = c."owner"
  GROUP BY user_id
  HAVING count(c.id) < 10
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION avg_users_contact_count() RETURNS TABLE(avg_count numeric) AS $$
  SELECT avg(s.contact_count) as avg_count
  FROM(SELECT u.id as user_id, count(c.id) as contact_count
       FROM users u
         INNER JOIN contacts c ON u.id = c."owner"
       GROUP BY user_id) s
$$ LANGUAGE SQL;

-- create functions for table users
CREATE OR REPLACE FUNCTION get_all_users()
  RETURNS TABLE(id INT, login VARCHAR(20), password VARCHAR(20),
                firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50)) AS $$
SELECT *
FROM users;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION add_user(login VARCHAR(20), password VARCHAR(20),
                                    firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50))
  RETURNS VOID AS $$
INSERT INTO users (id, login, password, firstname, middlename, lastname) VALUES
  (nextval('users_id_seq'), login, password, firstname, middlename, lastname);
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION update_user(u_id INT, u_login VARCHAR(20), u_password VARCHAR(20),
                                       u_firstname VARCHAR(30), u_middlename VARCHAR(30), u_lastname VARCHAR(50))
  RETURNS VOID AS $$
UPDATE users
SET login = u_login,
  password = u_password,
  firstname = u_firstname,
  middlename = u_middlename,
  lastname = u_lastname
WHERE id = u_id;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_user_by_name(u_firstname VARCHAR(30))
  RETURNS TABLE(id INT, login VARCHAR(20), password VARCHAR(20),
                firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50)) AS $$
SELECT *
FROM users
WHERE lower(firstname) = lower(u_firstname);
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_user(u_id INT)
  RETURNS VOID AS $$
DELETE FROM users
WHERE id = u_id;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_user_by_id(u_id INT)
  RETURNS TABLE(id INT, login VARCHAR(20), password VARCHAR(20),
                firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50)) AS $$
SELECT *
FROM users
WHERE id = u_id;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_user_id(u_login VARCHAR(20), u_password VARCHAR(20))
  RETURNS INT AS $$
SELECT id
FROM users
WHERE lower(login) = lower(u_login)
      AND lower(password) = lower(u_password);
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_user_by_credential(u_login VARCHAR(20), u_password VARCHAR(20))
  RETURNS TABLE(id INT, login VARCHAR(20), password VARCHAR(20),
                firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50)) AS $$
SELECT *
FROM users
WHERE lower(login) = lower(u_login)
      AND lower(password) = lower(u_password);
$$ LANGUAGE SQL;