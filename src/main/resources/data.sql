INSERT INTO `product` (`name`, `description`, `price`) VALUES ('iPhone 3', 'Apple iPhone 3', '100');
INSERT INTO `product` (`name`, `description`, `price`) VALUES ('iPhone 4', 'Apple iPhone 4', '200');
INSERT INTO `product` (`name`, `description`, `price`) VALUES ('iPhone 5', 'Apple iPhone 5', '300');
INSERT INTO `product` (`name`, `description`, `price`) VALUES ('iPhone 6', 'Apple iPhone 6', '400');
INSERT INTO `product` (`name`, `description`, `price`) VALUES ('iPhone 7', 'Apple iPhone 7', '500');
INSERT INTO `product` (`name`, `description`, `price`) VALUES ('iPhone X', 'Apple iPhone 10', '600');
INSERT INTO `product` (`name`, `description`, `price`) VALUES ('iPhone 11', 'Apple iPhone 11', '1000');
INSERT INTO `product` (`name`, `description`, `price`) VALUES ('iPhone 12', 'Apple iPhone 12', '2000');

INSERT INTO `user_order` (`buyer_email`, `timestamp`, `total`) VALUES ('example1@mail.com', NOW(), '1100');
INSERT INTO `user_order` (`buyer_email`, `timestamp`, `total`) VALUES ('example2@mail.com', NOW(), '600');
INSERT INTO `user_order` (`buyer_email`, `timestamp`, `total`) VALUES ('example3@mail.com', NOW(), '400');
INSERT INTO `user_order` (`buyer_email`, `timestamp`, `total`) VALUES ('example4@mail.com', NOW(), '2100');

INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('1', '7');
INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('1', '1');

INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('2', '2');
INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('2', '4');

INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('3', '4');

INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('4', '1');
INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('4', '2');
INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('4', '3');
INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('4', '4');
INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('4', '5');
INSERT INTO `order2product` (`fk_order_id`, `fk_product_id`) VALUES ('4', '6');

