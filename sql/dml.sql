INSERT INTO tb_user (user_id, username, password)
VALUES (1, 'alex', '123456'),
       (2, 'beth', '123456');


INSERT INTO tb_role (role_id, role_name)
VALUES (1, 'admin');


INSERT INTO tb_user_role (user_id, role_id)
VALUES (1, 1);


INSERT INTO tb_role_menu (role_id, menu_key)
VALUES (1, 'store.query'),
       (1, 'order.query');


INSERT INTO tb_role_store (role_id, store_id)
VALUES (1, 1);


INSERT INTO tb_store (store_id, store_name)
VALUES (1, 'AA'),
       (2, 'BB');


INSERT INTO tb_order (order_id, store_id, product_name, order_time)
VALUES (1, 1, 'sku1', now()),
       (2, 2, 'sku2', now());
