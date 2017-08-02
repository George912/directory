-- create indexes for table groups
DROP INDEX IF EXISTS id_idx;
DROP INDEX IF EXISTS name_idx;

CREATE INDEX id_idx
ON groups
USING btree
(id);

CREATE INDEX name_idx
ON groups
USING btree
(name);

-- create indexes for table contacts
DROP INDEX IF EXISTS id_idx;
DROP INDEX IF EXISTS firstName_idx;

CREATE INDEX id_idx
  ON contacts
  USING btree
  (id);

CREATE INDEX firstName_idx
  ON contacts
  USING btree
  (firstName);