create table delivery_fee (
   id integer not null,
    fee float not null,
    flat bit not null,
    frequency integer,
    primary key (id)
) engine=InnoDB
