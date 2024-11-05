DROP TABLE users IF EXISTS;
DROP TABLE tasks IF EXISTS;

CREATE TABLE users (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  full_name VARCHAR(30),
  password   VARCHAR(30),
  email      VARCHAR(50),
  city       VARCHAR(30),
  zip  VARCHAR(30),
  street VARCHAR(30),
  street_number INTEGER,
  deleted    BOOLEAN DEFAULT FALSE
);

CREATE TABLE tasks (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  title TEXT,
  description TEXT,
  user_id   INTEGER,
  deleted    BOOLEAN DEFAULT FALSE
);
ALTER TABLE tasks ADD CONSTRAINT fk_tasks_users FOREIGN KEY (user_id) REFERENCES users (id);
