-- create triggers for table groups 
CREATE FUNCTION delete_groups_info() RETURNS trigger AS $$
    BEGIN
        DELETE FROM 
    RETURN NEW;
    END;
$$ LANGUAGE plpgsql;