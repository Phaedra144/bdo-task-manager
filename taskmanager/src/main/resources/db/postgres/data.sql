INSERT INTO users (full_name, email, city, zip) SELECT 'Maria Escobito', 'maria@gmail.com', 'Caracas', '6085557683' WHERE NOT EXISTS (SELECT * FROM users WHERE id=1);

INSERT INTO tasks (title, description, user_id) SELECT 'Do homework', 'Do very important math homework', 1 WHERE NOT EXISTS (SELECT * FROM tasks WHERE id=1);
INSERT INTO tasks (title, description, user_id) SELECT 'Do the dishes', null, 1 WHERE NOT EXISTS (SELECT * FROM tasks WHERE id=2);
