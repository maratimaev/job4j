create table bodys (
	id serial primary key,
	body varchar(200)
)

create table engines (
	id serial primary key,
	engine varchar(200)
)

create table transmissions (
	id serial primary key,
	transmission varchar(200)
)

create table cars (
	id serial primary key,
	name varchar(200),
	bodys_id int references bodys(id),
	engines_id int references engines(id),
	transmission_id int references transmissions(id)
)

insert into bodys (body) values ('hatch'),('sedan'),('universal')
insert into engines (engine) values ('1.4'),('1.6'),('2.0')
insert into transmissions (transmission) values ('mechanic'),('auto')

insert into cars (name, bodys_id, engines_id, transmission_id) 
values ('bmw', '1','2','1'),
		('audi','3','3','2'),
		('lada','1','2','1')

--1
select c.name, b.body, e.engine, t.transmission from cars as c
join bodys as b
on c.bodys_id = b.id
join engines as e
on c.engines_id = e.id
join transmissions as t
on c.transmission_id = t.id

--2
select c.name, b.body, e.engine, t.transmission from cars as c
full join bodys as b
on c.bodys_id = b.id
full join engines as e  
on c.engines_id = e.id
full join transmissions as t
on c.transmission_id = t.id
where c.name is null
