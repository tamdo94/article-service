-- article table --
INSERT INTO article (id,name,slogan,net_price,sales_price,vat) VALUES (100, 'T-Shirt', 'Cool T-Shirt', 20.0, 25.0, 8.0);
INSERT INTO article (id,name,slogan,net_price,sales_price,vat) VALUES (101, 'Polo', 'Cool Polo', 22.0, 38.0, 8.0);

-- discount table --
INSERT INTO discount (id,name,percentage,start,end,applied) VALUES (200, 'Discount 5%', 5.0, '2021-11-29 00:00', '2021-11-29 12:00', true);
INSERT INTO discount (id,name,percentage,start,end,applied) VALUES (201, 'Discount 10%', 10.0, '2021-11-29 00:00', '2021-11-29 23:59', true);
INSERT INTO discount (id,name,percentage,start,end,applied) VALUES (202, 'Discount 7%', 7.0, '2021-11-29 12:00', '2021-11-29 23:59', true);

-- article_discount table --
INSERT INTO article_discount (article_id, discount_id) VALUES (100, 200);
INSERT INTO article_discount (article_id, discount_id) VALUES (100, 202);
INSERT INTO article_discount (article_id, discount_id) VALUES (101, 201);