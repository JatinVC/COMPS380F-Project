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
	item_availability INTEGER NOT NULL DEFAULT 100,
	PRIMARY KEY(item_id)
);
/* 
	items example
*/

Delete from items;
ALTER TABLE items ALTER COLUMN item_id RESTART WITH 1;
INSERT INTO items(item_name, item_price,item_description,item_availability) VALUES ('Pizza', 50,'A dish of Italian origin, consisting of a flat round base of dough baked with a topping of tomatoes and cheese, typically with added meat, fish, or vegetables.',500);
INSERT INTO items(item_name, item_price,item_description,item_availability) VALUES ('Hamburger', 80,'A hamburger (also burger for short) is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun. The patty may be pan fried, grilled, smoked or flame broiled.',300);
INSERT INTO items(item_name, item_price,item_description,item_availability) VALUES ('Sushi', 80,'Sushi is a traditional Japanese dish of prepared vinegared rice (鮨飯, sushi-meshi), usually with some sugar and salt, accompanying a variety of ingredients',400);



/* 
	item comments
*/

CREATE TABLE item_comments(
	comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	user_id INTEGER NOT NULL,
	item_id INTEGER NOT NULL,
	comment_content VARCHAR(250) NOT NULL,
	comment_date DATE,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
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
	picture_data VARCHAR(250) NOT NULL,
	FOREIGN KEY (item_id) REFERENCES items(item_id),
	PRIMARY KEY (picture_id)
);

/*

	order system
	this is filled in once an order is complete
*/
Delete from item_picture;
ALTER TABLE item_picture ALTER COLUMN picture_id RESTART WITH 1;
INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (1,'https://hongkongliving.com/wp-content/uploads/2020/05/Emmer-Pizza-HK.jpg','pizza1','pizza1');
INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (1,'https://d2d5f3568fvb9s.cloudfront.net/wp-content/uploads/2020/07/20165100/aurelien-lemasson-theobald-x00CzBt4Dfk-unsplash-960x500.jpg','pizza2','pizza2');
INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (1,'https://www.sassyhongkong.com/wp-content/uploads/2020/01/eat-drink-best-pizza-hong-kong-500x500.jpg','pizza3','pizza3');

INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (2,'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/RedDot_Burger.jpg/1200px-RedDot_Burger.jpg','Hamburger1','Hamburger1');
INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (2,'https://littlesunnykitchen.com/wp-content/uploads/2019/09/Air-fryer-hamburgers-6.jpg','Hamburger2','Hamburger2');
INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (2,'https://s01.sgp1.cdn.digitaloceanspaces.com/article/143395-pysnzzzleh-1593090551.jpg','Hamburger3','Hamburger3');

INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (3,'https://www.kikkoman.com/homecook/search/recipe/img/00005163.jpg','Sushi1','Sushi1');
INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (3,'https://cdn.asiatatler.com/asiatatler/i/hk/2018/11/06132336-story-image-105298_cover_1000x817.jpg','Sushi2','Sushi2');
INSERT INTO item_picture(item_id,picture_data,picture_name,picture_mimetype) VALUES (3,'https://media-cdn.tripadvisor.com/media/photo-s/14/7a/a7/f9/koh-sushi.jpg','Sushi3','Sushi3');
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