CREATE TABLE IF NOT EXISTS users (
  id         INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  full_name TEXT,
  password  TEXT,
  email    TEXT,
  city       TEXT,
  zip  TEXT,
  street TEXT,
  street_number TEXT,
  deleted    BOOLEAN DEFAULT FALSE
);
ALTER TABLE users ALTER COLUMN street_number TYPE integer USING (street_number::integer);


CREATE TABLE IF NOT EXISTS tasks (
  id         INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  title TEXT,
  description TEXT,
  user_id   INT REFERENCES users (id)
);


DROP ROLE IF EXISTS root;
CREATE ROLE root WITH LOGIN SUPERUSER PASSWORD 'password';
