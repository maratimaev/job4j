create table products (
	id serial primary key, 
	name varchar(2000), 
	type_id int references prod_type(id), 
	expired_date date, 
	price money
)

create table prod_type (
	id serial primary key, 
	name varchar(2000)
)

--1
select * from products as p
join prod_type as t
on p.type_id = t.id
where t.name = 'Сыр'

--2
select * from products
where name like '%мороженное%'

--3
select * from products as p
where extract(month from p.expired_date) = extract(month from current_date)+1

--4
select * from products
where price = (
	select max(price)
	from products
)

--5
select t.name, count(t.name) from prod_type as t
join products as p
on p.type_id = t.id
group by t.name

--6
select * from products as p
join prod_type as t
on p.type_id = t.id
where t.name = 'Сыр' or t.name = 'Хлеб'

--7
select t.name, count(p.name) as c from products as p
join prod_type as t
on p.type_id = t.id
group by t.name
having count(p.name) < 10

--8
select p.name, t.name from products as p
join prod_type as t
on p.type_id = t.id
order by t.name