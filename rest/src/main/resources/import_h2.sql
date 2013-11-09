insert into vitamine (name, description) values ('Vitamine A', 'Vitamine A desc.');
insert into vitamine (name, description) values ('Vitamine B', 'Vitamine B desc.');
insert into vitamine (name, description) values ('Vitamine C', 'Vitamine C desc.');
insert into vitamine (name, description) values ('Vitamine D', 'Vitamine D desc.');
insert into vitamine (name, description) values ('Calcium', 'Calcium desc.');

insert into vitamineamount (vitamine_id, amount) values (1, 4);
insert into vitamineamount (vitamine_id, amount) values (2, 23);
insert into vitamineamount (vitamine_id, amount) values (3, 5);
insert into vitamineamount (vitamine_id, amount) values (4, 7);

insert into vitamineamount (vitamine_id, amount) values (1, 6);
insert into vitamineamount (vitamine_id, amount) values (2, 45);
insert into vitamineamount (vitamine_id, amount) values (3, 27);
insert into vitamineamount (vitamine_id, amount) values (4, 1);

insert into vitamineamount (vitamine_id, amount) values (1, 23);
insert into vitamineamount (vitamine_id, amount) values (2, 41);
insert into vitamineamount (vitamine_id, amount) values (3, 16);
insert into vitamineamount (vitamine_id, amount) values (4, 29);

insert into vitamineamount (vitamine_id, amount) values (1, 16);
insert into vitamineamount (vitamine_id, amount) values (2, 14);
insert into vitamineamount (vitamine_id, amount) values (3, 17);
insert into vitamineamount (vitamine_id, amount) values (4, 21);

insert into activity (name, description, foodType, calories, happiness, fatLevel) values ('Sing', 'Sing desc.', 'PLAY', -1, 10, -5);
insert into activity (name, description, foodType, calories, happiness, fatLevel) values ('Run', 'Run desc.', 'RUN', -20, 10, -20);
insert into activity (name, description, foodType, calories, happiness, fatLevel) values ('Play', 'Play desc.', 'PLAY', -10, 30, -10);
insert into activity (name, description, foodType, calories, happiness, fatLevel) values ('TV', 'TV desc.', 'TV', 30, 5, 5);
insert into activity (name, description, foodType, calories, happiness, fatLevel) values ('Clean room', 'HOMEWORK desc.', 'HOMEWORK', 30, -5, -10);

insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Meat', 'Pig meat', 50, 20, 'MEAT',20 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Fish', 'Fish', 30, 10, 'FISH',5 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Legume', 'Legume', 10, 15, 'LEGUME',0 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Banana', 'Banana', 30, 7, 'FRUIT',0 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Candy', 'Candy', 100, 24, 'CANDY',10 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Cereals', 'Cereals', 70, 10, 'CEREAL',5 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Carrot', 'Carrot', 70, 10, 'VEGETABLE',5 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Pizza', 'Pizza slice', 70, 10, 'CEREAL',10 );

insert into food_vitamines (food_id, vitamineamount_id) values  (1, 1);
insert into food_vitamines (food_id, vitamineamount_id) values  (1, 4);
insert into food_vitamines (food_id, vitamineamount_id) values  (1, 3);

insert into food_vitamines (food_id, vitamineamount_id) values  (2, 1);
insert into food_vitamines (food_id, vitamineamount_id) values  (2, 3);
insert into food_vitamines (food_id, vitamineamount_id) values  (2, 2);

insert into food_vitamines (food_id, vitamineamount_id) values  (3, 1);
insert into food_vitamines (food_id, vitamineamount_id) values  (3, 2);

insert into food_vitamines (food_id, vitamineamount_id) values  (4, 1);
insert into food_vitamines (food_id, vitamineamount_id) values  (4, 4);

insert into food_vitamines (food_id, vitamineamount_id) values  (5, 2);
insert into food_vitamines (food_id, vitamineamount_id) values  (6, 3);


insert into lobster (name, password, email) values ('lobster1', 'pwd1', 'lob1@mail.com');
insert into lobster (name, password, email) values ('lobster2', 'pwd2', 'lob2@mail.com');
insert into lobster (name, password, email) values ('lobster3', 'pwd3', 'lob3@mail.com');
insert into lobster (name, password, email) values ('lobster4', 'pwd4', 'lob4@mail.com');
insert into lobster (name, password, email) values ('lobster5', 'pwd5', 'lob5@mail.com');
insert into lobster (name, password, email) values ('lobster6', 'pwd6', 'lob6@mail.com');


insert into status (fatLevel, happiness, lastEat)  values (50, 80, null) ;
insert into status (fatLevel, happiness, lastEat)  values (75, 50, null) ;
insert into status (fatLevel, happiness, lastEat)  values (30, 60, null) ;
insert into status (fatLevel, happiness, lastEat)  values (50, 10, null) ;



update lobster set status_id = 1 where name = 'lobster1';
update lobster set status_id = 2 where name = 'lobster2';
update lobster set status_id = 3 where name = 'lobster3';
update lobster set status_id = 4 where name = 'lobster4';

