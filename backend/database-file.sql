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
    username varchar(255) not null
);

create table ca_media(
    media_id integer primary key not null,
    user_id integer not null,
    name varchar(255) not null,
    media_size decimal not null,
    contentType varchar(255) not null,
    data bytea not null
);
alter table ca_media add constraint media_cat_fk
foreign key(user_id) references ca_users(user_id)
on delete cascade
on update cascade;


create table ca_friends_list(
    frd_id integer primary key not null,
    user_id integer not null,
    friend_id integer not null,
    accepted integer not null,
    blocked_status integer not null
);
alter table ca_friends_list add constraint friends_list_fk
foreign key(user_id) references ca_users(user_id)
on delete cascade
on update cascade;

create sequence ca_users_seq increment 1 start 1;
create sequence ca_media_seq increment 1 start 1;
create sequence ca_friends_list_seq increment 1 start 1;