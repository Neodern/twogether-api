--
-- person
--
CREATE TABLE person (
  id        BIGSERIAL CONSTRAINT pk_person PRIMARY KEY,
  email     TEXT    NOT NULL,
  firstname TEXT    NOT NULL,
  lastname  TEXT    NOT NULL,
  app_role  TEXT    NOT NULL,
  version   INT     NOT NULL,
  login     TEXT    NOT NULL,
  password  TEXT    NOT NULL,
  enabled   BOOLEAN NOT NULL
);

--
-- person_role table
--
CREATE TABLE person_role (
  id           BIGSERIAL CONSTRAINT pk_person_role PRIMARY KEY,
  fk_person_id BIGINT NOT NULL,
  role         TEXT   NOT NULL
);

ALTER TABLE person_role
  ADD CONSTRAINT person_role_fk FOREIGN KEY (fk_person_id) REFERENCES person (id);

