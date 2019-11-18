CREATE TABLE `product` (
  `id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL
);

CREATE TABLE `user_order` (
  `id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `buyer_email` VARCHAR(255) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `total` DOUBLE NOT NULL
);

CREATE TABLE `order2product` (
  `id` BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `fk_order_id` INT(11) NOT NULL,
  `fk_product_id` INT(11) NOT NULL,
  CONSTRAINT `fk_order2product_orderId`
    FOREIGN KEY (`fk_order_id`)
    REFERENCES `user_order` (`id`),
  CONSTRAINT `fk_order2product_productId`
    FOREIGN KEY (`fk_product_id`)
    REFERENCES `product` (`id`)
);




