alter table song
add constraint fk_song foreign key(album_id) references album(album_id);

alter table member
add constraint fk_member foreign key(artist_id) references artist(artist_id);

alter table album
add constraint fk_album_artist foreign key(artist_id) references artist(artist_id),
add constraint fk_album_distributor foreign key(distributor_id) references music_distributor(distributor_id);