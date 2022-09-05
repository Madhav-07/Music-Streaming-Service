insert into admin(username,password) values
("madhav","hi@123");

insert into artist(name,origin_country,email) values
("The Script","Ireland","thescriptmusic@gmail.com"),
("Niall Horan","Ireland","niallhoranmusic@gmail.com"),
("Linkin Park","USA","linkinparkmusic@gmail.com"),
("James Arthur","England","jamesarthurmusic@gmail.com");

insert into member(name,artist_id) values
("Danny O'Donoghue",1),
("Mark Sheehan",1),
("Glen Power",1),
("Niall Horan",2),
("Chester Bennington",3),
("Rob Bourdon",3),
("Brad Delson",3),
("Mike Shinoda",3),
("Dave Farrell",3),
("Joe Hahn",3),
("James Arthur",4);

insert into music_distributor(name,headquarters_country) values
("Sony Music Entertainment","England"),
("Neon Haze Music","Ireland"),
("Warner Records Inc","USA"),
("Sony Music Entertainment","Germany");

insert into album(name,release_date,artist_id,distributor_id) values
("Sunset & Full Moons","2019-11-08",1,1),
("Flicker","2017-10-20",2,2),
("One More Light","2017-05-19",3,3),
("Meteora","2003-03-25",3,3),
("Living Things","2012-06-26",3,3),
("A Thousand Suns","2010-09-08",3,3),
("Say You Won't Let Go","2016-09-09",4,4),
("Falling Like The Stars","2019-05-10",4,4);

insert into song(name,genre,length,album_id) values
("Run Through Walls","Pop","00:03:24",1),
("If You Don't Love Yourself","Pop","00:02:44",1),
("Hurt People Hurt People","Pop","00:03:06",1),
("Same Time","Pop","00:03:16",1),
("The Hurt Game","Alternate Rock","00:03:47",1),
("Hot Summer Nights","Pop","00:03:32",1),
("Flicker","Pop","00:04:15",2),
("Slow Hands","Pop","00:03:07",2),
("Seeing Blind","Pop","00:03:07",2),
("Too Much To Ask","Pop","00:03:41",2),
("Paper Houses","Pop","00:03:33",2),
("Mirrors","Alternate Rock","00:02:47",2),
("One More Light","Pop","00:04:13",3),
("Heavy","Pop","00:02:48",3),
("Battle Symphony","Pop","00:03:35",3),
("Nobody Can Save Me","Pop","00:03:44",3),
("Invisible","Alternate Rock","00:03:33",3),
("Sharp Edges","Pop","00:02:57",3),
("Numb","Rock","00:03:04",4),
("Somewhere I Belong","Rock","00:03:32",4),
("Faint","Rock","00:02:41",4),
("Breaking The Habit","Rock","00:03:15",4),
("Castle Of Glass","Alternate Rock","00:03:24",5),
("Burn It Down","Rock","00:03:49",5),
("Lost In The Echo","Rock","00:03:22",5),
("Burning In The Skies","Rock","00:04:12",6),
("Waiting For The End","Alternate Rock","00:03:50",6),
("Irdescent","Alternate Rock","00:04:55",6),
("The Catalyst","Rock","00:05:38",6),
("Say You Won't Let Go","Pop","00:03:30",7),
("Falling Like The Stars","Pop","00:03:31",8);