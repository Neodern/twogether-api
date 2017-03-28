--
-- person
--
CREATE TABLE person (
  id         BIGSERIAL CONSTRAINT pk_person PRIMARY KEY,
  email      TEXT    NOT NULL,
  firstname  TEXT,
  lastname   TEXT,
  role       TEXT    NOT NULL,
  password   TEXT    NOT NULL,
  enabled    BOOLEAN NOT NULL,
  created_at DATE    NOT NULL,
  updated_at DATE    NOT NULL,
  version    INT     NOT NULL
);

--
-- couple
--
CREATE TABLE couple (
  id            BIGSERIAL CONSTRAINT pk_couple PRIMARY KEY,
  fk_person1_id BIGINT NOT NULL,
  fk_person2_id BIGINT NOT NULL,
  status        TEXT   NOT NULL,
  birthdate     DATE,
  created_at    DATE   NOT NULL
);

