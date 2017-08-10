-- create functions for table groups
CREATE OR REPLACE FUNCTION add_group(name VARCHAR(30), notes VARCHAR(300), owner INT)
  RETURNS VOID AS $$
INSERT INTO groups (id, name, notes, owner) VALUES
  (nextval('groups_id_seq'), name, notes, owner)
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION update_group(group_id INT, group_name VARCHAR(30), group_notes VARCHAR(300), group_owner INT)
  RETURNS VOID AS $$
UPDATE groups
SET name = group_name,
  notes  = group_notes
WHERE id = group_id
      AND owner = group_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_all_groups(g_owner INT)
  RETURNS TABLE(id INT, name VARCHAR(30), notes VARCHAR(300), owner INT) AS $$
SELECT *
FROM groups
WHERE owner = g_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_group_by_id(group_id INT, g_owner INT)
  RETURNS TABLE(id INT, name VARCHAR(30), notes VARCHAR(300), owner INT) AS $$
SELECT *
FROM groups
WHERE id = group_id
      AND owner = g_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_groups_by_name(group_name VARCHAR(30), g_owner INT)
  RETURNS TABLE(id INT, name VARCHAR(30), notes VARCHAR(300), owner INT) AS $$
SELECT *
FROM groups
WHERE lower(name) = lower(group_name)
      AND owner = g_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_group(group_id INT, g_owner INT)
  RETURNS VOID AS $$
DELETE FROM groups
WHERE id = group_id
      AND owner = g_owner
$$ LANGUAGE SQL;

-- create functions for table contacts_groups
CREATE OR REPLACE FUNCTION add_group_to_contact(contact_id INT, group_id INT)
  RETURNS VOID AS $$
INSERT INTO contacts_groups (contact_id, group_id) VALUES
  (contact_id, group_id)
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_group_from_contact(c_id INT, g_id INT)
  RETURNS VOID AS $$
DELETE FROM contacts_groups
WHERE contact_id = c_id
      AND group_id = g_id
$$ LANGUAGE SQL;

-- create functions for table contacts
CREATE OR REPLACE FUNCTION add_contact(firstname             VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50),
                                       firstphonenumber      VARCHAR(11), firstphonenumbertype VARCHAR(10),
                                       secondphonenumber     VARCHAR(11),
                                       secondphonenumbertype VARCHAR(10), email VARCHAR(30), notes VARCHAR(300),
                                       owner                 INT)
  RETURNS VOID AS $$
INSERT INTO contacts (id, firstname, middlename, lastname, firstphonenumber, firstphonenumbertype,
                      secondphonenumber, secondphonenumbertype, email, notes, owner) VALUES
  (nextval('contacts_id_seq'), firstname, middlename, lastname, firstphonenumber, firstphonenumbertype,
                               secondphonenumber, secondphonenumbertype, email, notes, owner)
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION update_contact(c_id                    INT, c_firstname VARCHAR(30),
                                          c_middlename            VARCHAR(30), c_lastname VARCHAR(50),
                                          c_firstphonenumber      VARCHAR(11), c_firstphonenumbertype VARCHAR(10),
                                          c_secondphonenumber     VARCHAR(11),
                                          c_secondphonenumbertype VARCHAR(10), c_email VARCHAR(30),
                                          c_notes                 VARCHAR(300), c_owner INT)
  RETURNS VOID AS $$
UPDATE contacts
SET firstname           = c_firstname,
  middlename            = c_middlename,
  lastname              = c_lastname,
  firstphonenumber      = c_firstphonenumber,
  firstphonenumbertype  = c_firstphonenumbertype,
  secondphonenumber     = c_secondphonenumber,
  secondphonenumbertype = c_secondphonenumbertype,
  email                 = c_email,
  notes                 = c_notes,
  owner                 = c_owner
WHERE id = c_id
      AND owner = c_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_contact(contact_id INT, contact_owner INT)
  RETURNS VOID AS $$
DELETE FROM contacts
WHERE id = contact_id
      AND owner = contact_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_all_contacts(contact_owner INT)
  RETURNS TABLE(id                    INT, firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50),
                firstphonenumber      VARCHAR(11), firstphonenumbertype VARCHAR(10), secondphonenumber VARCHAR(11),
                secondphonenumbertype VARCHAR(10), email VARCHAR(30), notes VARCHAR(300), owner INT) AS $$
