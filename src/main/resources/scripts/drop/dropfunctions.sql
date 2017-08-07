-- delete functions for table groups
DROP FUNCTION IF EXISTS add_group(varchar(30), varchar(300), int);
DROP FUNCTION IF EXISTS update_group(int, varchar(30), varchar(300), int);
DROP FUNCTION IF EXISTS get_all_groups(int);
DROP FUNCTION IF EXISTS get_group_by_id(int, int);
DROP FUNCTION IF EXISTS get_groups_by_name(varchar(30), int);
DROP FUNCTION IF EXISTS delete_group(int, int);

-- delete functions for table contacts_groups
DROP FUNCTION IF EXISTS add_group_to_contact(int, int);
DROP FUNCTION IF EXISTS delete_group_from_contact(int, int);

-- delete functions for table contacts
DROP FUNCTION IF EXISTS add_contact(varchar(30), varchar(30), varchar(50), varchar(11),
  varchar(10), varchar(11), varchar(10), varchar(30), varchar(300), int);
DROP FUNCTION IF EXISTS update_contact(int, varchar(30), varchar(30), varchar(50), varchar(11),
  varchar(10), varchar(11), varchar(10), varchar(30), varchar(300), int);
DROP FUNCTION IF EXISTS delete_contact(int, int);
DROP FUNCTION IF EXISTS get_all_contacts(int);
DROP FUNCTION IF EXISTS get_contact_by_id(int, int);
DROP FUNCTION IF EXISTS get_contacts_by_name(varchar(30), int);

-- delete trigger functions
DROP FUNCTION IF EXISTS delete_groups_info();
DROP FUNCTION IF EXISTS delete_contacts_info();

-- delete analytic functions
DROP FUNCTION IF EXISTS get_all_users();
DROP FUNCTION IF EXISTS get_each_user_contact_count();
DROP FUNCTION IF EXISTS get_each_user_group_count();
DROP FUNCTION IF EXISTS avg_user_count_in_groups();
DROP FUNCTION IF EXISTS get_inactive_user_count();
DROP FUNCTION IF EXISTS avg_users_contact_count();