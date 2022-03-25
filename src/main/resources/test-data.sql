DROP TABLE IF EXISTS `sims_db`;

CREATE TABLE sims_db(
id long AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
size FLOAT NOT NULL,
costPrice float NOT NULL,
listedPrice float NOT NULL,
isSold boolean NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO `sims_db` (`name`, `size`, `costPrice`, `listedPrice`, `isSold`) VALUES (`sneaker1`, 6.5, 160.00, 240.00, false);
INSERT INTO `sims_db` (`name`, `size`, `costPrice`, `listedPrice`, `isSold`) VALUES (`sneaker2`, 8.5, 170.00, 290.00, false);
INSERT INTO `sims_db` (`name`, `size`, `costPrice`, `listedPrice`, `isSold`) VALUES (`sneaker3`, 9.0, 110.00, 280.00, false);
INSERT INTO `sims_db` (`name`, `size`, `costPrice`, `listedPrice`, `isSold`) VALUES (`sneaker4`, 4.5, 75.00, 180.00, false);