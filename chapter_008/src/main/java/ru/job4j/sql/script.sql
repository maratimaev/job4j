create table users (
	id serial primary key, 
	name varchar(2000)
)

create table roles (
	id serial primary key,
	role varchar(2000)
)

alter table users add column roles_id int references roles(id); 

create table rules (
	id serial primary key,
	rule varchar(2000)
)

create table items (
	id serial primary key,
	item varchar(2000),
	users_id int references users(id) 
)

create table comments (
	id serial primary key,
	comments_desc varchar(2000),
	items_id int references items(id) 
)

create table attachs (
	id serial primary key,
	attach varchar(2000),
	items_id int references items(id) 
)

create table roles_rules_compose (
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id) 
)

create table categories (
	id serial primary key,
	category varchar(2000) 
)

alter table items add column categories_id int references categories(id);

create table states (
	id serial primary key,
	state varchar(2000) 
)

alter table items add column states_id int references states(id);

