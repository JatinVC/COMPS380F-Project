/* 
	users table 
	takes in a username and password
	mostly will be get requests that will just reference the values for comparision
*/
CREATE TABLE users (
	user_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
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
	user_id INTEGER NOT NULL,
	role VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_role_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

/* 
	items
*/

CREATE TABLE items (
	item_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	item_name VARCHAR(50) NOT NULL,
	item_price INTEGER NOT NULL,
	item_description VARCHAR(250) NOT NULL,
	item_availability VARCHAR(50) NOT NULL DEFAULT 'YES',
	PRIMARY KEY(item_id)
);

/* 
	item comments
*/

CREATE TABLE item_comments(
	comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	user_id INTEGER NOT NULL,
	comment_content VARCHAR(250) NOT NULL,
	comment_date DATE,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	PRIMARY KEY (comment_id)
);

/* 
	item photos
	picture for an item
*/

CREATE TABLE item_picture(
	picture_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	item_id INTEGER NOT NULL,
	picture_mimetype VARCHAR(50) NOT NULL,
	picture_data VARCHAR(250) NOT NULL,
	FOREIGN KEY (item_id) REFERENCES items(item_id),
	PRIMARY KEY (picture_id)
);

/*
	order system
	this is filled in once an order is complete
*/
CREATE TABLE orders(
	order_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	user_id INTEGER NOT NULL,
	order_total INTEGER NOT NULL DEFAULT 0,
	order_date DATE,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	PRIMARY KEY(order_id)
);

/*
	cart items
	all items in the cart after pressing 'order' button on website
*/

CREATE TABLE cart(
	cart_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	order_id INTEGER NOT NULL,
	item_id INTEGER NOT NULL,
	cart_quantity INTEGER NOT NULL DEFAULT 1,
	FOREIGN KEY (order_id) REFERENCES orders(order_id),
	FOREIGN KEY (item_id) REFERENCES items(item_id),
	PRIMARY KEY (cart_id)
);