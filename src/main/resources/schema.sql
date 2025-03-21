drop table sandwichshop if exists;
drop table ratings if exists;

create table sandwichshop (id integer auto_increment primary key, name varchar(50) not null , rating decimal(2,1) not null);
create table ratings (id integer auto_increment primary key,
    sandwichshop_id integer not null,
    rating decimal(2,1) not null,
    review text,
    foreign key (sandwichshop_id) references sandwichshop(id)
);

