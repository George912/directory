CREATE TABLE "groups" (
  "id"    SERIAL       NOT NULL,
  "name"  VARCHAR(30)  NOT NULL,
  "notes" VARCHAR(300) NOT NULL,
  "owner" INT          NOT NULL,
  CONSTRAINT groups_pk PRIMARY KEY ("id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "contacts" (
  "id"                    SERIAL       NOT NULL,
  "firstname"             VARCHAR(30)  NOT NULL,
  "middlename"            VARCHAR(30)  NOT NULL,
  "lastname"              VARCHAR(50)  NOT NULL,
  "firstphonenumber"      VARCHAR(11)  NOT NULL,
  "firstphonenumbertype"  VARCHAR(10)  NOT NULL,
  "secondphonenumber"     VARCHAR(11)  NOT NULL,
  "secondphonenumbertype" VARCHAR(10)  NOT NULL,
  "email"                 VARCHAR(30)  NOT NULL,
  "notes"                 VARCHAR(300) NOT NULL,
  "owner"                 INT          NOT NULL,
  CONSTRAINT contacts_pk PRIMARY KEY ("id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "contacts_groups" (
  "contact_id" INT NOT NULL,
  "group_id"   INT NOT NULL
) WITH (
OIDS = FALSE
);

CREATE TABLE "users" (
  "id"         SERIAL      NOT NULL,
  "login"      VARCHAR(20) NOT NULL UNIQUE,
  "password"   VARCHAR(20) NOT NULL,
  "firstname"  VARCHAR(30) NOT NULL,
  "middlename" VARCHAR(30) NOT NULL,
  "lastname"   VARCHAR(50) NOT NULL,
  "enabled"    BOOLEAN NOT NULL,
  CONSTRAINT users_pk PRIMARY KEY ("id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "user_roles" (
  "id"         SERIAL      NOT NULL,
  "user_id"  INT NOT NULL,
  "role" VARCHAR(30) NOT NULL,
  CONSTRAINT user_roles_pk PRIMARY KEY ("id")
) WITH (
OIDS = FALSE
);

ALTER TABLE "groups"
  ADD CONSTRAINT "groups_fk0" FOREIGN KEY ("owner") REFERENCES "users" ("id");

ALTER TABLE "contacts"
  ADD CONSTRAINT "contacts_fk0" FOREIGN KEY ("owner") REFERENCES "users" ("id");

ALTER TABLE "contacts_groups"
  ADD CONSTRAINT "contacts_groups_fk0" FOREIGN KEY ("contact_id") REFERENCES "contacts" ("id") ON DELETE CASCADE;

ALTER TABLE "contacts_groups"
  ADD CONSTRAINT "contacts_groups_fk1" FOREIGN KEY ("group_id") REFERENCES "groups" ("id") ON DELETE CASCADE;

ALTER TABLE "user_roles"
  ADD CONSTRAINT "user_roles_fk1" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE;

