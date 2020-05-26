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

INSERT INTO public.users (user_id,username,password,points,role_id) VALUES(USERS_USER_ID_SEQ.nextval, 'Stephanie', 'pass', 0, 1),
(USERS_USER_ID_SEQ.nextval, 'Cody', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Colin', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Jake', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Devin', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Laurence', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Joshua', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Jordan', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Jack', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'David', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Connor', 'pass', 50, 1),
(USERS_USER_ID_SEQ.nextval, 'Admin', 'pass', 50, 2);

INSERT INTO public.bug_reports(bug_id, reporter_id, resolver_id, application_name, location_of_bug, description, steps, severity, submission_date, status) VALUES(BUG_REPORTS_BUG_ID_SEQ.nextval, 1, null, 'our application', 'on page 243', 'scary bug', 'open to page 243, observe bug', 'low', NOW(), 'pending'),
(BUG_REPORTS_BUG_ID_SEQ.nextval, 5, null, 'some app', 'that place where the bug lives', 'we need more bugs', 'sometimes it is necessary to introduce bugs when the nature of your project is finding and squashing them', 'high', NOW(), 'open'),
(BUG_REPORTS_BUG_ID_SEQ.nextval, 2, null, 'this app', 'over there', 'missing a ;', 'open page, view bug', 'critical', NOW(), 'pending'),
(BUG_REPORTS_BUG_ID_SEQ.nextval, 3, null, 'our app', 'where it is', 'greasy spoon', 'page opens to wrong site', 'high', NOW(), 'open'),
(BUG_REPORTS_BUG_ID_SEQ.nextval, 4, null, 'that app', 'above', 'existing bug', 'above the word', 'medium', NOW(), 'pending');

