CREATE DATABASE quarkusSocial;

CREATE TABLE users (
   id bigint generated by default as identity,
   age integer not null,
   name varchar(255) not null,
   primary key (id)
);

CREATE TABLE posts (
    id bigint generated by default as identity,
    post_text varchar(150) not null,
    dateTime timestamp not null,
    user_id bigint not null references users(id),
    primary key (id)
);

CREATE TABLE followers (
   id bigint generated by default as identity,
   user_id bigint not null references users(id),
   follower_id bigint not null references users(id),
   primary key (id)
);
