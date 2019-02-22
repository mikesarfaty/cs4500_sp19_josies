create table estimate (
	id integer not null auto_increment,
	estimate float(10,2),
	basePrice float(10,2),
	baseFrequency int,
	subscription bit,
	subscriptionFrequency int
	deliveryFrequency int
	primary key (id),
	foreign key (provider_id) references user (id),
	foreign key (service_id) references service (id)
	) engine=InnoDB




