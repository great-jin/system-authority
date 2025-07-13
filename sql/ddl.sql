-- auth_system.tb_store definition
CREATE TABLE `tb_store`
(
    `store_id`   bigint(20) NOT NULL,
    `store_name` varchar(100) NOT NULL
);


-- auth_system.tb_order definition
CREATE TABLE `tb_order`
(
    `order_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `store_id`   bigint(20) NOT NULL
    `product_name` varchar(100) NOT NULL,
    `order_time` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`order_id`)
);


-- auth_system.tb_user definition
CREATE TABLE `tb_user`
(
    `user_id`  bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    PRIMARY KEY (`user_id`)
);


-- auth_system.tb_role definition
CREATE TABLE `tb_role`
(
    `role_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(100) NOT NULL,
    PRIMARY KEY (`role_id`)
) DEFAULT CHARSET=utf8mb4;


-- auth_system.tb_user_role definition
CREATE TABLE `tb_user_role`
(
    `user_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL
);


-- auth_system.tb_user_store definition
CREATE TABLE `tb_user_store`
(
    `user_id`  bigint(20) NOT NULL,
    `store_id` bigint(20) NOT NULL
);


-- auth_system.tb_role_menu definition
CREATE TABLE `tb_role_menu`
(
    `role_id`  bigint(20) NOT NULL,
    `menu_key` varchar(19) NOT NULL
);


-- auth_system.tb_role_store definition
CREATE TABLE `tb_role_store`
(
    `role_id`  bigint(20) NOT NULL,
    `store_id` bigint(20) NOT NULL
);
