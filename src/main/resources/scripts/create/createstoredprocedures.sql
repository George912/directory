-- create functions for table groups
CREATE OR REPLACE FUNCTION add_group(name varchar(30), notes varchar(300), owner int) RETURNS void AS $$
  INSERT INTO groups (id, name, notes, owner) VALUES
    (nextval('groups_id_seq'), name, notes, owner);
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION update_group(group_id int, group_name varchar(30), group_notes varchar(300), group_owner int) RETURNS void AS $$
  UPDATE groups
  SET name = group_name,
    notes = group_notes,
    owner = group_owner
  WHERE id = group_id;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_all_groups() RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT * FROM groups;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_group_by_id(group_id int) RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT *
    FROM groups
    WHERE id = group_id;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_groups_by_name(group_name varchar(30)) RETURNS TABLE(id int, name varchar(30), notes varchar(300), owner int) AS $$
  SELECT *
    FROM groups
    WHERE lower(name) = lower(group_name);
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION delete_group_by_id(group_id int) RETURNS void AS $$
  DELETE FROM groups
    WHERE id = group_id;
$$ LANGUAGE SQL;