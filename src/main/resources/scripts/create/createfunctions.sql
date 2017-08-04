-- create functions for table groups
CREATE OR REPLACE FUNCTION add_group(name varchar(30), notes varchar(300), owner int) RETURNS void AS $$
  INSERT INTO groups (id, name, notes, owner) VALUES
    (nextval('groups_id_seq'), name, notes, owner);
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION update_group(group_id int, group_name varchar(30), group_notes varchar(300), group_owner int) RETURNS void AS $$
  UPDATE groups
  SET name = group_name,
    notes = group_notes
  WHERE id = group_id
  AND owner = group_owner;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_all_groups(g_owner int) RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT *
    FROM groups
    WHERE owner = g_owner;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_group_by_id(group_id int, g_owner int) RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT *
  FROM groups
    WHERE id = group_id
    AND owner = g_owner;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_groups_by_name(group_name varchar(30), g_owner int) RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT *
    FROM groups
    WHERE lower(name) = lower(group_name)
    AND owner = g_owner;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_group(group_id int, g_owner int) RETURNS void AS $$
  DELETE FROM groups
    WHERE id = group_id
    AND owner = g_owner;
$$ LANGUAGE SQL;

-- create functions for table contacts_groups
CREATE OR REPLACE FUNCTION add_group_to_contact(contact_id int, group_id int) RETURNS void AS $$
  INSERT INTO contacts_groups (contact_id, group_id) VALUES
    (contact_id, group_id);
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_group_from_contact(c_id int, g_id int) RETURNS void AS $$
  DELETE FROM contacts_groups
  WHERE contact_id = c_id
        AND group_id = g_id;
$$ LANGUAGE SQL;

-- create functions for table contacts
CREATE OR REPLACE FUNCTION add_contact(firstname varchar(30), middlename varchar(30), lastname varchar(50),
                                       firstphonenumber varchar(11), firstphonenumbertype varchar(10), secondphonenumber varchar(11),
                                       secondphonenumbertype varchar(10),  email varchar(30), notes varchar(300), owner int) RETURNS void AS $$
  INSERT INTO contacts (id, firstname, middlename, lastname, firstphonenumber, firstphonenumbertype,
                        secondphonenumber, secondphonenumbertype, email, notes, owner) VALUES
    (nextval('contacts_id_seq'), firstname, middlename, lastname, firstphonenumber, firstphonenumbertype,
                                 secondphonenumber, secondphonenumbertype, email, notes, owner);
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
      AND owner = c_owner;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_contact(contact_id int, contact_owner int) RETURNS void AS $$
  DELETE FROM contacts
    WHERE id = contact_id
    AND owner = contact_owner;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_all_contacts(contact_owner int)
  RETURNS TABLE (id int, firstname varchar(30), middlename varchar(30), lastname varchar(50),
                 firstphonenumber varchar(11), firstphonenumbertype varchar(10), secondphonenumber varchar(11),
                 secondphonenumbertype varchar(10), email varchar(30), notes varchar(300), owner int) AS $$
  SELECT * FROM contacts
    WHERE owner = contact_owner;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_contact_by_id(contact_id int, contact_owner int)
  RETURNS TABLE (id int, firstname varchar(30), middlename varchar(30), lastname varchar(50),
                   firstphonenumber varchar(11), firstphonenumbertype varchar(10), secondphonenumber varchar(11),
                   secondphonenumbertype varchar(10), email varchar(30), notes varchar(300), owner int) AS $$
  SELECT * FROM contacts
    WHERE id = contact_id
    AND owner = contact_owner;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_contacts_by_name(c_firstname varchar(30), c_owner int)
  RETURNS TABLE (id int, firstname varchar(30), middlename varchar(30), lastname varchar(50),
                 firstphonenumber varchar(11), firstphonenumbertype varchar(10), secondphonenumber varchar(11),
                 secondphonenumbertype varchar(10), email varchar(30), notes varchar(300), owner int)  AS $$
  SELECT * FROM contacts
    WHERE lower(firstname) = lower(c_firstname)
    AND owner = c_owner;
$$ LANGUAGE SQL;

-- create functions for triggers
CREATE OR REPLACE FUNCTION delete_groups_info() RETURNS trigger AS $$
  BEGIN
    IF TG_OP = 'DELETE' THEN
      DELETE FROM contacts_groups
      WHERE group_id = OLD.id;
      RETURN OLD;
    END IF;
  END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION delete_contacts_info() RETURNS trigger AS $$
  BEGIN
    IF TG_OP = 'DELETE' THEN
      DELETE FROM contacts_groups
      WHERE contact_id = OLD.id;
      RETURN OLD;
    END IF;
  END;
$$ LANGUAGE plpgsql;