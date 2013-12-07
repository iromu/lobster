insert into vitamine (name, description) values ('Vitamine A', 'La vitamina A tiene varias funciones importantes en el organismo como la resistencia a infecciones, la producción de anticuerpos, crecimiento óseo o fertilidad.');
insert into vitamine (name, description) values ('Vitamine B', 'Esenciales para el metabolismo');
insert into vitamine (name, description) values ('Vitamine C', 'Evitar el envejecimiento prematuro (proteger el tejido conectivo, la "piel" de los vasos sanguíneos)');
insert into vitamine (name, description) values ('Vitamine D', 'Vitamin D is a group of fat-soluble secosteroids responsible for enhancing intestinal absorption of calcium and phosphate   ');
insert into vitamine (name, description) values ('Calcium', '"Calcium plays an important role in building stronger, denser bones early in life and keeping bones strong and healthy later in life.');

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

insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Meat', 'It is one of the most commonly consumed meats worldwide, with evidence of pig husbandry dating back to 5000 BC. Nutrition: Its myoglobin content is lower than that of beef, but much higher than that of chicken', 50, 20, 'MEAT',20 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Fish', 'Fish is a meat consumed by many species, including humans. Fish provides a good source of high quality protein and contains many vitamins and minerals. ', 30, 10, 'FISH', 5 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Legume', 'Legumes are a type of vegetable that has a seed pod –- beans, lentils, peas, and even peanuts –- that can be cooked and eaten in curry, stew, soup, and salad. Beans have enough protein that they are often used as meat substitutes.', 10, 15, 'LEGUME', 0 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Banana', 'Wonderfully sweet with firm and creamy flesh, . The banana plant grows 10 to 26 feet and belongs to the Musaceae family of plants along with plantains.Source of potasium and fiber.', 30, 7, 'FRUIT', 0 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Candy', 'Most contain a substantial amount of sugar, and that’s it! . They will cause a rapid spike in blood sugar, but a short while later you might feel yourself crashing from lack of energy.', 100, 24, 'CANDY', 10 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Cereals', 'Besides carbohydrates cereals also provide proteins, calcium, iron, sodium, magnesium, fiber and B-complex vitamins. ', 70, 10, 'CEREAL', 5 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Carrot', 'Carrots are good for vitamin A', 70, 10, 'VEGETABLE',5 );
insert into food (name, description, happiness, calories, foodType, fatLevel) values ('Pizza', 'Is not good', 70, 10, 'CEREAL',10 );

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


insert into status (fatLevel, happiness, lastEat)  values (50, 80, null) ;
insert into status (fatLevel, happiness, lastEat)  values (75, 50, null) ;
insert into status (fatLevel, happiness, lastEat)  values (30, 60, null) ;
insert into status (fatLevel, happiness, lastEat)  values (50, 10, null) ;



update lobster set status_id = 1 where name = 'lobster1';
update lobster set status_id = 2 where name = 'lobster2';
update lobster set status_id = 3 where name = 'lobster3';
update lobster set status_id = 4 where name = 'lobster4';

