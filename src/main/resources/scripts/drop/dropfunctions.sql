-- delete functions for table groups
DROP FUNCTION IF EXISTS add_group( VARCHAR(30), VARCHAR(300), INT );
DROP FUNCTION IF EXISTS update_group( INT, VARCHAR(30), VARCHAR(300), INT );
DROP FUNCTION IF EXISTS get_all_groups( INT );
DROP FUNCTION IF EXISTS get_group_by_id( INT, INT );
DROP FUNCTION IF EXISTS get_groups_by_name( VARCHAR(30), INT );
DROP FUNCTION IF EXISTS delete_group( INT, INT );

-- delete functions for table contacts_groups
DROP FUNCTION IF EXISTS add_group_to_contact( INT, INT );
DROP FUNCTION IF EXISTS delete_group_from_contact( INT, INT );

-- delete functions for table contacts
DROP FUNCTION IF EXISTS add_contact( VARCHAR(30), VARCHAR(30), VARCHAR(50), VARCHAR(11),
  VARCHAR(10), VARCHAR(11), VARCHAR(10), VARCHAR(30), VARCHAR(300), INT );
DROP FUNCTION IF EXISTS update_contact( INT, VARCHAR(30), VARCHAR(30), VARCHAR(50), VARCHAR(11),
  VARCHAR(10), VARCHAR(11), VARCHAR(10), VARCHAR(30), VARCHAR(300), INT );
DROP FUNCTION IF EXISTS delete_contact( INT, INT );
DROP FUNCTION IF EXISTS get_all_contacts( INT );
DROP FUNCTION IF EXISTS get_contact_by_id( INT, INT );
DROP FUNCTION IF EXISTS get_contacts_by_name( VARCHAR(30), INT );

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