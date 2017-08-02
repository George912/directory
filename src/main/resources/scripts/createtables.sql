DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS contacts CASCADE;
DROP TABLE IF EXISTS contacts_groups CASCADE;

CREATE TABLE "groups" (
  "id" serial NOT NULL,
  "name" varchar(30) NOT NULL,
  "notes" varchar(300) NOT NULL,
  CONSTRAINT groups_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);

CREATE TABLE "contacts" (
  "id" serial NOT NULL,
  "firstName" varchar(30) NOT NULL,
  "middleName" varchar(30) NOT NULL,
  "lastName" varchar(50) NOT NULL,
  "firstPhoneNumber" varchar(11) NOT NULL,
  "firstPhoneNumberType" varchar(10) NOT NULL,
  "secondPhoneNumber" varchar(11) NOT NULL,
  "secondPhoneNumberType" varchar(10) NOT NULL,
  "email" varchar(30) NOT NULL,
  "notes" varchar(300) NOT NULL,
  CONSTRAINT contacts_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);

CREATE TABLE "contacts_groups" (
  "contact_id" int NOT NULL,
  "group_id" int NOT NULL
) WITH (
OIDS=FALSE
);

ALTER TABLE "contacts_groups" ADD CONSTRAINT "contacts_groups_fk0" FOREIGN KEY ("contact_id") REFERENCES "contacts"("id");
ALTER TABLE "contacts_groups" ADD CONSTRAINT "contacts_groups_fk1" FOREIGN KEY ("group_id") REFERENCES "groups"("id");
