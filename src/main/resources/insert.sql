insert into PRODUCT (id, name, description, parentProduct_id) values ('1', 'Notebook', 'Notebook Dell Modelo Vostro 1100', null);
insert into PRODUCT (id, name, description, parentProduct_id) values ('2', 'Teclado', 'Teclado Dell Modelo Vostro 1100',  '1');
insert into PRODUCT (id, name, description, parentProduct_id) values ('3', 'Mouse', 'Mouse Dell Modelo Vostro 1100',  '1');
 call next value for SE_PRODUCT;
 call next value for SE_PRODUCT;
 call next value for SE_PRODUCT;
 insert into IMAGE (id, type, product_id) values ('1', 'jpeg', '1');
 insert into IMAGE (id, type, product_id) values ('2', 'png', '1');
 call next value for SE_IMAGE;
 call next value for SE_IMAGE;