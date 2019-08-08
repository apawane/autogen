DROP TABLE IF EXISTS todoitem;

CREATE TABLE ToDoItem (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  text VARCHAR(50) NOT NULL,
  is_Completed BIT NOT NULL,
  created_At TIMESTAMP DEFAULT NULL
);

INSERT INTO ToDoItem (text, is_Completed, created_At, id) VALUES
  ('start walking', 1, '2016-05-03 15:10:24.007', 1000),
  ('drink water', 1, '2016-05-03 15:10:24.007', 1001),
  ('eat breakfast', 0, '2016-05-03 15:10:24.007', 1002);
