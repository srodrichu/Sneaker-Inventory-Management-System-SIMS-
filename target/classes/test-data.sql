drop table if exists sneaker_entry CASCADE;
create table sneaker_entry (id bigint auto_increment, cost_price float not null, is_sold boolean default false not null, listed_price float not null, name varchar(150) not null, size float not null, primary key (id));

INSERT INTO `sneaker_entry` (`name`, `size`, `cost_price`, `listed_price`, `is_sold`) VALUES ('sneaker1', 6.50, 160.00, 240.00, false);
INSERT INTO `sneaker_entry` (`name`, `size`, `cost_price`, `listed_price`, `is_sold`) VALUES ('sneaker2', 8.50, 170.00, 290.00, false);
INSERT INTO `sneaker_entry` (`name`, `size`, `cost_price`, `listed_price`, `is_sold`) VALUES ('sneaker3', 9.00, 110.00, 280.00, false);
INSERT INTO `sneaker_entry` (`name`, `size`, `cost_price`, `listed_price`, `is_sold`) VALUES ('sneaker4', 4.50, 75.00, 180.00, false);