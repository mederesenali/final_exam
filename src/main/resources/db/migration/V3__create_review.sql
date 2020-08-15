create table review(
    id int not null auto_increment,
    review varchar(200) not null ,
    local_date date,
    place_id int,
    user_id int,
    foreign key (place_id) references place(id),
    foreign key (user_id) references user(user_id),
    primary key (id)
)