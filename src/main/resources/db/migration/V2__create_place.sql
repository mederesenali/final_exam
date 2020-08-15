create table place(
 id int not null auto_increment,
 tag varchar(45) not null ,
 description varchar(200) not null ,
 filename varchar(500) not null ,
 reviewers int ,
 rating double ,
 primary key (id)

)