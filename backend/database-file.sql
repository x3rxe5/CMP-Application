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
    password text not null,
    dob date not null
);

create table ca_media(
    user_id integer not null,
    media_id integer primary key not null,
    name varchar(255) not null,
    media_size decimal not null,
    url varchar(255) not null,
    contentType bytea not null;
);
alter table ca_media add constraint media_cat_fk
foreign key(user_id) references ca_users(user_id);

create sequence ca_users_seq increment 1 start 1;
create sequence ca_media_seq increment 1 start 1;