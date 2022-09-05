create table song(
    song_id int auto_increment,
    name varchar(30) not null,
    genre varchar(30) not null,
    length time not null,
    album_id int not null,
    constraint pk_song primary key(song_id)
);

create table member(
    member_id int auto_increment,
    name varchar(30) not null unique,
    artist_id int,
    constraint pk_member primary key(member_id)
);

create table album(
    album_id int auto_increment,
    name varchar(30) not null unique,
    release_date date not null,
    artist_id int not null,
    distributor_id int,
    constraint pk_album primary key(album_id)
);

create table artist(
    artist_id int auto_increment,
    name varchar(30) not null unique,
    origin_country varchar(50) not null,
    email varchar(30) not null unique,
    constraint pk_artist primary key(artist_id)
);

create table music_distributor(
    distributor_id int auto_increment,
    headquarters_country varchar(30) not null,
    name varchar(50) not null,
    constraint pk_distributor primary key(distributor_id)
);

create table admin(
    admin_id int auto_increment,
    username varchar(30) not null unique,
    password varchar(30) not null,
    constraint pk_admin primary key(admin_id)
);