ALTER TABLE "groups" DROP CONSTRAINT IF EXISTS "groups_fk0";

ALTER TABLE "contacts" DROP CONSTRAINT IF EXISTS "contacts_fk0";

ALTER TABLE "contacts_groups" DROP CONSTRAINT IF EXISTS "contacts_groups_fk0";

ALTER TABLE "contacts_groups" DROP CONSTRAINT IF EXISTS "contacts_groups_fk1";

DROP TABLE IF EXISTS "groups";

DROP TABLE IF EXISTS "contacts";

DROP TABLE IF EXISTS "contacts_groups";

DROP TABLE IF EXISTS "users";
