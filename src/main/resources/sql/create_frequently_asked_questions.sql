create table frequently_asked_questions (
   id integer not null auto_increment,
   question varchar(255),
   title varchar(255),
   primary key (id),
   foreign key (frequently_asked_question_id) references frequently_asked_question (id),
   foreign key (user_id) references user (id)
) engine=InnoDB