SELECT *
FROM contacts
WHERE owner = contact_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_contact_by_id(contact_id INT, contact_owner INT)
  RETURNS TABLE(id                    INT, firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50),
                firstphonenumber      VARCHAR(11), firstphonenumbertype VARCHAR(10), secondphonenumber VARCHAR(11),
                secondphonenumbertype VARCHAR(10), email VARCHAR(30), notes VARCHAR(300), owner INT) AS $$
SELECT *
FROM contacts
WHERE id = contact_id
      AND owner = contact_owner
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_contacts_by_name(c_firstname VARCHAR(30), c_owner INT)
  RETURNS TABLE(id                    INT, firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50),
                firstphonenumber      VARCHAR(11), firstphonenumbertype VARCHAR(10), secondphonenumber VARCHAR(11),
                secondphonenumbertype VARCHAR(10), email VARCHAR(30), notes VARCHAR(300), owner INT) AS $$
SELECT *
FROM contacts
WHERE lower(firstname) = lower(c_firstname)
      AND owner = c_owner
$$ LANGUAGE SQL;

-- create functions for triggers
CREATE OR REPLACE FUNCTION delete_groups_info()
  RETURNS TRIGGER AS $$
BEGIN
  IF TG_OP = 'DELETE'
  THEN
    DELETE FROM contacts_groups
    WHERE group_id = OLD.id;
    RETURN OLD;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION delete_contacts_info()
  RETURNS TRIGGER AS $$
BEGIN
  IF TG_OP = 'DELETE'
  THEN
    DELETE FROM contacts_groups
    WHERE contact_id = OLD.id;
    RETURN OLD;
  END IF;
END;
$$ LANGUAGE plpgsql;

-- create analytic functions
CREATE FUNCTION get_user_count()
  RETURNS INT AS $$
DECLARE
  user_count INT;
BEGIN
  SELECT COUNT(*)
  INTO user_count
  FROM users;
  RETURN user_count;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_each_user_contact_count()
  RETURNS TABLE(user_id INT, contact_count BIGINT) AS $$
SELECT
  u.id        AS user_id,
  count(c.id) AS contact_count
FROM users u
  INNER JOIN contacts c ON u.id = c."owner"
GROUP BY user_id
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_each_user_group_count()
  RETURNS TABLE(user_id INT, group_count BIGINT) AS $$
SELECT
  u.id        AS user_id,
  count(g.id) AS group_count
FROM users u
  INNER JOIN groups g ON u.id = g."owner"
GROUP BY user_id
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION avg_user_count_in_groups()
  RETURNS TABLE(avg_count NUMERIC) AS $$
SELECT avg(s.user_count) AS avg_count
FROM (SELECT
        g."name"    AS group_name,
        count(u.id) AS user_count
      FROM users u
        INNER JOIN groups g ON u.id = g."owner"
      GROUP BY group_name) s
$$ LANGUAGE SQL;

CREATE FUNCTION get_inactive_user_count()
  RETURNS INT AS $$
DECLARE
  user_count INT;
BEGIN
  SELECT COUNT(u.id)
  INTO user_count
  FROM users u
    INNER JOIN contacts c ON u.id = c."owner"
  GROUP BY u.id
  HAVING count(c.id) < 10;
  RETURN user_count;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION avg_users_contact_count()
  RETURNS TABLE(avg_count NUMERIC) AS $$
SELECT avg(s.contact_count) AS avg_count
FROM (SELECT
        u.id        AS user_id,
        count(c.id) AS contact_count
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

-- не робит
CREATE OR REPLACE FUNCTION add_user(login VARCHAR(20), password VARCHAR(20),
                                    firstname VARCHAR(30), middlename VARCHAR(30), lastname VARCHAR(50))
  RETURNS VOID AS $$
INSERT INTO users (id, login, password, firstname, middlename, lastname) VALUES
  (nextval('users_id_seq'), login, password, firstname, middlename, lastname);
$$ LANGUAGE SQL;