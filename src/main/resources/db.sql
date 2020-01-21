-- Database: testdb
 --DROP DATABASE testdb;

CREATE DATABASE testdb
WITH
OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

DROP TABLE public.policy_type;
-- Table: public.types

 DROP TABLE public.types;

CREATE TABLE public.types
(
  id uuid NOT NULL DEFAULT uuid_generate_v4(),
  name character varying(50) COLLATE pg_catalog."default",
  CONSTRAINT types_pkey PRIMARY KEY (id)
)
  WITH (
    OIDS = FALSE
  )
  TABLESPACE pg_default;



DELETE FROM public.types
WHERE name = 'KASKO';
DELETE FROM public.types
WHERE name = 'National';
INSERT INTO public.types(
  name)
VALUES ('KASKO');
INSERT INTO public.types(
  name)
VALUES ('National');


-- Table: public.policy

 DROP TABLE public.policy;

CREATE TABLE public.policy
(
  id uuid NOT NULL DEFAULT uuid_generate_v4(),
  id_type uuid,
  company character(3) COLLATE pg_catalog."default",
  car_type character(1) COLLATE pg_catalog."default",
  id_period character(1) COLLATE pg_catalog."default",
  price numeric(17,2),
  stock integer,
  is_active boolean,
  is_delete boolean,
  CONSTRAINT policy_pkey PRIMARY KEY (id),
  CONSTRAINT policy_type_fkey FOREIGN KEY (id_type)
    REFERENCES public.types (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)
  WITH (
    OIDS = FALSE
  )
  TABLESPACE pg_default;

ALTER TABLE public.policy
  OWNER to postgres;


DROP TABLE public.user_role;
-- Table: public.role

 DROP TABLE public.role;

CREATE TABLE public.role
(
  id uuid NOT NULL DEFAULT uuid_generate_v4(),
  name character varying(45) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
  WITH (
    OIDS = FALSE
  )
  TABLESPACE pg_default;

ALTER TABLE public.role
  OWNER to postgres;


INSERT INTO public.role(
  name)
VALUES ('ROLE_USER');
INSERT INTO public.role(
  name)
VALUES ('ROLE_ADMIN');

 DROP TABLE public.users;

CREATE TABLE public.users
(
  id uuid NOT NULL DEFAULT uuid_generate_v4(),
  username character varying(255) COLLATE pg_catalog."default" NOT NULL,
  password character varying(255) COLLATE pg_catalog."default" NOT NULL,
  is_active boolean,
  is_delete boolean,
  id_role uuid,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_username_key UNIQUE (username),
  CONSTRAINT users_id_role_fkey FOREIGN KEY (id_role)
    REFERENCES public.role (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)
  WITH (
    OIDS = FALSE
  )
  TABLESPACE pg_default;

ALTER TABLE public.users
  OWNER to postgres;
