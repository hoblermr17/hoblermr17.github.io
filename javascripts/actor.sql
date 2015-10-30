use matt;
drop table if exists actor;
create table actor (
  actId int unsigned not null auto_increment,
  lastName varchar(24) not null,
  firstName varchar(18) not null,
  homePhone varchar(14) not null,
  salary double not null,
  primary key(actId)
);
