INSERT INTO auth_system.tb_user (user_id, username, password)
VALUES (1, 'alex', '123456');


INSERT INTO auth_system.tb_role (role_id, role_name)
VALUES (1, 'admin');


INSERT INTO auth_system.tb_role_menu (role_id, menu_key)
VALUES (1, 'store.query'),
       (1, 'order.query');
