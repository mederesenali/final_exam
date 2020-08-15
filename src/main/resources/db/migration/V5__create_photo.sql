create table photo(
    id int not null auto_increment,
    filename varchar(200),
    place_id int,
    foreign key (place_id) references place(id),
    primary key (id)
)