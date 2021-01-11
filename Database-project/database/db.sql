CREATE DATABASE zapatos;

use zapatos;

CREATE TABLE customer (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  cc VARCHAR(15),
  phone VARCHAR(15),
  address VARCHAR(100),
  loans INT(15) DEFAULT '0',
  description VARCHAR(100),
  amount INT(6) DEFAULT 0
);

CREATE TABLE inventory(
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  sizep VARCHAR(15),
  counter INT(6),
  price VARCHAR(15),
  status VARCHAR(15),
  description varchar(255) NOT NULL,
  image varchar(255) NOT NULL,
  amount INT(6) DEFAULT 0
);

CREATE TABLE vendors(
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  phone VARCHAR(15),
  description VARCHAR(100)
);

CREATE TABLE users (
  id INT(11) NOT NULL,
  username VARCHAR(16) NOT NULL,
  password VARCHAR(60) NOT NULL,
  fullname VARCHAR(100) NOT NULL
);

ALTER TABLE users
  ADD PRIMARY KEY (id);

ALTER TABLE users
  MODIFY id INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 2;

show tables;

describe customer;
describe vendors;
describe inventory;
describe users;
