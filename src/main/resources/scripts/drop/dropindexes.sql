-- drop indexes for table groups
DROP INDEX IF EXISTS group_name_idx;

-- drop indexes for table contacts
DROP INDEX IF EXISTS contact_name_idx;

-- drop indexes for table users
DROP INDEX IF EXISTS user_login_idx;

-- drop indexes for table contacts_groups
DROP INDEX IF EXISTS cg_contact_id_idx;
DROP INDEX IF EXISTS cg_group_id_idx;
