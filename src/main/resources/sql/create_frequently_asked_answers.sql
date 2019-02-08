create table frequently_asked_answers (
   id integer not null auto_increment,
   answer varchar(255),
   frequently_asked_question_id integer,
   user_id integer,
   primary key (id),
   foreign key (frequently_asked_question_id) references frequently_asked_questions (id)
) engine=InnoDB