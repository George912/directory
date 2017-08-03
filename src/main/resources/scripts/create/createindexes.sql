-- create indexes for table groups
CREATE INDEX group_id_idx
  ON groups
  USING btree
  (id);

CREATE INDEX group_name_idx
  ON groups
  USING btree
  (name);

-- create indexes for table contacts
CREATE INDEX contact_id_idx
  ON contacts
  USING btree
  (id);

CREATE INDEX contact_name_idx
  ON contacts
  USING btree
  (firstname);

-- create indexes for table users
CREATE INDEX user_id_idx
  ON users
  USING btree
  (id);

CREATE INDEX user_login_idx
  ON users
  USING btree
  (login);

-- create indexes for table contacts_groups
CREATE INDEX cg_contact_id_idx
  ON contacts_groups
  USING btree
  (contact_id);

CREATE INDEX cg_group_id_idx
  ON contacts_groups
  USING btree
  (group_id);