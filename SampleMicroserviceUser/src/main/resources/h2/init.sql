
CREATE TABLE users(
id bigint auto_increment PRIMARY KEY,
name varchar(50),
balance int
);

CREATE TABLE user_transaction(
id bigint  PRIMARY KEY,
user_id bigint,
amount int,
transaction_date timestamp,
foreign key (user_id) references users(id)
);
