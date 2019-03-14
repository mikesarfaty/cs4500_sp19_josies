create table subscription_discount (
   id integer not null,
    discount float not null,
    flat bit not null,
    frequency integer,
    primary key (id)
) engine=InnoDB
