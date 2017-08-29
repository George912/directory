-- create indexes for table groups
CREATE INDEX group_name_idx
  ON groups
  USING BTREE
  (name);

-- create indexes for table contacts
CREATE INDEX contact_name_idx
  ON contacts
  USING BTREE
  (firstname);

-- create indexes for table users
CREATE INDEX user_login_idx
  ON users
  USING BTREE
  (login);

-- create indexes for table contacts_groups
CREATE INDEX cg_contact_id_idx
  ON contacts_groups
  USING BTREE
  (contact_id);

CREATE INDEX cg_group_id_idx
  ON contacts_groups
  USING BTREE
  (group_id);