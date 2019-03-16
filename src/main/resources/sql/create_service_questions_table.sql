create table service_questions (
    id integer not null auto_increment,
      choices varchar(255),
      question varchar(255),
      type integer,
      primary key (id)
) engine=InnoDB
