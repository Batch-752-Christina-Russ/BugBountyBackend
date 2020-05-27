create table if not exists roles (
role_id serial Primary key,
role_name varchar
);

create table if not exists users(
user_id serial Primary key,
username varchar unique,
password varchar not null,
points numeric,
role_id integer,
foreign key (role_id) references roles (role_id)
);

create table  if not exists bug_reports (
bug_id serial Primary key,
reporter_id integer,
resolver_id integer,
application_name varchar,
location_of_bug varchar,
description varchar,
steps varchar,
severity varchar,
submission_date timestamp with time zone default Now(),
status varchar,
foreign key (reporter_id) references users (user_id),
foreign key (resolver_id) references users (user_id)
);
INSERT INTO public.roles (role_id,role_name) VALUES 
(1,'user')
,(2,'admin');
INSERT INTO public.users (user_id,username,password,points,role_id) VALUES 
(USERS_USER_ID_SEQ.nextval,'stephanie','test',0,2);
