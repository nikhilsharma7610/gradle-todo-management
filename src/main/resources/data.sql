DROP TABLE IF EXISTS item;

CREATE TABLE item (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR NOT NULL,
  description VARCHAR NOT NULL,
  status VARCHAR NOT NULL,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO item (title, description, status)
VALUES (
        'My initial todo',
        'This is auto generated todo',
        'ACTIVE'
       )