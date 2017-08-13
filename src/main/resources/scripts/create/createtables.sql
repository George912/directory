CREATE TABLE "groups" (
  "id" serial NOT NULL,
  "name" varchar(30) NOT NULL,
  "notes" varchar(300) NOT NULL,
  "ownerId" int NOT NULL,
  CONSTRAINT groups_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);

CREATE TABLE "contacts" (
  "id" serial NOT NULL,
  "firstname" varchar(30) NOT NULL,
  "middlename" varchar(30) NOT NULL,
  "lastname" varchar(50) NOT NULL,
  "firstphonenumber" varchar(11) NOT NULL,
  "firstphonenumbertype" varchar(10) NOT NULL,
  "secondphonenumber" varchar(11) NOT NULL,
  "secondphonenumbertype" varchar(10) NOT NULL,
  "email" varchar(30) NOT NULL,
  "notes" varchar(300) NOT NULL,
  "ownerId" int NOT NULL,
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

CREATE TABLE "users" (
  "id" serial NOT NULL,
  "login" varchar(20) NOT NULL UNIQUE,
  "password" varchar(20) NOT NULL,
  "firstname" varchar(30) NOT NULL,
  "middlename" varchar(30) NOT NULL,
  "lastname" varchar(50) NOT NULL,
  CONSTRAINT users_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);

ALTER TABLE "groups" ADD CONSTRAINT "groups_fk0" FOREIGN KEY ("ownerId") REFERENCES "users"("id");

ALTER TABLE "contacts" ADD CONSTRAINT "contacts_fk0" FOREIGN KEY ("ownerId") REFERENCES "users"("id");

ALTER TABLE "contacts_groups" ADD CONSTRAINT "contacts_groups_fk0" FOREIGN KEY ("contact_id") REFERENCES "contacts"("id");
ALTER TABLE "contacts_groups" ADD CONSTRAINT "contacts_groups_fk1" FOREIGN KEY ("group_id") REFERENCES "groups"("id");

