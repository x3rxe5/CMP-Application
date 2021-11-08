drop database cmp;
drop user cmpuser;

create user cmpuser with password 'test123';
create database cmp with template=template0 owner=cmpuser;
\connect cmp;
alter default privileges grant all on tables to cmpuser;
alter default privileges grant all on sequences to cmpuser;

create table ca_users(
    user_id integer primary key not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    password text not null
);

create sequence ca_users_seq increment 1 start 1;

