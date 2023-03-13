CREATE TABLE IF NOT EXISTS "hrapp"."department"
(
    "id" bigint PRIMARY KEY,
    "name" text
);

CREATE TABLE IF NOT EXISTS "hrapp"."employee"
(
    "id" bigint PRIMARY KEY,
    "name" text,
	"email" text,
	"address" text,
	"department_id" bigint REFERENCES "hrapp"."department"
);

CREATE TABLE IF NOT EXISTS "hrapp"."leave_type"
(
    "id" bigint PRIMARY KEY,
    "name" text
);

CREATE TABLE IF NOT EXISTS "hrapp"."leave"
(
    "id" bigint PRIMARY KEY,
    "leave_type_id" bigint,
	"from" date,
	"to" date,
	"no_of_days" integer,
	"note" text,
	"employee_id" bigint REFERENCES "hrapp"."employee"
);

CREATE TABLE IF NOT EXISTS "hrapp"."expense_claim"
(
    "id" bigint PRIMARY KEY,
    "date" date,
	"description" text,
	"total" decimal,
	"status" text,
	"employee_id" bigint REFERENCES "hrapp"."employee"
);

CREATE TABLE IF NOT EXISTS "hrapp"."expense_claim_detail"
(
    "id" bigint PRIMARY KEY,
    "date" date,
	"description" text,
	"type" text,
	"total" decimal,
	"expense_claim_id" bigint REFERENCES "hrapp"."expense_claim"
);

--script to create sequence
CREATE SEQUENCE hrapp.department_seq
INCREMENT 1
MINVALUE 1
START 1;

CREATE SEQUENCE hrapp.employee_seq
INCREMENT 1
MINVALUE 1
START 1;

CREATE SEQUENCE hrapp.expense_claim_seq
INCREMENT 1
MINVALUE 1
START 1;

CREATE SEQUENCE hrapp.expense_claim_detail_seq
INCREMENT 1
MINVALUE 1
START 1;

CREATE SEQUENCE hrapp.leave_seq
INCREMENT 1
MINVALUE 1
START 1;

CREATE SEQUENCE hrapp.leave_type_seq
INCREMENT 1
MINVALUE 1
START 1;

--inserting data in to department table
INSERT INTO hrapp.department(id, name)	VALUES (nextval('hrapp.department_seq'), 'IT Application');
INSERT INTO hrapp.department(id, name)	VALUES (nextval('hrapp.department_seq'), 'IT Database');
INSERT INTO hrapp.department(id, name)	VALUES (nextval('hrapp.department_seq'), 'IT Security');
INSERT INTO hrapp.department(id, name)	VALUES (nextval('hrapp.department_seq'), 'IT Unix');
INSERT INTO hrapp.department(id, name)	VALUES (nextval('hrapp.department_seq'), 'IT Testing');
INSERT INTO hrapp.department(id, name)	VALUES (nextval('hrapp.department_seq'), 'HR');

--inserting data in to leave_type table
INSERT INTO hrapp.leave_type(id, name)	VALUES (nextval('hrapp.leave_type_seq'), 'Sick Leave');
INSERT INTO hrapp.leave_type(id, name)	VALUES (nextval('hrapp.leave_type_seq'), 'Maternity Leave');
INSERT INTO hrapp.leave_type(id, name)	VALUES (nextval('hrapp.leave_type_seq'), 'Annual Leave');