-- create triggers for table groups 
CREATE TRIGGER t_group
    AFTER DELETE ON groups
    FOR EACH STATEMENT
    EXECUTE PROCEDURE delete_groups_info();

-- create triggers for table contacts
CREATE TRIGGER t_contact
    AFTER DELETE ON contacts
    FOR EACH STATEMENT
    EXECUTE PROCEDURE delete_contacts_info();