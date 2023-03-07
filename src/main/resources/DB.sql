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
--inserting data in to leave_type table
INSERT INTO hrapp.leave_type(id, name)	VALUES (1, 'Sick Leave');
INSERT INTO hrapp.leave_type(id, name)	VALUES (2, 'Maternity Leave');
INSERT INTO hrapp.leave_type(id, name)	VALUES (3, 'Annual Leave');