drop schema if exists `full-stack-ecommerce`;

create schema `full-stack-ecommerce`;
use `full-stack-ecommerce`;

create table if not exists `full-stack-ecommerce`.`product_category` (
	`id` bigint(20) not null auto_increment,
    `category_name` varchar(255) null default null,
    primary key (`id`))
engine = InnoDB
auto_increment = 1;

create table if not exists `full-stack-ecommerce`.`product` (
	`id` bigint(20) not null auto_increment,
    `sku` varchar(255) default null,
    `name` varchar(255) default null,
    `description` varchar(255) default null,
    `unit_price` decimal(13, 2) default null,
    `image_url` varchar(255) default null,
    `active` bit default 1,
    `units_in_stock` int(11) default null,
    `date_created` datetime(6) default null,
    `last_updated` datetime(6) default null,
    `category_id` bigint(20) not null,
    primary key (`id`),
    key `fk_category` (`category_id`),
    constraint `fk_category` foreign key (`category_id`) references `product_category` (`id`))
engine = InnoDB
auto_increment = 1;

insert into product_category(category_name) values ('books');

insert into product(sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
	values('BOOK-TECH-1001', 'Spring Framework Tutorial', 'Learn Spring', 'assets/images/products/placeholder.png'
			,1,100,29.99,1, NOW());

insert into product(sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
	values('BOOK-TECH-1001', 'Spring Framework Tutorial', 'Learn Spring', 'assets/images/products/placeholder.png'
			,1,100,29.99,1, NOW());
            
insert into product(sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
	values('BOOK-TECH-1004', 'The Go Programming Language: A to Z', 'Learn Go', 'assets/images/products/placeholder.png'
			,1,100,24.99,1, NOW());