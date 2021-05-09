/* 
	users table 
	takes in a username and password
	mostly will be get requests that will just reference the values for comparision
*/
CREATE TABLE users (
	user_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	fullname VARCHAR(50) ,
	phonenumber VARCHAR(50) ,
	address VARCHAR(50),
	PRIMARY KEY (user_id)
);

/*
	user roles
	takes in the role of the user
	a single user can have multiple roles
	by default the role for any user will be: 'ROLE_USER'
	a user with admin privilages will be: 'ROLE_ADMIN'
*/
CREATE TABLE user_roles (
	user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	user_id INTEGER,
        username VARCHAR(50) NOT NULL,
	role VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_role_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);
INSERT INTO users(username,password)VALUES ('keith', '{noop}keithpw');
INSERT INTO user_roles(user_id,username, role) VALUES (1,'keith', 'ROLE_USER');
INSERT INTO user_roles(user_id,username, role) VALUES (1,'keith', 'ROLE_ADMIN');
INSERT INTO users (username,password)VALUES ('vanessa', '{noop}vanessapw');
INSERT INTO user_roles(user_id,username, role) VALUES (2,'vanessa', 'ROLE_ADMIN');
INSERT INTO users (username,password)VALUES ('kevin', '{noop}kevinpw');
INSERT INTO user_roles(user_id,username, role) VALUES (3,'kevin', 'ROLE_USER');
/* 
	items
*/

CREATE TABLE items (
	item_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	item_name VARCHAR(50) NOT NULL,
	item_price INTEGER NOT NULL,
	item_description VARCHAR(250) NOT NULL,
	item_availability BOOLEAN NOT NULL DEFAULT true,
	PRIMARY KEY(item_id)
);
/* 
	items example
*/

Delete from items;
ALTER TABLE items ALTER COLUMN item_id RESTART WITH 1;
INSERT INTO items(item_name, item_price,item_description,item_availability) VALUES ('Pizza', 50,'A dish of Italian origin, consisting of a flat round base of dough baked with a topping of tomatoes and cheese, typically with added meat, fish, or vegetables.',false);
INSERT INTO items(item_name, item_price,item_description,item_availability) VALUES ('Hamburger', 80,'A hamburger (also burger for short) is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun. The patty may be pan fried, grilled, smoked or flame broiled.',true);
INSERT INTO items(item_name, item_price,item_description,item_availability) VALUES ('Sushi', 80,'Sushi is a traditional Japanese dish of prepared vinegared rice (鮨飯, sushi-meshi), usually with some sugar and salt, accompanying a variety of ingredients',true);



/* 
	item comments
*/


CREATE TABLE item_comments(
    comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    item_id INTEGER NOT NULL,
    comment_content VARCHAR(250) NOT NULL,
    comment_date VARCHAR(250) NOT NULL,
    FOREIGN KEY (item_id) REFERENCES items(item_id),
    PRIMARY KEY (comment_id)
);
/* 
	item photos
	picture for an item
*/

CREATE TABLE item_picture(
	picture_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	item_id INTEGER NOT NULL,
	picture_name VARCHAR(50) NOT NULL,
	picture_mimetype VARCHAR(50) NOT NULL,
	picture_data BLOB NOT NULL,
	FOREIGN KEY (item_id) REFERENCES items(item_id),
	PRIMARY KEY (picture_id)
);

/*
	order system
	this is filled in once an order is complete
*/
CREATE TABLE orders(
	orderId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	userName VARCHAR(50) NOT NULL,
        item VARCHAR(50) NOT NULL,
	orderDate DATE,
	PRIMARY KEY(orderId)
);
